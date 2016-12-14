package com.boyapp.missyou.shopper.model.impl;

import com.boyapp.missyou.shopper.entity.Goods;
import com.boyapp.missyou.shopper.model.GoodModel;
import com.boyapp.missyou.shopper.presenter.GoodsPresenter;
import com.boyapp.missyou.shopper.presenter.impl.GoodsPresenterImpl;
import com.boyapp.missyou.shopper.utils.Comment;
import com.boyapp.missyou.shopper.utils.RxVolleyUtils;
import com.kymjs.rxvolley.client.HttpParams;

/**
 * Created by MissYou on 2016/12/13.
 */
public class GoodModelImpl implements GoodModel {

    private GoodsPresenter mGoodsPresenter;

    public GoodModelImpl(GoodsPresenter goodsPresenter) {
        this.mGoodsPresenter = goodsPresenter;
    }

    @Override
    public void deleteGoods(int goodsId) {
        HttpParams params = new HttpParams();
        params.put("goodsId", goodsId);
        mGoodsPresenter.setSuccessView(RxVolleyUtils.getInstance().post(Comment.BASE_URL + Comment.GOOD_DELETE + goodsId, params));
    }

    @Override
    public void checkGoods(int goodsId, Boolean status) {
        String url = null;
        if (status) {
            url = Comment.BASE_URL + Comment.GOOD_ON_SHELVES + goodsId;
        } else {
           url = Comment.BASE_URL + Comment.GOOD_OFF_SHELVES + goodsId;
        }
        HttpParams params = new HttpParams();
        params.put("goodId", goodsId);

        mGoodsPresenter.setSuccessView(RxVolleyUtils.getInstance().post(url, params));

    }

    @Override
    public void updateGoods(Goods goods) {
        HttpParams params = new HttpParams();
        params.put("name", goods.getName());
        params.put("defNumber", goods.getDefNumber());
        params.put("shopId", goods.getShopId() + "");
        params.put("number", goods.getNumber());
        params.put("introduce",goods.getIntroduce());
        params.put("classification", 1);

        mGoodsPresenter
                .setSuccessView(RxVolleyUtils
                .getInstance()
                .post(Comment.BASE_URL + Comment.GOODS_UPDATE, params));
    }
}
