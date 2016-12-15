package com.boyapp.missyou.shopper.presenter.impl;

import android.util.Log;

import com.boyapp.missyou.shopper.view.OrderDetailView;
import com.boyapp.missyou.shopper.entity.OrderDetail;
import com.boyapp.missyou.shopper.entity.ResultBean;
import com.boyapp.missyou.shopper.model.OrderDetailModel;
import com.boyapp.missyou.shopper.model.impl.OrderDetailModelImpl;
import com.boyapp.missyou.shopper.presenter.OrderDetailPresenter;
import com.boyapp.missyou.shopper.utils.GsonUtils;
import com.kymjs.rxvolley.rx.Result;
import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by MissYou on 2016/12/12.
 */

public class OrderDetaliPresenterImpl implements OrderDetailPresenter<OrderDetailView>{
    private OrderDetailView mOrderDetailView;
    private OrderDetailModel mOrderDetailModel;

    public OrderDetaliPresenterImpl(OrderDetailView mOrderDetailView) {
        this.mOrderDetailView = mOrderDetailView;
        this.mOrderDetailModel = new OrderDetailModelImpl(this);
    }

    @Override
    public void setSuccessView(Observable<Result> successMsg) {

    }

    @Override
    public void setInitView(Observable<Result> successMsg) {
        successMsg.map(new Func1<Result, String>() {

            @Override
            public String call(Result result) {
                return new String(result.data);
            }
        } )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                               @Override
                               public void onCompleted() {
                                   Logger.d("succ");
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Logger.e("error");
                                   Log.e("OrderDetaliPresenter", "onError: ",e );
                                   Logger.e("登录错误信息", e);
                               }

                               @Override
                               public void onNext(String string) {
                                   Logger.d("onNext: " + string);
                                   ResultBean resultBean = GsonUtils.getResultBeanByJson(string);
                                   //进行数据处理
                                  if (resultBean.isServiceResult()) {
                                      mOrderDetailView.setInitView(
                                              GsonUtils.getBeanFromResultBeanListMiss(
                                                      resultBean,
                                                      "orderDetailList",
                                                      OrderDetail[].class));
                                  } else {
                                      mOrderDetailView.setErrorView(resultBean.getResultInfo());
                                  }
                               }
                           }
                );
    }

    @Override
    public void initData(Integer ordersId) {
        mOrderDetailModel.initData(ordersId);
    }

}
