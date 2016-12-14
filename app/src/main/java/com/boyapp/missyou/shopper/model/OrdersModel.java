package com.boyapp.missyou.shopper.model;

/**
 * Created by MissYou on 2016/12/12.
 */
public interface OrdersModel<T>  extends BaseModel<T>{

    /**
     * 初始数据
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
