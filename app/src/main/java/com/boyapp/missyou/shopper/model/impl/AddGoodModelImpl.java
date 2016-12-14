package com.boyapp.missyou.shopper.model.impl;

import com.boyapp.missyou.shopper.model.AddGoodModel;
import com.boyapp.missyou.shopper.presenter.AddGoodsPresenter;
import com.boyapp.missyou.shopper.utils.Comment;
import com.boyapp.missyou.shopper.utils.RxVolleyUtils;
import com.kymjs.rxvolley.client.HttpParams;

/**
 * Created by MissYou on 2016/12/13.
 */
public class AddGoodModelImpl implements AddGoodModel {

    private AddGoodsPresenter mAddGoodsPresenter;

    public AddGoodModelImpl(AddGoodsPresenter addGoodsPresenter) {
        this.mAddGoodsPresenter = addGoodsPresenter;
    }

    @Override
    public void addGoods(String name, String number, String defNumber, String desc, int shopId, String price) {
        HttpParams params = new HttpParams();
        params.put("name", name);
        params.put("defNumber", defNumber);
        params.put("shopId", shopId );
        params.put("number", number);
        params.put("introduce",desc);
        params.put("classification", 1);

        mAddGoodsPresenter
                .setSuccessView(RxVolleyUtils
                        .getInstance()
                        .post(Comment.BASE_URL + Comment.GOOD_ADD, params));
    }
}
