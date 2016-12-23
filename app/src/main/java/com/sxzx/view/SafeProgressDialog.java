package com.sxzx.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Administrator
 * on 2016/10/18.
 */

public class SafeProgressDialog extends ProgressDialog{
    Activity myParentActivity;

    public SafeProgressDialog(Context context) {
        super(context);
        myParentActivity = (Activity) context;
    }
    @Override
    public void show() {

        if (myParentActivity != null && !myParentActivity.isFinishing()) {

            super.show();
        }
    }

    @Override
    public void dismiss() {

        if (myParentActivity != null && !myParentActivity.isFinishing()) {

            super.dismiss();
        }
    }
}
