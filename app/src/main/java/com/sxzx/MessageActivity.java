package com.sxzx;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.koolearn.klibrary.ui.android.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.Utils.GsonUtil;
import com.sxzx.base.baseActivity.BaseActivity;
import com.sxzx.base.bean.ActivityInfoUi;
import com.sxzx.base.cardadapter.CardPagerAdapter;
import com.sxzx.base.cardadapter.ShadowTransformer;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator
 * on 2016/10/31.
 */

public class MessageActivity extends BaseActivity {
    @Bind(R.id.cardTypeBtn)
    Button cardTypeBtn;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;

    private List<ActivityInfoUi.ListBean> mlist ;
    @Override
    public int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    public void initView() {
        OkGo.get(Urls.URL_CONSTANT+Urls.URL_activity)
                .cacheKey(Urls.URL_CONSTANT+Urls.URL_activity)
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        ActivityInfoUi mInfoui = GsonUtil.GsonToBean(s,ActivityInfoUi.class);
                        mlist = mInfoui.getList() ;
                        mCardAdapter = new CardPagerAdapter(MessageActivity.this,mlist);
                        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
                        mCardShadowTransformer.enableScaling(true);
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
                        startProgressDialog("网络...");
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        stopProgressDialog();
                    }
                });

    }


    /**
     * 入口
     *
     * @param activity
     */
  /*  public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, MessageActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }*/
    @OnClick(R.id.cardTypeBtn)
    public void cardTypebtn(View v){
        finish();

    }

}
