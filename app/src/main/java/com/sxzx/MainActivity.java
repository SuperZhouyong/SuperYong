package com.sxzx;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.koolearn.klibrary.ui.android.R;
import com.sxzx.base.ConfigKey.AppConfig;
import com.sxzx.base.ConfigKey.AppConstant;
import com.sxzx.base.ConfigKey.UpdateKey;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.base.baseActivity.BaseActivity;
import com.sxzx.base.bean.TabEntity;
import com.sxzx.base.pager.LibraryPager;
import com.sxzx.base.pager.MyBookPager;
import com.sxzx.base.pager.NewsBookPager;
import com.sxzx.base.pager.ReadBookPager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.Bind;


/**
 * 主界面
 */
public class MainActivity extends BaseActivity {


    @Bind(R.id.fl_body)
    FrameLayout flBody;
    @Bind(R.id.tab_layout)
    CommonTabLayout tabLayout;
    private String TAG = "MainActivity";




    private String[] mTitles = {"新书推荐", "政协书库","委员读书","我的书库"};
    private int[] mIconUnselectIds = {
            R.mipmap.page11,R.mipmap.page22,R.mipmap.page33,R.mipmap.page44};
    private int[] mIconSelectIds = {
            R.mipmap.page1,R.mipmap.page2, R.mipmap.page3,R.mipmap.page4};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    // 新书阅读
    private NewsBookPager mNewsBookPager;
    // 政协书库
    private LibraryPager mLibraryPager;
    // 委员读书
    private ReadBookPager mReadBookPager;
    // 我的书库
    private MyBookPager mMyBookPager;

    private static int tabLayoutHeight;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment(savedInstanceState);
        tabLayout.measure(0, 0);
        tabLayoutHeight = tabLayout.getMeasuredHeight();
        LogUtils.i(TAG, tabLayoutHeight + "sss");
       /* mRxManager.on(AppConstant.MENU_SHOW_HIDE, new Action1<Boolean>() {
            @Override
            public void call(Boolean hideOrShow) {
                startAnimation(hideOrShow);
            }
        });*/
    }


    /**
     * 菜单显示隐藏动画
     *
     * @param showOrHide
     */
    private void startAnimation(boolean showOrHide) {
        final ViewGroup.LayoutParams layoutParams = tabLayout.getLayoutParams();
        ValueAnimator valueAnimator;
        ObjectAnimator alpha;
        if (!showOrHide) {
            valueAnimator = ValueAnimator.ofInt(tabLayoutHeight, 0);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 1, 0);
        } else {
            valueAnimator = ValueAnimator.ofInt(0, tabLayoutHeight);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 0, 1);
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.height = (int) valueAnimator.getAnimatedValue();
                tabLayout.setLayoutParams(layoutParams);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator, alpha);
        animatorSet.start();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        //此处填上在http://fir.im/注册账号后获得的API_TOKEN以及APP的应用ID
        UpdateKey.API_TOKEN = AppConfig.API_FIRE_TOKEN;
        UpdateKey.APP_ID = AppConfig.APP_FIRE_ID;
        //initFragment();
        initTab();
        copyBooks();
    }
    private void copyBooks() {
        new Thread() {
            @Override
            public void run() {
                copyFonts("fonts/W3.otf");
            }
        }.start();
    }
    /**
     * 字体拷贝
     */
    private void copyFonts(String fontName) {
        File destFile = new File(getFilesDir(), "W3.otf");
        LogUtils.i("字体",destFile.getAbsolutePath());
        if (destFile.exists()) {
            destFile.delete();
//            return;
        }

        FileOutputStream out = null;
        InputStream in = null;

        try {
            in = getAssets().open(fontName);
            out = new FileOutputStream(destFile);
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            LogUtils.i("字体","复制完成？？");
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTextSelectColor(getResources().getColor(R.color.tab_text_color));
        tabLayout.setTextUnselectColor(getResources().getColor(R.color.white));
        tabLayout.setBackgroundColor(getResources().getColor(R.color.indecttabstext));

        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }
            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    private void SwitchTo(int position) {
        LogUtils.i(TAG, "主页菜单position" + position);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            //首页
            case 0:
                transaction.show(mNewsBookPager);
                transaction.hide(mLibraryPager);
                transaction.hide(mReadBookPager);
                transaction.hide(mMyBookPager);
                transaction.commitAllowingStateLoss();
                break;
            //美女
            case 1:
                transaction.hide(mNewsBookPager);
                transaction.show(mLibraryPager);
                transaction.hide(mReadBookPager);
                transaction.hide(mMyBookPager);
                transaction.commitAllowingStateLoss();
                break;
            //视频
            case 2:
                transaction.hide(mNewsBookPager);
                transaction.hide(mLibraryPager);
                transaction.show(mReadBookPager);
                transaction.hide(mMyBookPager);
                transaction.commitAllowingStateLoss();
                break;
            //关注
            case 3:
                transaction.hide(mNewsBookPager);
                transaction.hide(mLibraryPager);
                transaction.hide(mReadBookPager);
                transaction.show(mMyBookPager);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }
/*
*  mNewsBookPager = (NewsBookPager) getSupportFragmentManager().findFragmentByTag("mNewsBookPager");
            mLibraryPager = (LibraryPager) getSupportFragmentManager().findFragmentByTag("mLibraryPager");
            mReadBookPager = (ReadBookPager) getSupportFragmentManager().findFragmentByTag("mReadBookPager");
            mMyBookPager = (MyBookPager) getSupportFragmentManager().findFragmentByTag("mMyBookPager");*/

    /**
     * 初始化fragment
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            mNewsBookPager = (NewsBookPager) getSupportFragmentManager().findFragmentByTag("mNewsBookPager");
            mLibraryPager = (LibraryPager) getSupportFragmentManager().findFragmentByTag("mLibraryPager");
            mReadBookPager = (ReadBookPager) getSupportFragmentManager().findFragmentByTag("mReadBookPager");
            mMyBookPager = (MyBookPager) getSupportFragmentManager().findFragmentByTag("mMyBookPager");
            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        } else {
            mNewsBookPager = new NewsBookPager();
            mLibraryPager = new LibraryPager();
            mReadBookPager = new ReadBookPager();
            mMyBookPager = new MyBookPager();
//R.id.fl_body, newsMainFragment, "newsMainFragment"
            transaction.add(R.id.fl_body, mNewsBookPager, "mNewsBookPager");
            transaction.add(R.id.fl_body, mLibraryPager, "mLibraryPager");
            transaction.add(R.id.fl_body, mReadBookPager, "mReadBookPager");
            transaction.add(R.id.fl_body, mMyBookPager, "mMyBookPager");
        }
        transaction.commit();
        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }


    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("确认退出吗？");

                builder.setTitle("提示");

                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        MainActivity.this.finish();
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

            return false;

        }

    }






    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.i(TAG, "onSaveInstanceState进来了1");
        if (tabLayout != null) {
            LogUtils.i(TAG, "onSaveInstanceState进来了2");
            outState.putInt(AppConstant.HOME_CURRENT_TAB_POSITION, tabLayout.getCurrentTab());
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

    }
}

