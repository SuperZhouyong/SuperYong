package com.sxzx;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.koolearn.android.kooreader.KooReaderApplication;
import com.koolearn.klibrary.ui.android.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.Utils.GsonUtil;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.base.baseActivity.BaseActivity;
import com.sxzx.base.bean.ReadBookInfo;
import com.sxzx.utils.NetworkUtils;
import com.sxzx.utils.glideLoaderUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import me.codeboy.android.aligntextview.AlignTextView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator
 * on 2016/10/31.
 */

public class ReadDetilActivity extends BaseActivity {
    @Bind(R.id.readactivity_return)
    ImageView readactivityReturn;
    @Bind(R.id.setting_activity_return)
    RelativeLayout settingActivityReturn;
    @Bind(R.id.readdetil_share)
    ImageView readdetilShare;
    @Bind(R.id.readdetil_activity_share)
    RelativeLayout readdetilActivityShare;
    @Bind(R.id.readdetil_title)
    TextView readdetilTitle;
    @Bind(R.id.readdetil_image)
    ImageView readdetilImage;
    @Bind(R.id.readdetil_imagename)
    TextView readdetilImagename;
    @Bind(R.id.textView11)
    AlignTextView textView11;
    @Bind(R.id.readdetil_booimg)
    ImageView readdetilBooimg;
    @Bind(R.id.readdetil_desc)
    TextView readdetilDesc;
    @Bind(R.id.readactivity_read)
    TextView readactivityRead;
    @Bind(R.id.readdetil_bookname)
    TextView readdetilBookname;
    @Bind(R.id.readdetil_activity_shoucang)
    RelativeLayout readdetilActivityShoucang;
    @Bind(R.id.readdetil_shoucang)
    ImageView readdetilShoucang;
    private String committee_id;
    private List<ReadBookInfo.ListBean> mList;
    private Boolean IsSendBorad = false;

    @Override
    public int getLayoutId() {
        return R.layout.readdetilactivity;
    }

    @Override
    public void initView() {
        committee_id = getIntent().getStringExtra("committee_id");
        OkGo.get(Urls.URL_CONSTANT + Urls.URL_ReadBook_Info + "id/" + committee_id)
                .cacheMode(CacheMode.NO_CACHE)
                .cacheKey(Urls.URL_CONSTANT + Urls.URL_ReadBook_Info)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        ReadBookInfo mInfo = GsonUtil.GsonToBean(s, ReadBookInfo.class);
                        mList = mInfo.getList();
                        glideLoaderUtils.ItemintoView(ReadDetilActivity.this, readdetilImage, Urls.URL_CONSTANT + mList.get(0).getCommittee_ren_head());
                        //人名
                        readdetilImagename.setText(mList.get(0).getCommittee_ren_name());
                        // 书名
                        readdetilBookname.setText(mList.get(0).getCommittee_book_title());
                        //书的描述
                        readdetilDesc.setText(mList.get(0).getBook_copyright());
                        // 书img
                        glideLoaderUtils.ItemintoView(ReadDetilActivity.this, readdetilBooimg, Urls.URL_CONSTANT + mList.get(0).getBook_pic());
                        //最大范围的秒速
                        textView11.setText(mList.get(0).getCommittee_book_details());
                        // 顶标题
                        readdetilTitle.setText(mList.get(0).getCommittee_book_title());

                    }
                });
    }

    @OnClick(R.id.readactivity_read)
    public void readBookInfo(View v) {
        if (!NetworkUtils.isAvailableByPing(this)) {
            new MaterialDialog.Builder(this)
                    .content("网络不可用")
                    .contentGravity(GravityEnum.CENTER)
                    .canceledOnTouchOutside(true)
                    .show();
            return;
        }

        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra("book_id", mList.get(0).getBook_id());
        startActivity(intent);
    }

    @OnClick(R.id.setting_activity_return)
    public void ReadDetilReturn(View v) {
        finish();
    }

    @OnClick(R.id.readdetil_activity_share)
    public void ReadDetilShare(View v) {
        showShare();
//        showShortToast("分享");
    }

    @OnClick(R.id.readdetil_activity_shoucang)
    public void ReadDetilCollect(View v) {
        //判断是否登陆
        if (KooReaderApplication.myuserinfo == null) {
            new MaterialDialog.Builder(ReadDetilActivity.this)
                    .content("请先登录")
                    .contentGravity(GravityEnum.CENTER)
                    .canceledOnTouchOutside(true)
                    .show();
        } else {
            OkGo.post(Urls.URL_CONSTANT + Urls.URL_AddCollect)
                    .cacheMode(CacheMode.NO_CACHE)
                    .params("user_id", KooReaderApplication.myuserinfo.getUserId())
                    .params("book_id", mList.get(0).getBook_id())
                    .params("committee_id", mList.get(0).getCommittee_id())
                    .params("committee_ren_id", mList.get(0).getCommittee_ren_id())
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            LogUtils.i("收藏成功", s);
                            readdetilShoucang.setBackgroundResource(R.mipmap.haveshoucang);
                            if (s.contains("ok")) {
                                new MaterialDialog.Builder(ReadDetilActivity.this)
                                        .content("收藏成功")
                                        .contentGravity(GravityEnum.CENTER)
                                        .canceledOnTouchOutside(true)
                                        .show();

                                IsSendBorad = true;
                            } else {
                                new MaterialDialog.Builder(ReadDetilActivity.this)
                                        .content("已收藏")
                                        .contentGravity(GravityEnum.CENTER)
                                        .canceledOnTouchOutside(true)
                                        .show();

                            }

                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            new MaterialDialog.Builder(ReadDetilActivity.this)
                                    .content("收藏失败")
                                    .contentGravity(GravityEnum.CENTER)
                                    .canceledOnTouchOutside(true)
                                    .show();
                        }
                    });

        }
    }

    private void ShouCang() {
        OkGo.post(Urls.URL_CONSTANT + Urls.URL_AddCollect)
                .cacheMode(CacheMode.NO_CACHE)
                .params("user_id", KooReaderApplication.myuserinfo.getUserId())
                .params("book_id", mList.get(0).getBook_id())
                .params("committee_id", mList.get(0).getCommittee_id())
                .params("committee_ren_id", mList.get(0).getCommittee_ren_id())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LogUtils.i("收藏成功", s);
                        readdetilShoucang.setBackgroundResource(R.mipmap.haveshoucang);
                        if (s.contains("ok")) {
                            new MaterialDialog.Builder(ReadDetilActivity.this)
                                    .content("收藏成功")
                                    .contentGravity(GravityEnum.CENTER)
                                    .canceledOnTouchOutside(true)
                                    .show();

                            IsSendBorad = true;
                        } else {
                            new MaterialDialog.Builder(ReadDetilActivity.this)
                                    .content("已收藏")
                                    .contentGravity(GravityEnum.CENTER)
                                    .canceledOnTouchOutside(true)
                                    .show();

                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        new MaterialDialog.Builder(ReadDetilActivity.this)
                                .content("收藏失败")
                                .contentGravity(GravityEnum.CENTER)
                                .canceledOnTouchOutside(true)
                                .show();
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (IsSendBorad) {
            SendBorad();
        }
    }

    private void SendBorad() {
        Intent intent = new Intent();
        //设置intent的动作为com.example.broadcast，可以任意定义
        intent.setAction("com.example.lxj.collectbook");
        //发送无序广播
        sendBroadcast(intent);
        LogUtils.i("MyboradRecever", "广播发出来了+collectbook");
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }


}
