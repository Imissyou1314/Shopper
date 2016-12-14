package com.boyapp.missyou.shopper.presenter;

import com.boyapp.missyou.shopper.view.BaseView;

/**
 * Created by MissYou on 2016/12/13.
 */
public interface AddGoodsPresenter<T extends BaseView> extends BasePresenter{

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
