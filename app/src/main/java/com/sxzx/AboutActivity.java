package com.sxzx;

import android.view.View;
import android.widget.RelativeLayout;

import com.koolearn.klibrary.ui.android.R;
import com.sxzx.base.baseActivity.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator
 * on 2016/11/25.
 */

public class AboutActivity extends BaseActivity {
    @Bind(R.id.setting_activity_about_return)
    RelativeLayout settingActivityAboutReturn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }
    @OnClick(R.id.setting_activity_about_return)
    public void about_return(View v){
        finish();
    }
    @Override
    public void initView() {

    }


}
