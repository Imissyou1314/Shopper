package com.boyapp.missyou.shopper.presenter.impl;

import android.util.Log;

import com.boyapp.missyou.shopper.entity.Goods;
import com.boyapp.missyou.shopper.entity.ResultBean;
import com.boyapp.missyou.shopper.model.GoodModel;
import com.boyapp.missyou.shopper.model.impl.GoodModelImpl;
import com.boyapp.missyou.shopper.presenter.GoodsPresenter;
import com.boyapp.missyou.shopper.utils.GsonUtils;
import com.boyapp.missyou.shopper.view.GoodView;
import com.kymjs.rxvolley.rx.Result;
import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by MissYou on 2016/12/13.
 */
public class GoodsPresenterImpl implements GoodsPresenter {

    private GoodView mGoodView;
    private GoodModel mGoodModel;
    public GoodsPresenterImpl(GoodView goodView) {
        this.mGoodView = goodView;
        this.mGoodModel = new GoodModelImpl(this);
    }

    @Override
    public void deleteGoods(int goodsId) {
        mGoodModel.deleteGoods(goodsId);
    }

    @Override
    public void checkGoods(int goodsId, Boolean status) {
        mGoodModel.checkGoods(goodsId, status);
    }

    @Override
    public void updateGoods(Goods goods) {
        mGoodModel.updateGoods(goods);
    }

    @Override
    public void setInitView(Observable<Result> resultObservable) {

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
                                   Logger.d("succ");
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Logger.e("error");
                                   Log.e("Goods->Impl", "onError: ",e );
                                   Logger.e("登录错误信息", e);
                               }

                               @Override
                               public void onNext(String string) {
                                   Logger.d("onNext: " + string);
                                   ResultBean resultBean = GsonUtils.getResultBeanByJson(string);
                                   //进行数据处理
                                   if (resultBean.isServiceResult()) {
                                       mGoodView.setSuccessView(resultBean.getResultInfo());
                                   } else {
                                       mGoodView.setErrorView(resultBean.getResultInfo());
                                   }
                               }
                           }
                );
    }
}
