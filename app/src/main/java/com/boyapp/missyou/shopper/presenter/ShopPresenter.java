package com.boyapp.missyou.shopper.presenter;

import com.boyapp.missyou.shopper.view.BaseView;

/**
 * Created by MissYou on 2016/12/12.
 */
public interface ShopPresenter<T extends BaseView> extends BasePresenter {

    /**
     * 获取商店的所有商品
     * @param shopId
     */
    void initData(Long shopId);

}
