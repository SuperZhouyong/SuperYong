package com.sxzx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.koolearn.klibrary.ui.android.R;

import com.sxzx.utils.PrefUtils;

/**
 * 闪屏界面
 * Created by 乐林荫 on 2016-09-05.
 */
public class SplashActivity extends Activity{
    private RelativeLayout rlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);


        // 渐变动画
        AlphaAnimation animAlpha = new AlphaAnimation(1, 1);
        animAlpha.setDuration(2000);// 动画时间
        animAlpha.setFillAfter(true);// 保持动画结束状态



        // 启动动画
        rlRoot.startAnimation(animAlpha);

        animAlpha.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 动画结束,跳转页面
                // 如果是第一次进入, 跳新手引导
                // 否则跳主页面
                boolean isFirstEnter = PrefUtils.getBoolean(
                        SplashActivity.this, "is_first_enter", true);

                Intent intent;
                if (isFirstEnter) {
                    // 新手引导
                    intent = new Intent(getApplicationContext(),
                            GuideActivity.class);
                } else {
                    // 主页面
                    intent = new Intent(getApplicationContext(),
                            MainActivity.class);
                }

                startActivity(intent);

                finish();// 结束当前页面
            }
        });
    }
}
