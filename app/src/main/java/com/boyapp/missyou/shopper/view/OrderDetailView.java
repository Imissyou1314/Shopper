package com.boyapp.missyou.shopper.view;

import com.boyapp.missyou.shopper.entity.OrderDetail;
import com.boyapp.missyou.shopper.view.BaseView;

import java.util.List;

/**
 * Created by MissYou on 2016/12/12.
 */
public interface OrderDetailView extends BaseView{
    /**
     * 设置初始化页面
     * @param orderDetailList
     */
    void setInitView(List<OrderDetail> orderDetailList);
}
