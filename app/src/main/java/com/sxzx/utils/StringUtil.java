package com.sxzx.utils;

/**
 * Created by Administrator
 * on 2016/10/24.
 */

public class StringUtil {


    /**
     * 判断字符串是否为null
     *
     * @param str 传入的字符串
     * @return 如果为null，则返回true，否则返回false；
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}