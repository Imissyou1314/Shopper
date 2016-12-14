package com.boyapp.missyou.shopper.view;

import java.util.List;

/**
 * Created by MissYou on 2016/12/12.
 */
public interface MainView<T> extends BaseView<T> {

    /**
     * 设置回调页面
     * @param ordersList
     */
    void setInitView(List<T> ordersList);


}
