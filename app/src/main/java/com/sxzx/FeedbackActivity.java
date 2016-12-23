package com.sxzx;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.koolearn.klibrary.ui.android.R;
import com.sxzx.base.baseActivity.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator
 * on 2016/11/25.
 */

public class FeedbackActivity extends BaseActivity {
    @Bind(R.id.edit_feedback)
    EditText editFeedback;
    @Bind(R.id.feedback_button)
    Button feedbackButton;
    @Bind(R.id.setting_activity_feedback_return)
    RelativeLayout settingActivityFeedbackReturn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedbac;
    }

    @OnClick(R.id.feedback_button)
    public void feedbt(View v) {
        String trim = editFeedback.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)) {
            showShortToast("提交");
            new MaterialDialog.Builder(this)
                    .content("提交成功")
                    .contentGravity(GravityEnum.CENTER)
                    .canceledOnTouchOutside(true)
                    .show();
        }
    }

    @OnClick(R.id.setting_activity_feedback_return)
    public void feedbackReturn(View v) {
        finish();

    }

    @Override
    public void initView() {

    }


}
