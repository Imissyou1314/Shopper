package com.boyapp.missyou.shopper.presenter;


import com.boyapp.missyou.shopper.view.BaseView;

/**
 * Created by MissYou on 2016/12/12.
 */
public interface OrderDetailPresenter<T extends BaseView> extends BasePresenter{

    /**
     * 初始化页面数据
     * @param ordersId
     */
    void initData(Integer ordersId);

}
