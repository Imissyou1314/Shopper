package com.boyapp.missyou.shopper.model.impl;

import com.boyapp.missyou.shopper.model.ShopModel;
import com.boyapp.missyou.shopper.presenter.ShopPresenter;
import com.boyapp.missyou.shopper.presenter.impl.ShopPresenterImpl;
import com.boyapp.missyou.shopper.utils.Comment;
import com.boyapp.missyou.shopper.utils.RxVolleyUtils;

/**
 * Created by MissYou on 2016/12/12.
 */
public class ShopModelImpl implements ShopModel {

    private ShopPresenter mShopPresenter;

    public ShopModelImpl(ShopPresenterImpl shopPresenter) {
        this.mShopPresenter = shopPresenter;
    }

    @Override
    public void initData(Long shopId) {
        mShopPresenter
                .setInitView(RxVolleyUtils.getInstance()
                .get(Comment.BASE_URL + Comment.GOODS_GETSHOP + shopId));
    }
}
