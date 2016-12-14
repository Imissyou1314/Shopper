package com.boyapp.missyou.shopper.presenter.impl;

import android.util.Log;

import com.boyapp.missyou.shopper.entity.Orders;
import com.boyapp.missyou.shopper.entity.ResultBean;
import com.boyapp.missyou.shopper.model.OrdersModel;
import com.boyapp.missyou.shopper.model.impl.OrdersModelImpl;
import com.boyapp.missyou.shopper.presenter.OrdersPresenter;
import com.boyapp.missyou.shopper.utils.GsonUtils;
import com.boyapp.missyou.shopper.view.MainView;
import com.kymjs.rxvolley.rx.Result;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by MissYou on 2016/12/12.
 */

public class OrdersPresenterImpl implements OrdersPresenter<MainView<Orders>> {

    private MainView mainView;
    private OrdersModel ordersModel;

    public OrdersPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        ordersModel = new OrdersModelImpl(this);
    }

    @Override
    public void initData() {
        ordersModel.initData();
    }

    @Override
    public void setInitView(Observable<Result> resultObservable) {
        resultObservable.map(new Func1<Result, String>() {

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
                                                   Logger.d("error");
                                                   Log.e("Error", "onError: ", e );
                                                   Logger.e("获取数据解析", e);
                                               }

                                               @Override
                                               public void onNext(String string) {
                                                   Logger.d("onNext: " + string);
                                                   ResultBean resultBean = GsonUtils.getResultBeanByJson(string);
                                                   //进行数据处理
                                                   List<Orders> ordersList = GsonUtils.
                                                           getBeanFromResultBeanListMiss(resultBean, "ordersList", Orders[].class);
                                                   if (null != ordersList && ordersList.size() != 0) {
                                                       Logger.json(GsonUtils.getJsonStr(ordersList));
                                                       mainView.setInitView(ordersList);
                                                   } else {
                                                       mainView.setErrorView(resultBean.getResultInfo());
                                                   }
                                               }
                                           }
                                );
    }

    @Override
    public void setSuccessView(Observable<Result> successMsg) {
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
                                                   Log.d("OrdersPresenterImpl", "succ");
                                               }

                                               @Override
                                               public void onError(Throwable e) {
                                                   Log.d("OrdersPresenterImpl==>","error");
                                                   Log.e("OrdersPresenterImpl", "登录错误信息", e);
                                               }

                                               @Override
                                               public void onNext(String string) {
                                                   Log.d("Login", "onNext: " + string);
                                                   ResultBean resultBean = GsonUtils.getResultBeanByJson(string);
                                                   //进行数据处理
                                                   if (resultBean.isServiceResult()) {
                                                       mainView.setSuccessView(resultBean.getResultInfo());
                                                   } else {
                                                       mainView.setErrorView(resultBean.getResultInfo());
                                                   }
                                               }
                                           }
                                );

    }

    @Override
    public void sendOrders(Integer ordersId) {
        ordersModel.sendOrders(ordersId);
    }

    @Override
    public void refuseOrders(Integer ordersId) {
        ordersModel.refuseOrders(ordersId);
    }

    @Override
    public void receOrders(Integer ordersId) {
        ordersModel.receOrders(ordersId);
    }


}
