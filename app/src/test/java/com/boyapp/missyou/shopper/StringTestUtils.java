package com.boyapp.missyou.shopper;

import com.boyapp.missyou.shopper.utils.StringUtils;

import org.junit.Test;

/**
 * Created by MissYou on 2016/12/13.
 */

public class StringTestUtils {

    @Test
    public void TestStrUtils() {
        String name = "missyou";
        if (StringUtils.checkString(name))
            System.out.print("成功");
        else
            System.out.print("烧饼");
    }
}
