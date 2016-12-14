package com.boyapp.missyou.shopper.model;

import com.boyapp.missyou.shopper.entity.Goods;

/**
 * Created by MissYou on 2016/12/13.
 */
public interface GoodModel extends BaseModel {

    /**
     * 删除商品
     * @param goodsId
     */
    void deleteGoods(int goodsId);

    /**
     * 上下架商品
     * @param goodsId
     * @param status
     */
    void checkGoods(int goodsId , Boolean status);

    /**
     * 更新商品
     * @param goods
     */
    void updateGoods(Goods goods);
}
