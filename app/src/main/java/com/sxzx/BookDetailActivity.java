package com.sxzx;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import com.anye.greendao.gen.BuyBookDao;
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
import com.sxzx.GreenDao.BuyBook;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.Utils.GsonUtil;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.base.Utils.PayResult;
import com.sxzx.base.baseActivity.BaseActivity;
import com.sxzx.base.bean.PayBeanalI;
import com.sxzx.base.bean.newBookdetil;
import com.sxzx.utils.SPUtils;
import com.sxzx.utils.glideLoaderUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
/*
*   setContentView(R.layout.activity_appbar_detail);
*
*     String filePath = getExternalCacheDirPath() + "/" + book.getTitle() + ".epub";
* */
public class BookDetailActivity extends BaseActivity {
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
    /*  @Bind(R.id.numberbar1)
      NumberProgressBar numberbar1;*/
    private String book_id;
    //    private List<newBookdetil.ListBean> mList ;
    private newBookdetil.ListBean mbookdetilbean;
    private String url_Download = "";
    private String Url_mDownload = "" ;
    private final BookCollectionShadow bookCollectionShadow = new BookCollectionShadow();
    private final String app_id = "2016081601753857";
    private final String TAG = "BookDetailActivity";
    private static final int SDK_PAY_FLAG = 1;
    private File mfile;
    private SPUtils mSPUtils ;
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
                                        Toast.makeText(BookDetailActivity.this, "支付成功", Toast.LENGTH_SHORT).show();

