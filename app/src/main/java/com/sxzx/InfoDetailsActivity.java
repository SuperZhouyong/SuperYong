package com.sxzx;

import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.koolearn.klibrary.ui.android.R;
import com.sxzx.base.baseActivity.BaseActivity;

import butterknife.Bind;

/**
 * Created by Administrator
 * on 2016/10/31.
 */

public class InfoDetailsActivity extends BaseActivity {


    @Bind(R.id.mwebview_activity)
    LinearLayout mwebviewRel;
    private WebView webView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_infodetails;
    }

    @Override
    public void initView() {
        webView = new WebView(getApplicationContext());
        mwebviewRel.addView(webView);
        // 得到上一级的url
        String url = getIntent().getStringExtra("URL");


        // 开启 localStorage
        webView.getSettings().setDomStorageEnabled(true);
        // 设置支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        // 启动缓存
        webView.getSettings().setAppCacheEnabled(true);
        // 设置缓存模式
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        //使用自定义的WebViewClient
        webView.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.removeAllViews();
        webView.destroy() ;

    }

}
