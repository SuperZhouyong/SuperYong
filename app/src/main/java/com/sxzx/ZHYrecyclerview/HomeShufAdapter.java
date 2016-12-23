package com.sxzx.ZHYrecyclerview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sxzx.base.Utils.LogUtils;
import com.sxzx.utils.DimenUtils;

import java.util.List;

/**
 * 首页轮播图
 * Created by mdw on 2016/4/20.
 */
public class HomeShufAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {
    //DimenUtils.dp2px(24) DimenUtils.dp2px(32)
    private String TAG = "HomeShufAdapter--" ;
    public static int sWidthPadding =DimenUtils.dp2px(32);

    public static int  sHeightPadding = DimenUtils.dp2px(48);

    private Context mContext ;

    private List<ImageView> mImageViewList;


    private OnPageSelectListener listener;

    public HomeShufAdapter(List<ImageView> mImageViewList, Context context) {

        this.mImageViewList = mImageViewList;
        // padding = DimenUtils.dp2px(40);
        mContext = context ;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(mImageViewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= mImageViewList.size();
       /* if (position<0) {
            position = mImageViewList.size() + position;
        }*/
        ImageView imageView = mImageViewList.get(position) ;
       /* ViewParent viewParent = imageView.getParent();
        if (viewParent!=null){
            ViewGroup parent = (ViewGroup)viewParent;
            parent.removeView(imageView);
        }*/
        container.addView(imageView);

        return imageView;
    }

    @Override
    public int getCount() {
        return mImageViewList == null ? 0 : mImageViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        LogUtils.i(TAG,"一个不进去");
        position %= mImageViewList.size();
        if (position<0) {
            position = mImageViewList.size() + position;
        }
        if (mImageViewList.size()>0&&position<mImageViewList.size()) {
            //当前手指触摸滑动的页面,从0页滑动到1页 offset越来越大，padding越来越大
            int outHeightPadding = (int) (positionOffset * sHeightPadding);
            int outWidthPadding = (int) (positionOffset * sWidthPadding);
            mImageViewList.get(position).setPadding(outWidthPadding, outHeightPadding*2, outWidthPadding, 0);
            Log.i(TAG,"outHeightPadding"+outHeightPadding+"---outWidthPadding"+outWidthPadding);
            if (position < mImageViewList.size() - 1) {
                int inWidthPadding = (int) ((1 - positionOffset) * sWidthPadding);
                int inHeightPadding = (int) ((1 - positionOffset) * sHeightPadding);
                mImageViewList.get(position + 1).setPadding(inWidthPadding,outHeightPadding*2 ,inWidthPadding, 0);
                Log.i(TAG,"inWidthPadding"+inWidthPadding+"---inHeightPadding"+inHeightPadding);
            }
        }

    }

    @Override
    public void onPageSelected(int position) {
        if (listener != null) {
            listener.select(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setOnPageSelectListener(OnPageSelectListener listener) {
        this.listener = listener;
    }


    public interface OnPageSelectListener {
        void select(int position);
    }
}
