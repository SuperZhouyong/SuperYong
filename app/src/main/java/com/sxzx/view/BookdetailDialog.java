package com.sxzx.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alipay.sdk.app.PayTask;
import com.koolearn.android.kooreader.KooReader;
import com.koolearn.android.kooreader.KooReaderApplication;
import com.koolearn.android.kooreader.libraryService.BookCollectionShadow;
import com.koolearn.klibrary.ui.android.R;
import com.koolearn.kooreader.book.Book;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.download.DownloadInfo;
import com.lzy.okserver.download.DownloadManager;
import com.lzy.okserver.download.DownloadService;
import com.lzy.okserver.listener.DownloadListener;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.Utils.GsonUtil;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.base.Utils.PayResult;
import com.sxzx.base.bean.PayBeanalI;
import com.sxzx.base.bean.newBookdetil;
import com.sxzx.utils.glideLoaderUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator
 * on 2016/11/30.
 */

public class BookdetailDialog extends Dialog {
    @Bind(R.id.textview_description)
    TextView textviewDescription;
    @Bind(R.id.detil_buy_button)
    Button detilBuyButton;
    @Bind(R.id.iv_photo_img)
    ImageView ivPhoto;
    @Bind(R.id.new_book_name)
    TextView newBookName;
    @Bind(R.id.new_book_author)
    TextView newBookAuthor;
    @Bind(R.id.bookdetil_size)
    TextView textFilesize;
    @Bind(R.id.textView8)
    TextView textView8;
    @Bind(R.id.textView7)
    TextView textView7;
    @Bind(R.id.detail_relat)
    RelativeLayout detailRelat;
    @Bind(R.id.detil_description)
    TextView detilDescription;
    @Bind(R.id.bookdetil_haveread)
    TextView bookdetilHaveread;
    @Bind(R.id.numberbar1)
    ProgressBar NumProgressBar;
    private Activity mContext;
    private String book_id;
    //    private List<newBookdetil.ListBean> mList ;
    private newBookdetil.ListBean mbookdetilbean;
    private String url_Download = "";
    private final BookCollectionShadow bookCollectionShadow = new BookCollectionShadow();
    private final String app_id = "2016081601753857";
    private final String TAG = "BookDetailActivity";
    private static final int SDK_PAY_FLAG = 1;
    private File mfile;
    private boolean IsSendBorad = false;
    private DownloadManager downloadManager;
    public BookdetailDialog(Context context,String bookid) {
        super(context);
        mContext = (Activity) context;
        book_id = bookid;
    }

