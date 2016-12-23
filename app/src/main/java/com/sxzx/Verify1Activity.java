package com.sxzx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.koolearn.android.kooreader.KooReaderApplication;
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

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Call;
import okhttp3.Response;

/**
 *
 */
public class Verify1Activity extends BaseActivity {

    private String TAG = "Verify1Activity";
    // 电话号码
    @Bind(R.id.regist_phone_edit)
    EditText registPhoneEdit;
    // 验证码 填写的地方
    @Bind(R.id.regist_authcode_edit)
    EditText registAuthcodeEdit;
    // 请求验证码
    @Bind(R.id.regist_send_authcode_btn)
    Button registSendAuthcodeBtn;
    // 密码输入
    @Bind(R.id.regist_password_edit)
    EditText registPasswordEdit;
    // 注册的按钮
    @Bind(R.id.regist_submit_btn)
    Button registSubmitBtn;

    private SPUtils sp ;

    private String phone, authCode, password;
    private EventHandler eventHandler;
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
                    LogUtils.i(TAG, "验证码提交成功");
                    OkGo.post(Urls.URL_CONSTANT + Urls.URL_Regist_user)
                            .params("tel", phone)
                            .params("password", password)
                            .params("type","tel")
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {

                                    try {
                                        LogUtils.i(TAG, s);
                                        JSONObject mjson = new JSONObject(s);
                                        String state = mjson.getString("result");
                                        switch (Integer.valueOf(state)) {
                                            case 1:
                                                showShortToast("电话已存在，注册失败");
                                                break;
                                            case 2:
                                                showShortToast("注册失败，请稍后再试");
                                                break;
                                            case 3:
                                                showShortToast("注册成功，登陆中...");
                                                String user_id = mjson.getString("user_id");
                                                LogUtils.i(TAG, s);
                                                KooReaderApplication.myuserinfo.setUserId(user_id);
                                                sp.putString(AppConfig.spUserId,user_id);
                                                AppManager.getAppManager().finishAllActivity();
                                                MainActivity.startAction(Verify1Activity.this);
                                                break;
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    showShortToast("给自己服务器的上传用户信息失败 失败");
                                }

                                @Override
                                public void onAfter(@Nullable String s, @Nullable Exception e) {
                                    super.onAfter(s, e);
                                    stopProgressDialog();
                                }
                            });
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    showShortToast("已发送短信验证码");
                    new TimeCount(60 * 1000, 1000, registSendAuthcodeBtn).start();

                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                }
            } else {
                ((Throwable) data).printStackTrace();
                // 把错误展示看一下
                showShortToast(String.valueOf(data));
            }
        }
    };

    /*
    * 发送验证吗
    *
    * */
    @OnClick(R.id.regist_send_authcode_btn)
    public void RequestauthCode(View v) {

        phone = registPhoneEdit.getText().toString().trim();
        if (StringUtil.isEmpty(phone)) {
            registPhoneEdit.setError("手机号为空");
            return;
        }
        SMSSDK.getVerificationCode("86", phone);

    }

    /*
    * 提交验证吗
    *
    * */
    @OnClick(R.id.regist_submit_btn)
    public void RegistUserInfo(View v) {

        authCode = registAuthcodeEdit.getText().toString().trim();
        password = registPasswordEdit.getText().toString().trim();

        if (StringUtil.isEmpty(authCode)) {
            registAuthcodeEdit.setError("验证码为空");
            return;
        }
        if (StringUtil.isEmpty(password)) {
            registPasswordEdit.setError("密码为空");
            return;
        }
        LogUtils.i(TAG, phone + "    " + authCode + "   " + password);
        startProgressDialog("注册中...");
        SMSSDK.submitVerificationCode("86", phone, authCode);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_verify1;
    }
    @OnClick(R.id.SMS_return)
    public void SMSreturn(View v){

            finish();

    }
    @Override
    public void initView() {
        /*
        * 注册
        * */
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

    //隐藏状态栏
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }


/*
    *  // 显示密码
        mEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
    *
    * */
    /*
     隐藏密码
    mEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    *
    */
/*
* EventHandler eh=new EventHandler(){

            @Override
            public void afterEvent(int event, int result, Object data) {

               if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                //提交验证码成功
                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                //获取验证码成功
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                //返回支持发送验证码的国家列表
                }
              }else{
                 ((Throwable)data).printStackTrace();
          }
      }
   };
*
*
*
* */

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, Verify1Activity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }
}
