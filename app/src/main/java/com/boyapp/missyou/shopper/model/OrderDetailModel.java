package com.boyapp.missyou.shopper.model;

/**
 * Created by MissYou on 2016/12/12.
 */

public interface OrderDetailModel<T> extends BaseModel<T> {

    /**
     * 获取订详情
     * @param ordersId
     */
    void initData(Integer ordersId);
}
