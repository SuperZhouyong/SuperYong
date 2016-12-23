package com.sxzx.base.pager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.koolearn.android.kooreader.KooReaderApplication;
import com.koolearn.klibrary.ui.android.R;
import com.sxzx.InformationActivity;
import com.sxzx.LoginActivity;
import com.sxzx.NoteInfoActivity;
import com.sxzx.SettingActivity;
import com.sxzx.ZHYrecyclerview.BaseFragmentAdapter;
import com.sxzx.base.BasePager;
import com.sxzx.base.ConfigKey.AppConfig;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.base.pager.fragmentmybook.MyBookfragmentone;
import com.sxzx.base.pager.fragmentmybook.MyBookfragmenttwo;
import com.sxzx.utils.SPUtils;
import com.sxzx.utils.glideLoaderUtils;
import com.sxzx.view.RoundImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 我的书库
 *
 * @author Kevin
 * @date 2015-10-18
 */
public class MyBookPager extends BasePager {
    // 自己保存头像的路径

    private String TAG = "MyBookPager";
    @Bind(R.id.ib2)
    ImageView ib2;
    @Bind(R.id.tv)
    TextView tv;


    @Bind(R.id.To_SettingActivity)
    RoundImageView ToSettingActivity;

    @Bind(R.id.buyandRecord)
    RoundImageView buyandRecord;
    @Bind(R.id.mybkVP)
    ViewPager mybkVP;
    @Bind(R.id.mybptablayout)
    TabLayout mybptablayout;
    private SPUtils sp ;
    private String[] mybp = {"书架", "收藏",};

    // vp adapter
    private BaseFragmentAdapter mybpfragmentAdapter;
    /*
    * 书架和收藏的两个 fragment
    * */
    private List<Fragment> mNewsFragmentList = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.mybook_pager;
    }

    /*
    *
    * 进入设置界面
    * */
    @OnClick(R.id.To_SettingActivity)
    public void ToSettingActivity() {
        if (TextUtils.isEmpty(KooReaderApplication.myuserinfo.getUserId())) {
            showShortToast("请先登录");
        } else {
            startActivity(SettingActivity.class);
        }
    }

    /*
    *
    * 进入购买记录
    * */
    @OnClick(R.id.buyandRecord)
    public void buyandRecordonclick(View v) {
        if (TextUtils.isEmpty(KooReaderApplication.myuserinfo.getUserId())) {
            showShortToast("请先登录");
        } else {
            LogUtils.i(TAG, KooReaderApplication.myuserinfo.getUserId() + "---");
            // 进入到购买记录的书架
            startActivity(NoteInfoActivity.class);
            // startActivity(SettingActivity.class);
        }

    }

    @Override
    protected void initView() {
        sp = new SPUtils(getActivity(), AppConfig.spName);
        // 请求个人信息
        List<String> tabList = Arrays.asList(mybp);
        /*
        *  底部两个fragment
        * */
        mNewsFragmentList.add(new MyBookfragmentone());
        mNewsFragmentList.add(new MyBookfragmenttwo());
        mybpfragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), mNewsFragmentList, tabList);
        mybkVP.setAdapter(mybpfragmentAdapter);
        mybptablayout.setupWithViewPager(mybkVP);
        mybptablayout.setTabMode(TabLayout.MODE_FIXED);
        setPageChangeListener();

    }



    private void setPageChangeListener() {
        mybkVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
/*
*  登陆与否 此页的信息显示完毕  --  检头像和名字的信息是否完整
*
* */
    @Override
    public void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(KooReaderApplication.myuserinfo.getUserId())) {
            //未登录
            glideLoaderUtils.displayRoundint(getActivity(), ib2, R.mipmap.weilogin);
            tv.setText("请登录");
            mybkVP.setVisibility(View.INVISIBLE);

        } else {
            if (TextUtils.isEmpty(sp.getString(AppConfig.spUserIcon))) {
                glideLoaderUtils.displayRoundint(getActivity(), ib2, R.mipmap.logicon);
            } else {
                // 设置用户的头像
                glideLoaderUtils.displayRound(getActivity(), ib2, sp.getString(AppConfig.spUserIcon));
            }
            tv.setText(sp.getString(AppConfig.spUserName,"未设置"));
            mybkVP.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.ib2)
    public void StartToInfoActivity(View v) {
     LogUtils.i(TAG,KooReaderApplication.myuserinfo.getUserId()+"   ");
        if (TextUtils.isEmpty(KooReaderApplication.myuserinfo.getUserId())) {
            LoginActivity.startAction(getActivity());
        } else {
            InformationActivity.startAction(getActivity());
        }
    }


    /**
     * 请求权限
     */
    public void getUserPar() {
//        Log.d("测试--授权信息", ContextCompat.checkSelfPermission(this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE) + "");
//        Log.d("测试--已授权", PackageManager.PERMISSION_GRANTED + "");
//        Log.d("测试--未授权", PackageManager.PERMISSION_DENIED + "");
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LogUtils.i("PermissionsResult", "同意");
                } else {
                    LogUtils.i("PermissionsResult", "拒绝");
                }
                return;
            }
        }
    }


}
