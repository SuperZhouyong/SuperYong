package com.sxzx.base.cardadapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.koolearn.klibrary.ui.android.R;
import com.sxzx.InfoDetailsActivity;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.base.bean.ActivityInfoUi;
import com.sxzx.utils.glideLoaderUtils;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter, ViewPager.OnPageChangeListener {

    private List<CardView> mViews;
    private List<String> mData;
    private float mBaseElevation;
    private List<ActivityInfoUi.ListBean> mList ;
    private  Context mContext ;
    private String InfoUrl = Urls.URL_CONSTANT+Urls.URL_Activity_message;
    public CardPagerAdapter(Context context ,List<ActivityInfoUi.ListBean> mlistbean) {
        mList = mlistbean ;
        mContext = context ;

        mData = new ArrayList<>();
        mViews = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            mData.add("");
            mViews.add(null);
        }
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.adapter, container, false);
        container.addView(view);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);
        Button morebutton = (Button) view.findViewById(R.id.adapter_more);
        ImageView img_info = (ImageView) view.findViewById(R.id.image_uiinfo);
        TextView textviewTitle = (TextView) view.findViewById(R.id.textView9);
        TextView textviewtime = (TextView) view.findViewById(R.id.adapter_shijian);
        TextView textviewaddress = (TextView) view.findViewById(R.id.adapter_dizhi);
//        TextView textViewtime2 = (TextView) view.findViewById(R.id.adapter_shijian2);

        textviewTitle.setText(mList.get(position).getActivity_title());
        textviewtime.setText(mList.get(position).getActivity_time()+"至"+mList.get(position).getActivity_time2()+" "+mList.get(position).getActivity_time3()+"至"+mList.get(position).getActivity_time4());
       /* textViewtime2.setText();*/
        textviewaddress.setText("地址 ："+mList.get(position).getActivity_address());
        glideLoaderUtils.ItemintoViewADDailog(mContext,img_info, Urls.URL_CONSTANT+mList.get(position).getActivity_pic());
      /*  cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(mContext, InfoDetailsActivity.class);
                mIntent.putExtra("URL",InfoUrl+ String.valueOf(position+1));
                LogUtils.i("了解更多URL",InfoUrl+ String.valueOf(position+1));
                mContext.startActivity(mIntent);
            }
        });*/
//        分别设置值
        morebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 了解更多的界面、
                LogUtils.i("more","了解更多的界面");
                Intent mIntent = new Intent(mContext, InfoDetailsActivity.class);
                mIntent.putExtra("URL",InfoUrl+ String.valueOf(position+1));
                LogUtils.i("了解更多URL",InfoUrl+ String.valueOf(position+1));
                mContext.startActivity(mIntent);
            }
        });
        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        listener.select(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private CardPagerAdapter.OnPageSelectListener listener;
    public void setOnPageSelectListener(CardPagerAdapter.OnPageSelectListener listener) {
        this.listener = listener;
    }


    public interface OnPageSelectListener {
        void select(int position);
    }
}
