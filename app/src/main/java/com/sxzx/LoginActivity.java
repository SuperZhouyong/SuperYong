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
import android.widget.ImageView;
import android.widget.TextView;

import com.koolearn.android.kooreader.KooReaderApplication;
import com.koolearn.klibrary.ui.android.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.sxzx.base.ConfigKey.AppConfig;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.Utils.GsonUtil;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.base.baseActivity.AppManager;
import com.sxzx.base.baseActivity.BaseActivity;
import com.sxzx.base.bean.LogSuccessPhoto;
import com.sxzx.base.bean.Loginbean;
import com.sxzx.base.bean.ThreeLoginbean;
import com.sxzx.utils.SPUtils;
import com.sxzx.utils.StringUtil;
import com.sxzx.view.RoundImageView;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import okhttp3.Call;
import okhttp3.Response;

/**
 *
 */
public class LoginActivity extends BaseActivity implements PlatformActionListener, Handler.Callback {
    @Bind(R.id.iv)
    ImageView iv;
    @Bind(R.id.bt_register)
    Button btRegister;

    @Bind(R.id.imageButton)
    RoundImageView imageButton;
    @Bind(R.id.edit_phone)
    EditText etPhone;
    @Bind(R.id.editText_password)
    EditText etPassword;
    @Bind(R.id.bt_login)
    Button btLogin;
    @Bind(R.id.roundImageView)
    RoundImageView roundImageView;
    @Bind(R.id.roundImageView2)
    RoundImageView roundImageView2;
    @Bind(R.id.textView6)
    TextView textView6;
    private String TAG = "LoginActivity";
    private SPUtils mSPUtils;
    /*
    * shareSDK 一些列东西
    * */
    private Handler handler;

    private static final int MSG_AUTH_CANCEL = 2;
    private static final int MSG_AUTH_ERROR = 3;
    private static final int MSG_AUTH_COMPLETE = 4;

    private Platform mPlatform;
    // 电话，验证码，密码
    private String phone, password;

    @OnClick(R.id.imageButton)
    public void WeiBoLogin(View v) {
        Platform sina = ShareSDK.getPlatform(SinaWeibo.NAME);
        authorize(sina);
    }

    @OnClick(R.id.roundImageView)
    public void QQLogin(View v) {
        Platform qzone = ShareSDK.getPlatform(QZone.NAME);
        authorize(qzone);

    }

    @OnClick(R.id.roundImageView2)
    public void WeiChat(View v) {
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        authorize(wechat);
    }

    //执行授权,获取用户信息
    //文档：http://wiki.mob.com/Android_%E8%8E%B7%E5%8F%96%E7%94%A8%E6%88%B7%E8%B5%84%E6%96%99
    private void authorize(Platform plat) {
        LogUtils.i(TAG,"...."+plat);
        if (plat == null) {
            //popupOthers();
            return;
        }
        plat.setPlatformActionListener(this);
        //关闭SSO授权
//         plat.SSOSetting(true);
        plat.showUser(null);
    }

