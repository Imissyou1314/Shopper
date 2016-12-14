package com.boyapp.missyou.shopper.model.impl;

import com.boyapp.missyou.shopper.model.OrdersModel;
import com.boyapp.missyou.shopper.presenter.OrdersPresenter;

import com.boyapp.missyou.shopper.utils.Comment;
import com.boyapp.missyou.shopper.utils.RxVolleyUtils;
import com.kymjs.rxvolley.client.HttpParams;

/**
 * Created by MissYou on 2016/12/12.
 */
public class OrdersModelImpl implements OrdersModel {

    private OrdersPresenter ordersPresenter;

    public OrdersModelImpl(OrdersPresenter ordersPresenter) {
        this.ordersPresenter = ordersPresenter;
    }

    @Override
    public void initData() {
        ordersPresenter.setInitView(RxVolleyUtils.getInstance().get(Comment.BASE_URL +
                Comment.Order_Shop_unWathc + "1"));
    }

    @Override
    public void sendOrders(Integer ordersId) {
        HttpParams params = new HttpParams();
        params.put("ordersId", ordersId);
        ordersPresenter.setSuccessView(
                RxVolleyUtils.getInstance()
                .post(Comment.BASE_URL + Comment.ORDER_DELIVER + ordersId, params));
    }

    @Override
    public void refuseOrders(Integer ordersId) {
        HttpParams params = new HttpParams();
        params.put("ordersId", ordersId);
        ordersPresenter.setSuccessView(
                RxVolleyUtils.getInstance()
                        .post(Comment.BASE_URL + Comment.ORDER_REFUE + ordersId, params));
    }

    @Override
    public void receOrders(Integer ordersId) {
        HttpParams params = new HttpParams();
        params.put("ordersId", ordersId);
        ordersPresenter.setSuccessView(
                RxVolleyUtils.getInstance()
                        .post(Comment.BASE_URL + Comment.ORDER_ACCEPT + ordersId, params));
    }

}
