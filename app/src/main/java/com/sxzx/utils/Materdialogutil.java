package com.sxzx.utils;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by Administrator
 * on 2016/11/2.
 */

public class Materdialogutil {
    public static void ShowAutoDissDialog(Context mcontext){
        MaterialDialog mDialog = new MaterialDialog.Builder(mcontext)
                .content("加入书架成功")
                .canceledOnTouchOutside(true)
                .show();


    }


}