    /*R.id.
    * 邓重声明 这个ui名字不是我写 我也不撒算改了  麻烦 取名字取得真菜
    * */
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.textView6)
    public void forgetpassword(View v) {
        ResetPasswordActivity.startAction(this);
    }

    @Override
    public void initView() {
        mSPUtils = new SPUtils(getApplicationContext(), AppConfig.spName);
        ShareSDK.initSDK(this);
        handler = new Handler(this);

    }

    /*
    * 注册
    * */
    @OnClick(R.id.bt_register)
    public void registerUser(View v) {
        Verify1Activity.startAction(this);
    }
    /*
    * 登陆
    * */
    @OnClick(R.id.bt_login)
    public void loginUser(View v) {

        phone = etPhone.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        if (StringUtil.isEmpty(phone)) {
            showShortToast("手机号不能为空");
            return;
        }
        if (StringUtil.isEmpty(password)) {
            showShortToast("密码不能为空");
            return;
        }
        startProgressDialog("登陆中...");
        OkGo.post(Urls.URL_CONSTANT + Urls.URL_Login)
                .params("tel", phone)
                .params("password", password)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        final Loginbean mLogin = GsonUtil.GsonToBean(s, Loginbean.class);
                        if (mLogin.getStatus().trim().contains("success")) {
                            if (mLogin.getList().get(0).getResult().contains("ok")) {
                                mSPUtils.clear();
                                KooReaderApplication.myuserinfo.setUserId(String.valueOf(mLogin.getList().get(0).getUser_id()));
                                mSPUtils.putString(AppConfig.spUserId, String.valueOf(mLogin.getList().get(0).getUser_id()).trim());
                                OkGo.post(Urls.URL_CONSTANT+Urls.URl_Photo_Name)
                                        .params("user_id",KooReaderApplication.myuserinfo.getUserId())
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onSuccess(String s, Call call, Response response) {
                                                if(s.contains("success")){
                                                    LogSuccessPhoto mPhotoName = GsonUtil.GsonToBean(s,LogSuccessPhoto.class);
                                                    mSPUtils.putString(AppConfig.spName,mPhotoName.getList().get(0).getNickname());
                                                    mSPUtils.putString(AppConfig.spUserIcon,mPhotoName.getList().get(0).getAvatar());
                                                    mSPUtils.putString(AppConfig.spUserType,mPhotoName.getList().get(0).getType());
                                                }
                                            }

                                            @Override
                                            public void onAfter(@Nullable String s, @Nullable Exception e) {
                                                super.onAfter(s, e);
                                                AppManager.getAppManager().finishAllActivity();
                                                MainActivity.startAction(LoginActivity.this);
                                                showShortToast("登陆，跳转中..."+String.valueOf(mLogin.getList().get(0).getUser_id()));
                                            }
                                        });






                            }else {
                                showShortToast("登陆失败");
                            }
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        showShortToast("请检查网络...");
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        stopProgressDialog();
                    }
                });

    }

    /*
    *   showShortToast("登陆，跳转中...");
                                    String user_id = mjson.getString("user_id");
                                    LogUtils.i(TAG, s);
                                    KooReaderApplication.myuserinfo.setUserId(user_id);
                                    AppManager.getAppManager().finishAllActivity();
                                    MainActivity.startAction(LoginActivity.this);
    *
    * */
   /* @OnClick(R.id.textView)
    public void youkelogin(View v) {
        finish();
    }*/

    // 点击事件完毕之后隐藏 键盘
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }


    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
        if (action == Platform.ACTION_USER_INFOR) {
            Message msg = new Message();
            msg.what = MSG_AUTH_COMPLETE;
            msg.obj = new Object[]{platform.getName(), res};
            handler.sendMessage(msg);
        }
    }

    @Override
    public void onError(Platform platform, int action, Throwable t) {
        if (action == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_ERROR);
        }
        LogUtils.i(TAG,"OnerrOr" );
        t.printStackTrace();
    }

    @Override
    public void onCancel(Platform platform, int action) {
        if (action == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_CANCEL);
        }
    }

    @SuppressWarnings("unchecked")
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_AUTH_CANCEL: {
                //取消授权
                showShortToast(R.string.auth_cancel);
            }
            break;
            case MSG_AUTH_ERROR: {
                //授权失败
                showShortToast(R.string.auth_error);
            }
            break;
            case MSG_AUTH_COMPLETE: {
                //授权成功
                showShortToast(R.string.auth_complete);
                Object[] objs = (Object[]) msg.obj;
                String platform = (String) objs[0];
                HashMap<String, Object> res = (HashMap<String, Object>) objs[1];
                doLogined(platform);
            }
            break;

        }
        return false;
    }


    private void doLogined(String platform) {
        // TODO Auto-generated method stub
        mPlatform = ShareSDK.getPlatform(platform);
        //获取数平台数据DB
        String gender = "";
        if (platform != null) {
            //Sex
            gender = mPlatform.getDb().getUserGender();
            if (gender.equals("m")) {
                //  userInfo.setUserGender(UserInfo.Gender.BOY);
                gender = "男";
            } else {
                //userInfo.setUserGender(UserInfo.Gender.GIRL);
                gender = "女";
            }
            //type
            String typeName = mPlatform.getName();
            //token
            String token = mPlatform.getDb().getToken();
            //Avatar
            String usericon = mPlatform.getDb().getUserIcon();
            //openid
            final String userId = mPlatform.getDb().getUserId();
            //Nickname
            String userName = mPlatform.getDb().getUserName();
                LogUtils.i("QQThreed",typeName+"    "+token+"    "+usericon+"    "+userId+"    "+userName+"    ");
            OkGo.post(Urls.URL_CONSTANT + Urls.URL_ThreeLogin)
                    .params("type", typeName)
                    .params("user_id", userId)
                    .params("token", token)
                    .params("nickname", userName)
                    .params("avatar", usericon)
                    .params("sex", gender)
                    .cacheMode(CacheMode.NO_CACHE)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            LogUtils.i(TAG,"授权登陆成功"+s);
                            ThreeLoginbean mThreeLog = GsonUtil.GsonToBean(s, ThreeLoginbean.class);
                            if (mThreeLog.getStatus().contains("success")) {
                                ThreeLoginbean.ListBean mlistBean = mThreeLog.getList().get(0);
                                //三方登录成功保存本地的用户信息
                                if (mlistBean.getResult().contains("ok")) {
                                    mSPUtils.clear();
                                    //t头像
                                    KooReaderApplication.myuserinfo.setUserIcon(mlistBean.getAvatar());
                                    mSPUtils.putString(AppConfig.spUserIcon, mlistBean.getAvatar());
                                    //名称
                                    KooReaderApplication.myuserinfo.setNickname(mlistBean.getNickname());
                                    mSPUtils.putString(AppConfig.spUserName, mlistBean.getNickname());
                                    //user_id
                                    KooReaderApplication.myuserinfo.setUserId(mlistBean.getUser_id());
                                    mSPUtils.putString(AppConfig.spUserId, mlistBean.getUser_id());
                                    //type
                                    KooReaderApplication.myuserinfo.setType(mlistBean.getQqBinding());
                                    mSPUtils.putString(AppConfig.spUserType, mlistBean.getQqBinding());
                                    //sex
                                    KooReaderApplication.myuserinfo.setSex(mlistBean.getSex());
                                    mSPUtils.putString(AppConfig.spUserSex, mlistBean.getSex());
                                    //
                                    AppManager.getAppManager().finishAllActivity();
                                    MainActivity.startAction(LoginActivity.this);
                                }
                            }
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            showShortToast(mPlatform.getName() + "登陆失败");
                        }
                    });

        }


    }
}


