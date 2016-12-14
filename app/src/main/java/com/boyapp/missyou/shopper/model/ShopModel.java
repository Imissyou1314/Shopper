package com.boyapp.missyou.shopper.model;

/**
 * Created by MissYou on 2016/12/12.
 */
public interface ShopModel extends BaseModel{
    /**
     * 获取商店的所有商品
     * @param shopId
     */
    void initData(Long shopId);
}
