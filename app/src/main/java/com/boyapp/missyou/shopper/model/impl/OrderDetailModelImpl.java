package com.boyapp.missyou.shopper.model.impl;

import com.boyapp.missyou.shopper.model.OrderDetailModel;
import com.boyapp.missyou.shopper.presenter.BasePresenter;
import com.boyapp.missyou.shopper.presenter.OrderDetailPresenter;
import com.boyapp.missyou.shopper.utils.Comment;
import com.boyapp.missyou.shopper.utils.RxVolleyUtils;

/**
 * Created by MissYou on 2016/12/12.
 */

public class OrderDetailModelImpl implements OrderDetailModel {

    private OrderDetailPresenter mOrderDetaliPresenter;

    public OrderDetailModelImpl(BasePresenter presenter) {
        this.mOrderDetaliPresenter = (OrderDetailPresenter) presenter;
    }

    @Override
    public void initData(Integer ordersId) {
        mOrderDetaliPresenter
                .setInitView(RxVolleyUtils
                        .getInstance()
                        .get(Comment.BASE_URL + Comment.ORDER_INFO + ordersId));


    }
}
