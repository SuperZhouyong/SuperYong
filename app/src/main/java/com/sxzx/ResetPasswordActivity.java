package com.sxzx;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.koolearn.klibrary.ui.android.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.sxzx.base.ConfigKey.AppConfig;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.base.baseActivity.AppManager;
import com.sxzx.base.baseActivity.BaseActivity;
import com.sxzx.utils.SPUtils;
import com.sxzx.utils.StringUtil;
import com.sxzx.utils.TimeCount;

import butterknife.Bind;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator
 * on 2016/10/28.
 */

public class ResetPasswordActivity extends BaseActivity {


    @Bind(R.id.rest_Serach_return)
    RelativeLayout restSerachReturn;
    @Bind(R.id.rest_regist_phone_edit)
    EditText restRegistPhoneEdit;
    @Bind(R.id.rest_regist_authcode_edit)
    EditText restRegistAuthcodeEdit;
    @Bind(R.id.rest_regist_send_authcode_btn)
    TextView restRegistSendAuthcodeBtn;
    @Bind(R.id.rest_regist_password_edit)
    EditText restRegistPasswordEdit;
    @Bind(R.id.rest_regist_submit_btn)
    Button restRegistSubmitBtn;

    private String phone ,authCode,password ;
    private EventHandler eventHandler;
    private SPUtils sp ;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功   这时候给自己的服务器 提交密码
                    OkGo.post(Urls.URL_CONSTANT+Urls.URL_Resetpassword)
                            .params("user_name", phone)
                            .params("new_password", password)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    if(s.contains("success")){
                                        showShortToast("修改成功");
                                        AppManager.getAppManager().finishAllActivity();
                                        MainActivity.startAction(ResetPasswordActivity.this);
                                    }else if(s.contains("error")){
                                        showShortToast("修改失败");
                                    }
                                }
                            });
                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    //获取验证码成功
                    LogUtils.i("authCode",authCode+"-------"+data);

                        showShortToast("已发送短信验证码");
                        new TimeCount(60*1000,1000, restRegistSendAuthcodeBtn).start();

                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    //返回支持发送验证码的国家列表
                }
            }else{
                ((Throwable)data).printStackTrace();
            }
        }
    };
    @Override
    public int getLayoutId() {
        return R.layout.activity_verify2rest;
    }

    /*
    * 返还按钮
    * */
    @OnClick(R.id.rest_Serach_return)
    public void ResetPassReturn(View v) {
        finish();
    }

    @OnClick(R.id.rest_regist_send_authcode_btn)
    public void getrestauthcode(View v){
        phone = restRegistPhoneEdit.getText().toString().trim();
        if(StringUtil.isEmpty(phone)){
            restRegistPhoneEdit.setError("手机号为空");
            return;
        }
        SMSSDK.getVerificationCode("86",phone);

    }
    @OnClick(R.id.rest_regist_submit_btn)
    public void restsubmituserinfo(View v){
        authCode = restRegistAuthcodeEdit.getText().toString().trim();
        password = restRegistPasswordEdit.getText().toString().trim();
        if(StringUtil.isEmpty(authCode)){
            restRegistAuthcodeEdit.setError("验证码为空");
            return;
        }
        if(StringUtil.isEmpty(password)){
            restRegistPasswordEdit.setError("密码为空");
            return;
        }

        SMSSDK.submitVerificationCode("86",phone ,authCode);

    }
    @Override
    public void initView() {
        eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                super.afterEvent(event, result, data);
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                mHandler.sendMessage(msg);
            }
        };
        // 注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
        sp = new SPUtils(this, AppConfig.spName);
    }

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, ResetPasswordActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }

}
