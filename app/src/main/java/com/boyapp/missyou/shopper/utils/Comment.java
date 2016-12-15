package com.boyapp.missyou.shopper.utils;

import com.boyapp.missyou.shopper.entity.User;

/**
 * Created by MissYou on 2016/12/12.
 */


public class Comment {

    public static User user;
    //TODO
    public static Integer TestShopId;

    /**
     * 服务器地址
     */
//    public static final String BASE_URL = "http://172.16.13.28:9001";
    
    public static final String IMAGE_URL = "/imageware/";
        public static final String BASE_URL = "http://192.168.31.57:9001/";
    //商家未浏览订单
    public static final String Order_Shop_unWathc = "/orders/shop/unwatch/";
    //商家接单
    public static final String ORDER_ACCEPT = "/orders/accept/";
    public static final String ORDER_REFUE = "/orders/refuse/";
    public static final String ORDER_DELIVER = "/orders/deliver/";
    public static final String ORDER_INFO = "/orders/ordersInfo/";

    //商品接口
    public static final String GOOD_ADD ="/goods/add" ;
    public static final String GOOD_DELETE = "/goods/delete/";
    public static final String GOOD_OFF_SHELVES = "/goods/offShelves/";
    public static final String GOOD_ON_SHELVES = "/goods/onShelves/";
    public static final String GOODS_UPDATE = "/goods/update/";
    public static final String GOODS_GETSHOP = "/goods/shop/" ;

    public static long getUserId() {
        return null != user ? user.getId() : null;
    }

    private static String getUserName() {
        return null != user ? user.getUsername() : "";
    }

}
