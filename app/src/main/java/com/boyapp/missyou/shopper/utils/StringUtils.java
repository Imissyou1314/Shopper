package com.boyapp.missyou.shopper.utils;

/**
 * Created by MissYou on 2016/12/13.
 * 字符串工具类
 */
public class StringUtils {

    /**
     * 检验Str不为空
     * @param str
     * @return
     */
    public static boolean checkString(String str) {
        return null != str
                && !str.equals("")
                && str.trim().length() >= 0
                && !str.equals(" ")? true : false;
    }
}
