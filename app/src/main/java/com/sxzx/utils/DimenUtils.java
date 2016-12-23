package com.sxzx.utils;

import android.util.TypedValue;

import com.koolearn.android.kooreader.KooReaderApplication;

/**
 * Created by mdw on 2016/4/20.
 */
public class DimenUtils {


    /**
     * dp 转 px
     * @param dp
     * @return
     */
    public static int dp2px(int dp){
        int  px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, KooReaderApplication.getAppContext().getResources().getDisplayMetrics());

        return px;
        /*final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);*/
    }
}
