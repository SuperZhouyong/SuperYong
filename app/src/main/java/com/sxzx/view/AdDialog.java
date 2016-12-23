package com.sxzx.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.koolearn.klibrary.ui.android.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.sxzx.InfoDetailsActivity;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.Utils.GsonUtil;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.base.bean.ActivityInfoUi;
import com.sxzx.base.cardadapter.CardPagerAdapter;
import com.sxzx.base.cardadapter.ShadowTransformer;
import com.sxzx.utils.glideLoaderUtils;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class AdDialog extends Dialog {
    //
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private List<ActivityInfoUi.ListBean> mlist;

    private Context context;
    private RelativeLayout relblur ;
    private ViewPager mViewPager;
    private Button mBt;
    private String InfoUrl = Urls.URL_CONSTANT+Urls.URL_Activity_message;


//    private Drawable mDrawable ;
    public AdDialog(Context context) {
        super(context);
        this.context = context;
    }

    public AdDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_layout);
        setCanceledOnTouchOutside(false);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mBt = (Button) findViewById(R.id.cardTypeBtn);
        relblur = (RelativeLayout) findViewById(R.id.relblur);
        OkGo.get(Urls.URL_CONSTANT + Urls.URL_activity)
                .cacheKey(Urls.URL_CONSTANT + Urls.URL_activity)
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        ActivityInfoUi mInfoui = GsonUtil.GsonToBean(s, ActivityInfoUi.class);
                        mlist = mInfoui.getList();
                        /*ViewPagerAdapter mAdapter = new ViewPagerAdapter(mlist);
                        mViewPager.setPageMargin(20);

                        mViewPager.setAdapter(mAdapter);*/
                        mCardAdapter = new CardPagerAdapter(context, mlist);
                        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
                        mCardShadowTransformer.enableScaling(true);
                        mViewPager.setPageMargin(40);
                        mViewPager.setAdapter(mCardAdapter);
                        mViewPager.setPageTransformer(false, mCardShadowTransformer);
                        mViewPager.setOffscreenPageLimit(3);
                        mViewPager.setOnPageChangeListener(mCardAdapter);
                        // 条目选中的时候执行的方法
                        mCardAdapter.setOnPageSelectListener(new CardPagerAdapter.OnPageSelectListener() {
                            @Override
                            public void select(int position) {

                            }
                        });
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                    }
                });

        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
    private class ViewPagerAdapter extends PagerAdapter {
        private List<ActivityInfoUi.ListBean> mList ;
        public ViewPagerAdapter(List<ActivityInfoUi.ListBean> mlist){
            this.mList = mlist ;
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }


        @Override
        public void destroyItem(View container, int position, Object object) {
            // TODO Auto-generated method stub
            super.destroyItem(container, position, object);
        }

        @Override
        public Object instantiateItem(final View container, final int position) {
            // TODO Auto-generated method stub
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.adapter, (ViewGroup) container, false);

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
            glideLoaderUtils.ItemintoView(context,img_info, Urls.URL_CONSTANT+mList.get(position).getActivity_pic());
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(context, InfoDetailsActivity.class);
                    mIntent.putExtra("URL",InfoUrl+ String.valueOf(position+1));
                    LogUtils.i("了解更多URL",InfoUrl+ String.valueOf(position+1));
                    context.startActivity(mIntent);
                }
            });
//        分别设置值
            morebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 了解更多的界面、
                    LogUtils.i("more","了解更多的界面");
                }
            });

            ((ViewGroup) container).addView(view);
            return view;
        }

    }

}