    public BookdetailDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = (Activity) context;
    }

    protected BookdetailDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = (Activity) context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_detail);
        ButterKnife.bind(this);
        InitView();
    }

    private void InitView() {
        getWindow().setLayout(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
//        numberbar1.setMax(100);

        //这个界面不做缓存
        OkGo.get(Urls.URL_CONSTANT + Urls.URL_NNEWBOOK_DESCRIPTION + "id/" + book_id)
                .tag(this)
                .cacheMode(CacheMode.NO_CACHE)
                .cacheKey(Urls.URL_CONSTANT + Urls.URL_NNEWBOOK_DESCRIPTION + "id/" + book_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        newBookdetil mbookdetil = GsonUtil.GsonToBean(s, newBookdetil.class);
                        mbookdetilbean = mbookdetil.getList();
                        initData(mbookdetilbean);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });

        downloadManager = DownloadService.getDownloadManager();
        File mFilea = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/aaa/");
        if(!mFilea.exists()){
            mFilea.mkdirs();
        }
        downloadManager.setTargetFolder(Environment.getExternalStorageDirectory().getAbsolutePath() + "/aaa/");
    }

    private void initData(newBookdetil.ListBean bookdetil) {
        bookdetilHaveread.setText("3人阅读");
        textFilesize.setText("30M");
        newBookName.setText(bookdetil.getBook_name());
        newBookAuthor.setText(bookdetil.getBook_author());
        detilDescription.setText("  " + bookdetil.getBook_description());
        bookdetilHaveread.setText(bookdetil.getBook_read());
        detilBuyButton.setText(bookdetil.getBook_new_price() + "购买");
        url_Download = bookdetil.getBook_epub_free();
        glideLoaderUtils.ItemintoView(mContext, ivPhoto, Urls.URL_CONSTANT + bookdetil.getBook_pic());
        String fileName = url_Download.substring(url_Download.lastIndexOf("/") + 1);
//        mfile = new File("/storage/emulated/0/download/" + fileName);

    }

    /*
     * 支付宝的处理方式
     * */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    LogUtils.i("resultStatus", payResult.toString());
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        OkGo.post(Urls.URL_CONSTANT + Urls.URL_aLiServer)
                                .params("resultStatus", resultStatus)
                                .params("resultDict", resultInfo)
                                .cacheMode(CacheMode.NO_CACHE)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        LogUtils.i("resultStatus", "自己的服务器" + s);
                                        Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    };
    /*
  * 购买
  * */
    @OnClick(R.id.detil_buy_button)
    public void BuyButton(View v) {
        if ( TextUtils.isEmpty(KooReaderApplication.myuserinfo.getUserId())) {
            new MaterialDialog.Builder(mContext)
                    .content("请先登陆").contentGravity(GravityEnum.CENTER)
                    .canceledOnTouchOutside(true)
                    .show();
            return;
        }
        /**/
        //
        OkGo.post(Urls.URL_CONSTANT + Urls.URL_aliPay)
                .cacheMode(CacheMode.NO_CACHE)
                .params("user_id", KooReaderApplication.myuserinfo.getUserId())
                .params("book_id", book_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LogUtils.i("alipay------", "alipay" + s);
                        PayBeanalI mPayBean = GsonUtil.GsonToBean(s, PayBeanalI.class);
                        final String orderInfo = mPayBean.getList().get(0).getResult();
                        if (orderInfo != null) {
                            Runnable payRunnable = new Runnable() {
                                @Override
                                public void run() {
                                    PayTask alipay = new PayTask(mContext);
                                    Map<String, String> result = alipay.payV2(orderInfo, true);
                                    Log.i("msp", result.toString());
                                    Message msg = new Message();
                                    msg.what = SDK_PAY_FLAG;
                                    msg.obj = result;
                                    mHandler.sendMessage(msg);
                                }
                            };
                            Thread payThread = new Thread(payRunnable);
                            payThread.start();
                            LogUtils.i(TAG, orderInfo);
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        LogUtils.i("alipay------", "alipay_shibai");
                    }
                });


       /* OkGo.post(" ")
                .params("demo", orderParam)
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });*/
    }
    //加入书架me
    @OnClick(R.id.textView7)
    public void AddBookStock(View v) {
        if (TextUtils.isEmpty(KooReaderApplication.myuserinfo.getUserId())) {
            new MaterialDialog.Builder(mContext)
                    .content("请先登陆").contentGravity(GravityEnum.CENTER)
                    .canceledOnTouchOutside(true)
                    .show();
        } else {
            OkGo.post(Urls.URL_CONSTANT + Urls.URL_AddBookrack)
                    .params("user_id", KooReaderApplication.myuserinfo.getUserId())
                    .params("book_id", book_id)
                    .cacheMode(CacheMode.NO_CACHE)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
//                            showShortToast("加入书架成功");
                            try {
                                JSONObject mJson = new JSONObject(s);
                                String result = mJson.getString("result");
                                if (result.equals("1")) {
                                    new MaterialDialog.Builder(mContext)
                                            .content("加入书架成功,请在我的书库查看")
                                            .contentGravity(GravityEnum.CENTER)
                                            .canceledOnTouchOutside(true)
                                            .show();
                                    textView7.setText("已加入");
                                    // 发送一个加入书架成功的广播
                                    IsSendBorad = true;
                                } else if (result.equals("0")) {
                                    new MaterialDialog.Builder(mContext)
                                            .content("未购买，不可加入")
                                            .contentGravity(GravityEnum.CENTER)
                                            .canceledOnTouchOutside(true)
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (IsSendBorad) {
            SendBorad();
        }
    }
    private void SendBorad() {
        Intent intent = new Intent();
        //设置intent的动作为com.example.broadcast，可以任意定义
        intent.setAction("com.example.lxj.addbook");
        //发送无序广播
        mContext.sendBroadcast(intent);
        LogUtils.i("MyboradRecever", "广播发出来了");
    }
    @OnClick(R.id.textView8)
    public void freeReadBook(View v) {
//        startProgressDialog("加载中");

        if (downloadManager.getDownloadInfo(Urls.URL_CONSTANT + url_Download) != null) {
//            showShortToast("\"任务已经在下载列表中\"");


        } else {
            GetRequest request = OkGo.get(Urls.URL_CONSTANT + url_Download);
            downloadManager.addTask(url_Download.substring(url_Download.lastIndexOf("/") + 1), Urls.URL_CONSTANT + url_Download, request, mDownloadListener);
            // textView8.setText("");
            // textView8.setEnabled(false);
        }
    }
    /*
   * 下载 成功 移除下载的标签
   * */
    private DownloadListener mDownloadListener = new DownloadListener() {
        @Override
        public void onProgress(DownloadInfo downloadInfo) {
//            showShortToast(downloadInfo.getProgress() + "");
            NumProgressBar.setProgress((int)(downloadInfo.getProgress()*100));
        }

        @Override
        public void onFinish(DownloadInfo downloadInfo) {

            mfile = new File(downloadInfo.getTargetPath());
            OpenEpubBook(mfile.getAbsolutePath());
            downloadManager.removeTask(Urls.URL_CONSTANT + url_Download);
        }

        @Override
        public void onError(DownloadInfo downloadInfo, String errorMsg, Exception e) {
            downloadManager.removeTask(Urls.URL_CONSTANT + url_Download);
        }
    };
    /*
   *
   * 打开书的路径
   * */
    public void OpenEpubBook(final String bookpath) {
        bookCollectionShadow.bindToService(mContext, new Runnable() {
            @Override
            public void run() {
                Book bookByFile = bookCollectionShadow.getBookByFile(bookpath);
                LogUtils.i("absolutePath", "absolutePath----" + bookpath);
                if (bookByFile != null) {
                    openBook(bookByFile);
                } else {
                    Toast.makeText(mContext, "打开失败,请重试", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openBook(Book data) {
        KooReader.openBookActivity(mContext, data, null);
        Log.d("MainActivity", "结束System.currentTimeMillis():" + System.currentTimeMillis());
//        mContext.overridePendingTransition(R.anim.tran_fade_in, R.anim.tran_fade_out);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        ButterKnife.unbind(this);
        downloadManager.pauseTask(Urls.URL_CONSTANT + url_Download);
        downloadManager.removeTask(Urls.URL_CONSTANT + url_Download);
    }

    @Override
    public void hide() {
        super.hide();
        downloadManager.pauseTask(Urls.URL_CONSTANT + url_Download);
        downloadManager.removeTask(Urls.URL_CONSTANT + url_Download);
    }
}
