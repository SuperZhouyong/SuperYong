package com.sxzx.ZHYrecyclerview;

import java.util.Collection;

/**
 * Created by Administrator
 * on 2016/10/21.
 */

public class CollectionUtils {
    /**
     * 判断集合是否为null或者0个元素
     *
     * @param c
     * @return
     */
    public static boolean isNullOrEmpty(Collection c) {
        if (null == c || c.isEmpty()) {
            return true;
        }
        return false;
    }
}
