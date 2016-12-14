package com.boyapp.missyou.shopper.view;


import com.boyapp.missyou.shopper.entity.Goods;

import java.util.List;

/**
 * Created by MissYou on 2016/12/12.
 */
public interface ShopView extends BaseView{

    /**
     * 回调信息
     * @param goodsList
     */
    void setInitView(List<Goods> goodsList);
}
