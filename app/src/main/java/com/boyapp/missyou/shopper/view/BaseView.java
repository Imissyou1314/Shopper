package com.boyapp.missyou.shopper.view;

/**
 * Created by MissYou on 2016/12/12.
 */

public interface BaseView<T> {
    /**
     * 提示接口接受数据异常
     * @param errorMsg 错误信息
     */
    void setErrorView(String errorMsg);

    /**
     * 提示操作成功的接口
     * @param successMsg
     */
    void setSuccessView(String successMsg);

    /**
     * 设置
     * @param t
     */
//    void setPresenter(T t);

//    /**
//     * 操作成功
//     * @param t
//     */
//    void onSuccess(ResultBean resultBean);
//
//    void onSuccess(T t);
//
//    /**
//     * 操作成功
//     * @param lists
//     */
//    void onSuccess(List<T> lists);
//
//    /**
//     * 操作失败
//     * @param errorMsg
//     */
//    void Failer(String errorMsg);
//
//    /**
//     * 操作失败
//     * @param resultBase
//     */
//    void Failer(ResultBean resultBase);
}
