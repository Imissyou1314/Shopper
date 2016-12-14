package com.boyapp.missyou.shopper.presenter;

import com.boyapp.missyou.shopper.view.BaseView;
import com.kymjs.rxvolley.rx.Result;

import rx.Observable;

/**
 * Created by MissYou on 2016/12/12.
 */

public interface BasePresenter{

    /**
     * 设置回调函数
     * @param resultObservable
     */
    void setInitView(Observable<Result> resultObservable);

    /**
     * 提交操作成功
     * @param successMsg
     */
    void setSuccessView(Observable<Result> successMsg);


}
