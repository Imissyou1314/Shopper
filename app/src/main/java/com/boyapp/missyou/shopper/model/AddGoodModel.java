package com.boyapp.missyou.shopper.model;

/**
 * Created by MissYou on 2016/12/13.
 */
public interface AddGoodModel extends BaseModel {

    /**
     * 添加商品
     * @param name
     * @param number
     * @param defNumber
     * @param desc
     * @param shopId
     * @param price
     */
    void addGoods(String name,
                  String number,
                  String defNumber,
                  String desc,
                  int shopId,
                  String price);

}
