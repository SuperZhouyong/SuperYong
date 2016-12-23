package com.sxzx;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.koolearn.android.kooreader.KooReaderApplication;
import com.koolearn.klibrary.ui.android.R;
import com.sxzx.base.ConfigKey.AppConfig;
import com.sxzx.base.baseActivity.AppManager;
import com.sxzx.base.baseActivity.BaseActivity;
import com.sxzx.utils.DataCleanManager;
import com.sxzx.utils.SPUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator
 * on 2016/10/26.
 */

public class SettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    /*   @Bind(R.id.sb_default)
       SwitchButton sbDefault;*/
    @Bind(R.id.relative_2)
    RelativeLayout relative2;
    @Bind(R.id.relative_3)
    RelativeLayout relative3;
    @Bind(R.id.relative_4)
    RelativeLayout relative4;
    @Bind(R.id.relative_5)
    RelativeLayout relative5;

    @Bind(R.id.setting_activity_return)
    RelativeLayout settingActivityReturn;
    @Bind(R.id.relative_6)
    RelativeLayout relative6;
    @Bind(R.id.relative_reset)
    RelativeLayout relativeReset;

    private SPUtils sp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @OnClick(R.id.setting_activity_return)
    public void returnSetting(View v) {
        finish();
    }

    @Override
    public void initView() {
        sp = new SPUtils(this, AppConfig.spName);
       /* sbDefault.setOnCheckedChangeListener(this);*/
    }

    @OnClick(R.id.relative_reset)
    public void relative_reset(View v){
        ResetPasswordActivity.startAction(this);
    }
    /*
    * 网站链接
    * */
    @OnClick(R.id.relative_2)
    public void relative_2click(View v) {
        // 进入网址
        String url = "http://www.chinawenshi.com.cn";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    /*
    * 关于我们
    * */
    @OnClick(R.id.relative_6)
    public void relative_6click(View v) {
        startActivity(AboutActivity.class);

    }

    /*
    * 清楚缓存
    * */
    @OnClick(R.id.relative_3)
    public void relative_3click(View v) {
        /*
        * 根据文件大小 提示删除了多少的数据
        * */
        DataCleanManager.cleanInternalCache(this);
        long l = getCacheDir().length() / (1024);
        if (l > 1024) {
            showShortToast("一共清除缓存" + String.valueOf(l / 1024) + "M");
        } else {
            showShortToast("一共清除缓存" + String.valueOf(l) + "k");
        }


    }

    /*
    * 软件反馈
    * */
    @OnClick(R.id.relative_4)
    public void relative_4click(View v) {
        startActivity(FeedbackActivity.class);

    }

    /*
    * 退出登陆
    * */
    @OnClick(R.id.relative_5)
    public void relative_5click(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认退出登陆?");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                KooReaderApplication.myuserinfo.setUserId(null);
                sp.clear();
                AppManager.getAppManager().finishAllActivity();
                MainActivity.startAction(SettingActivity.this);
                showShortToast("退出登录");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();


    }

    /*
    * switchvutton 状态改变
    * */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            showShortToast("每日推送打开");
        } else {
            showShortToast("推送已关闭");
        }
    }


}


/*
*  startProgressDialog("加载中网站链接中");
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            stopProgressDialog();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
*
* */
