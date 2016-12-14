package com.boyapp.missyou.shopper.presenter.impl;

import android.util.Log;

import com.boyapp.missyou.shopper.entity.ResultBean;
import com.boyapp.missyou.shopper.model.AddGoodModel;
import com.boyapp.missyou.shopper.model.impl.AddGoodModelImpl;
import com.boyapp.missyou.shopper.presenter.AddGoodsPresenter;
import com.boyapp.missyou.shopper.utils.GsonUtils;
import com.boyapp.missyou.shopper.utils.StringUtils;
import com.boyapp.missyou.shopper.view.AddGoodView;
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
public class AddGoodsPresenterImpl implements AddGoodsPresenter {

    private AddGoodView mAddGoodView;
    private AddGoodModel mAddGoodModel;

    public AddGoodsPresenterImpl(AddGoodView addGoodView) {
        this.mAddGoodView = addGoodView;
        this.mAddGoodModel = new AddGoodModelImpl(this);
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
                                   Log.e("AddGoods->Impl", "onError: ",e );
                                   Logger.e("错误信息", e);
                               }

                               @Override
                               public void onNext(String string) {
                                   Logger.d("onNext: " + string);
                                   ResultBean resultBean = GsonUtils.getResultBeanByJson(string);
                                   //进行数据处理
                                   if (resultBean.isServiceResult()) {
                                       mAddGoodView.setSuccessView(resultBean.getResultInfo());
                                   } else {
                                       mAddGoodView.setErrorView(resultBean.getResultInfo());
                                   }

                               }
                           }
                );
    }

    @Override
    public void addGoods(String name,
                         String number,
                         String defNumber,
                         String desc,
                         int shopId,
                         String price) {

        if (StringUtils.checkString(name) && StringUtils.checkString(desc)) {
            if (Integer.valueOf(number) > 0 &&
                    Integer.valueOf(defNumber) > 0 &&
                    Integer.valueOf(price) > 0)
                mAddGoodModel.addGoods(name, number, defNumber, desc, shopId, price);
        } else {
            mAddGoodView.setErrorView("填写的数据不正确");
        }

    }
}
