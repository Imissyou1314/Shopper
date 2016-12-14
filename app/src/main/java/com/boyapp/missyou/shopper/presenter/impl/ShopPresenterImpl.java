package com.boyapp.missyou.shopper.presenter.impl;

import android.util.Log;

import com.boyapp.missyou.shopper.entity.Goods;
import com.boyapp.missyou.shopper.entity.ResultBean;
import com.boyapp.missyou.shopper.model.ShopModel;
import com.boyapp.missyou.shopper.model.impl.ShopModelImpl;
import com.boyapp.missyou.shopper.presenter.ShopPresenter;
import com.boyapp.missyou.shopper.utils.GsonUtils;
import com.boyapp.missyou.shopper.view.BaseView;
import com.boyapp.missyou.shopper.view.ShopView;
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

public class ShopPresenterImpl implements ShopPresenter<ShopView> {

    private ShopView mShopView;
    private ShopModel mShopModel;

    public ShopPresenterImpl(BaseView mView) {
        this.mShopView = (ShopView) mView;
        this.mShopModel = new ShopModelImpl(this);
    }

    @Override
    public void initData(Long shopId) {
        mShopModel.initData(shopId);
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
                                   Logger.e("error");
                                   Log.e("ShopPresenterImpl", "onError: ",e );
                                   Logger.e("登录错误信息", e);
                               }

                               @Override
                               public void onNext(String string) {
                                   Logger.d("onNext: " + string);
                                   ResultBean resultBean = GsonUtils.getResultBeanByJson(string);
                                   //进行数据处理
                                   if (resultBean.isServiceResult()) {
                                       mShopView.setInitView(GsonUtils.getBeanFromResultBeanListMiss(
                                               resultBean,
                                               "goodsList",
                                               Goods[].class));
                                   } else {
                                       mShopView.setErrorView(resultBean.getResultInfo());
                                   }

                               }
                           }
                );
    }

    @Override
    public void setSuccessView(Observable<Result> successMsg) {

    }
}