                                           // 开启 服务 直接下载 就是了
                                            GetRequest request = OkGo.get(Urls.URL_CONSTANT + Url_mDownload);
                                            downloadManager.addTask(Url_mDownload.substring(Url_mDownload.lastIndexOf("/") + 1), Urls.URL_CONSTANT + Url_mDownload, request, mMoneyDownloadListener);
                                        downloadManager.getDownloadInfo(Urls.URL_CONSTANT + Url_mDownload).getTargetPath();
                                        LogUtils.i("BuyBook","插入数据库表成功----"+ downloadManager.getDownloadInfo(Urls.URL_CONSTANT + Url_mDownload).getTargetPath());
                                        BuyBook mBuybook = new BuyBook(null,mbookdetilbean.getBook_id(),mbookdetilbean.getBook_name(),mbookdetilbean.getBook_pic()
                                                ,mbookdetilbean.getBook_author(),mbookdetilbean.getBook_description(),mbookdetilbean.getBook_read(),mbookdetilbean.getBook_new_price()
                                                ,mbookdetilbean.getBook_epub_free(),mbookdetilbean.getBook_pdf_free(),mbookdetilbean.getBook_pdf_money()
                                                , mbookdetilbean.getBook_epub_money(),mbookdetilbean.getPay(),mbookdetilbean.getBookcase(),mFilea.getAbsolutePath()+"/"+Url_mDownload.substring(Url_mDownload.lastIndexOf("/") + 1)
                                        ,mbookdetilbean.getSize1());
                                        long insert = mBuyBookDao.insert(mBuybook);
                                        LogUtils.i("BuyBook","插入数据库表成功----"+insert+"---"+mFilea.getAbsolutePath()+Url_mDownload.substring(Url_mDownload.lastIndexOf("/") + 1));
                                        // textView8.setText("");
                                            // textView8.setEnabled(false);
//                                        }
                                    }
                                });
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(BookDetailActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    };
    private boolean IsSendBorad = false;


    @Override
    public int getLayoutId() {
        return R.layout.activity_appbar_detail;
    }

    //加入书架me
    @OnClick(R.id.textView7)
    public void AddBookStock(View v) {
        if (TextUtils.isEmpty(KooReaderApplication.myuserinfo.getUserId()))  {
            new MaterialDialog.Builder(BookDetailActivity.this)
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
                                String result = mJson.getString("status");
                                if (result.equals("success")) {
                                    new MaterialDialog.Builder(BookDetailActivity.this)
                                            .content("加入书架成功,请在我的书库查看")
                                            .contentGravity(GravityEnum.CENTER)
                                            .canceledOnTouchOutside(true)
                                            .show();
                                    textView7.setText("已加入");
                                    // 发送一个加入书架成功的广播
                                    IsSendBorad = true;
                                    BuyBook mBuybook = new BuyBook(null,mbookdetilbean.getBook_id(),mbookdetilbean.getBook_name(),mbookdetilbean.getBook_pic()
                                            ,mbookdetilbean.getBook_author(),mbookdetilbean.getBook_description(),mbookdetilbean.getBook_read(),mbookdetilbean.getBook_new_price()
                                            ,mbookdetilbean.getBook_epub_free(),mbookdetilbean.getBook_pdf_free(),mbookdetilbean.getBook_pdf_money()
                                            , mbookdetilbean.getBook_epub_money(),mbookdetilbean.getPay(),mbookdetilbean.getBookcase(),mFilea.getAbsolutePath()+"/"+Url_mDownload.substring(Url_mDownload.lastIndexOf("/") + 1)
                                            ,mbookdetilbean.getSize1());
                                    long insert = mBuyBookDao.insert(mBuybook);
                                    LogUtils.i("BuyBook","插入数据库表成功----"+insert+"---"+mBuybook.toString());

                                    LogUtils.i("BuyBook","插入数据库表成功----"+insert+"---"+mFilea.getAbsolutePath()+Url_mDownload.substring(Url_mDownload.lastIndexOf("/") + 1));
                                    // textView8.setText("");
                                    /*if (result.equals("0"))*/
                                } else  if(result.equals("error")){
                                    new MaterialDialog.Builder(BookDetailActivity.this)
                                            .content("已存在书架")
                                            .contentGravity(GravityEnum.CENTER)
                                            .canceledOnTouchOutside(true)
                                            .show();
                                }else {
                                    new MaterialDialog.Builder(BookDetailActivity.this)
                                            .content("加入失败")
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
    protected void onPause() {
        super.onPause();

        if (IsSendBorad) {
            SendBorad();
        }
    }

    private void SendBorad() {
        Intent intent = new Intent();
        //设置intent的动作为com.example.broadcast，可以任意定义
        intent.setAction("com.example.lxj.addbook");
        //发送无序广播
        sendBroadcast(intent);
        LogUtils.i("MyboradRecever", "广播发出来了");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public void initView() {
        SetTranslanteBar();
        getWindow().setLayout(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
//        mSPUtils = new SPUtils(this,"CurrentBook");
//        numberbar1.setMax(100);
        book_id = getIntent().getStringExtra("book_id");
        //这个界面不做缓存
        OkGo.post(Urls.URL_CONSTANT + Urls.URL_NNEWBOOK_DESCRIPTION )
                .tag(this)
                .params("user_id",KooReaderApplication.myuserinfo.getUserId())
                .params("book_id",book_id)
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
        // 设置一个下载路径
        downloadManager = DownloadService.getDownloadManager();
         mFilea = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/epub/");
        if(!mFilea.exists()){
            mFilea.mkdirs();
        }
        downloadManager.setTargetFolder(mFilea.getAbsolutePath());
         mBuyBookDao = KooReaderApplication.getAppContext().getDaoSession().getBuyBookDao();
    }
    private  BuyBookDao mBuyBookDao ;
    /*
    * 购买
    * */
    private  File mFilea ;
    @OnClick(R.id.detil_buy_button)
    public void BuyButton(View v) {
        if (TextUtils.isEmpty(KooReaderApplication.myuserinfo.getUserId())) {
            new MaterialDialog.Builder(BookDetailActivity.this)
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
                                    PayTask alipay = new PayTask(BookDetailActivity.this);
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

    }

    /*
    * 控件数据初始化
    * */
    private void initData(newBookdetil.ListBean bookdetil) {
//        bookdetilHaveread.setText("3人阅读");
        textFilesize.setText(bookdetil.getSize1());
        newBookName.setText(bookdetil.getBook_name());
        newBookAuthor.setText(bookdetil.getBook_author());
        detilDescription.setText("  " + bookdetil.getBook_description());
        bookdetilHaveread.setText(bookdetil.getBook_read());
        // 购买状态
        // 购买过的话 不再执行点击事件
        if("0".equals(bookdetil.getPay())){
            detilBuyButton.setText(bookdetil.getBook_new_price() + "购买");
        }else {
            detilBuyButton.setText( "已购买");
            detilBuyButton.setEnabled(false);
        }
        // 加入书架状态
        if("0".equals(bookdetil.getBookcase())){
            textView7.setText("加入书架");
        }else {
            textView7.setText("已加入");
            textView7.setEnabled(false);
        }
        url_Download = bookdetil.getBook_epub_free();
        Url_mDownload = bookdetil.getBook_epub_money() ;
        glideLoaderUtils.ItemintoView(this, ivPhoto, Urls.URL_CONSTANT + bookdetil.getBook_pic());
        String fileName = url_Download.substring(url_Download.lastIndexOf("/") + 1);
//        mfile = new File("/storage/emulated/0/download/" + fileName);
    }
    /*
    * /storage/emulated/0/download/5800c2b1e9978.epub
    * */
    // 试读
    private DownloadManager downloadManager;
    // 下载 功能
    @OnClick(R.id.textView8)
    public void freeReadBook(View v) {
//        startProgressDialog("加载中");
        if (downloadManager.getDownloadInfo(Urls.URL_CONSTANT + url_Download) != null) {
            showShortToast("\"任务已经在下载列表中\"");
            mfile = new File(downloadManager.getDownloadInfo(Urls.URL_CONSTANT + url_Download).getTargetPath());
            if(mfile.isFile()){
                OpenEpubBook(mfile.getAbsolutePath());
                LogUtils.i("path",mfile.getAbsolutePath().toString());
            }else {
                // 移除下载
                downloadManager.removeTask(Urls.URL_CONSTANT + url_Download);
                GetRequest request = OkGo.get(Urls.URL_CONSTANT + url_Download);
                downloadManager.addTask(url_Download.substring(url_Download.lastIndexOf("/") + 1), Urls.URL_CONSTANT + url_Download, request, mDownloadListener);
            }
        } else {
            GetRequest request = OkGo.get(Urls.URL_CONSTANT + url_Download);
            downloadManager.addTask(url_Download.substring(url_Download.lastIndexOf("/") + 1), Urls.URL_CONSTANT + url_Download, request, mDownloadListener);
            // textView8.setText("");
            // textView8.setEnabled(false);
        }
       /* startProgressDialog("加载中");
        String fileName = url_Download.substring(url_Download.lastIndexOf("/") + 1);
        File mfile = new File("/storage/emulated/0/download/" + fileName);
        LogUtils.i("absolutePath", mfile.getAbsolutePath() + "------------");
        if (mfile.exists() && mfile.isFile()) {
//            numberbar1.setProgress(100);
            OpenEpubBook(mfile.getAbsolutePath());
            stopProgressDialog();
        } else {
            // 文件不存在 那就访问网络
            OkGo.get(Urls.URL_CONSTANT + url_Download)//
                    .cacheKey(Urls.URL_CONSTANT + url_Download)
                    .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                    .tag(this)//
                    .execute(new FileCallback() {  //文件下载时，可以指定下载的文件目录和文件名
                        @Override
                        public void onSuccess(File file, Call call, Response response) {
                            // file 即为文件数据，文件保存在指定目录
                            String absolutePath = file.getAbsolutePath();
                            LogUtils.i("absolutePath", absolutePath);
                            OpenEpubBook(absolutePath);
                        }
                        @Override
                        public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {


                        }
                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                        }
                        @Override
                        public void onAfter(@Nullable File file, @Nullable Exception e) {
                            super.onAfter(file, e);
                            stopProgressDialog();
                        }

                    });
        }*/

    }

    /*
    * 下载 成功 移除下载的标签
    * */
    private DownloadListener mDownloadListener = new DownloadListener() {
        @Override
        public void onProgress(DownloadInfo downloadInfo) {
            showShortToast(downloadInfo.getProgress() + "");
            NumProgressBar.setVisibility(View.VISIBLE);
            NumProgressBar.setProgress((int)(downloadInfo.getProgress()*100));
        }

        @Override
        public void onFinish(DownloadInfo downloadInfo) {

            mfile = new File(downloadInfo.getTargetPath());
            OpenEpubBook(mfile.getAbsolutePath());
            // 下载 成功
//            downloadManager.removeTask(Urls.URL_CONSTANT + url_Download);
        }

        @Override
        public void onError(DownloadInfo downloadInfo, String errorMsg, Exception e) {
            downloadManager.removeTask(Urls.URL_CONSTANT + url_Download);
        }
    };
    // 收费下载的监听
    private DownloadListener mMoneyDownloadListener = new DownloadListener() {
        @Override
        public void onProgress(DownloadInfo downloadInfo) {

        }

        @Override
        public void onFinish(DownloadInfo downloadInfo) {

            // 下载 成功
//            downloadManager.removeTask(Urls.URL_CONSTANT + url_Download);
        }

        @Override
        public void onError(DownloadInfo downloadInfo, String errorMsg, Exception e) {
            //


                downloadManager.removeTask(Urls.URL_CONSTANT + Url_mDownload);
                GetRequest request = OkGo.get(Urls.URL_CONSTANT + Url_mDownload);
                downloadManager.addTask(Url_mDownload.substring(Url_mDownload.lastIndexOf("/") + 1), Urls.URL_CONSTANT + Url_mDownload, request, mMoneyDownloadListener);
        }
    };
    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, BookDetailActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }


    /*
    *
    * 打开书的路径
    * */
    public void OpenEpubBook(final String bookpath) {
        bookCollectionShadow.bindToService(BookDetailActivity.this, new Runnable() {
            @Override
            public void run() {
                Book bookByFile = bookCollectionShadow.getBookByFile(bookpath);
                LogUtils.i("absolutePath", "absolutePath----" + bookpath);
                if (bookByFile != null) {
                    openBook(bookByFile);
                } else {
                    Toast.makeText(BookDetailActivity.this, "打开失败,请重试", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openBook(Book data) {
//        KooReader.openBookActivity(this, data, null);
        KooReader.openBookActivityId(this,data,null,mbookdetilbean.getBook_id(),mbookdetilbean.getBook_name());
        Log.d("MainActivity", "结束System.currentTimeMillis():" + System.currentTimeMillis());
        overridePendingTransition(R.anim.tran_fade_in, R.anim.tran_fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bookCollectionShadow.unbind();
        downloadManager.pauseTask(Urls.URL_CONSTANT + url_Download);
        downloadManager.removeTask(Urls.URL_CONSTANT + url_Download);
    }


}
