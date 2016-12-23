package com.sxzx;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.koolearn.klibrary.ui.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 乐林荫 on 2016-09-07.
 */
public class MenuActivity extends Activity {
    private ViewPager mViewPager;
    private List<View> mDatas;
    private MyPagerAdapter mAdapter;
    private Button bt_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        bt_close = (Button) findViewById(R.id.close);
        bt_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        mViewPager.setPageMargin((int)getResources().getDimensionPixelOffset(R.dimen.ui_5_dip));
        initData();
        mAdapter = new MyPagerAdapter();
        mViewPager.setAdapter(mAdapter);

    }

    protected void initData() {
        mDatas = new ArrayList<View>();
        mDatas.add(View.inflate(MenuActivity.this, R.layout.activity_menu_item, null));
        mDatas.add(View.inflate(MenuActivity.this, R.layout.activity_menu_item, null));
        mDatas.add(View.inflate(MenuActivity.this, R.layout.activity_menu_item, null));
        mDatas.add(View.inflate(MenuActivity.this, R.layout.activity_menu_item, null));
        mDatas.add(View.inflate(MenuActivity.this, R.layout.activity_menu_item, null));
        mDatas.add(View.inflate(MenuActivity.this, R.layout.activity_menu_item, null));
    }

    class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;//官方提示这样写
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mDatas.get(position));//删除页卡
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mDatas.get(position));//添加页卡
            return mDatas.get(position);
        }
    }


}