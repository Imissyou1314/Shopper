package com.boyapp.missyou.shopper.presenter;

import com.boyapp.missyou.shopper.view.BaseView;
/**
 * Created by MissYou on 2016/12/12.
 */

public interface OrdersPresenter<T extends BaseView> extends BasePresenter {

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 配送
     * @param ordersId
     */
    void sendOrders(Integer ordersId);

    /**
     * 拒绝接单
     * @param ordersId
     */
    void refuseOrders(Integer ordersId);

    /**
     * 接单
     * @param ordersId
     */
    void receOrders(Integer ordersId);
}
