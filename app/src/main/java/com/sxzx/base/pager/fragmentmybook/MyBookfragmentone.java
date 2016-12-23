package com.sxzx.base.pager.fragmentmybook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.koolearn.android.kooreader.KooReaderApplication;
import com.koolearn.klibrary.ui.android.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.sxzx.BookDetailActivity;
import com.sxzx.ZHYrecyclerview.CommonAdapter;
import com.sxzx.ZHYrecyclerview.MultiItemTypeAdapter;
import com.sxzx.ZHYrecyclerview.base.ViewHolder;
import com.sxzx.base.BasePager;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.Utils.GsonUtil;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.base.bean.MyBookStack;
import com.sxzx.utils.Dialogutil;
import com.sxzx.utils.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Response;


public class MyBookfragmentone extends BasePager {

    @Bind(R.id.mybookfg_one)
    RecyclerView mybookfgOne;
    private CommonAdapter<MyBookStack.ListBean> fragmentone;
    private List<MyBookStack.ListBean> mListfgone;
    private MyboradRecever mMyboradRecever ;

    @Override
    protected int getLayoutResource() {
        return R.layout.mybkfgone;
    }

    @Override
    protected void initView() {
        mListfgone = new ArrayList();
        fragmentone = new CommonAdapter<MyBookStack.ListBean>(getActivity(), R.layout.library_books_fragment, mListfgone) {
            @Override
            protected void convert(ViewHolder holder, MyBookStack.ListBean listBean, int position) {
                ImageView ImgBook = holder.getView(R.id.lirbray_fragmnet_bookimg);
                TextView TvBook = holder.getView(R.id.lirbray_fragment_bookname);
                com.sxzx.utils.glideLoaderUtils.ItemintoView(getActivity(), ImgBook, Urls.URL_CONSTANT+mListfgone.get(position).getBook_pic());
                TvBook.setText(mListfgone.get(position).getBook_name());
            }
        };
        fragmentone.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                // 弹一个 已经购买的窗口
                MyBookStack.ListBean mBookStack  =fragmentone.getDatas().get(position) ;
                // 这里弹出的两个窗口
                if(mBookStack.getIsPay().equals("no")){
                    // 未购买
                    if (!NetworkUtils.isAvailableByPing(getActivity())) {
                        new MaterialDialog.Builder(getActivity())
                                .content("网络不可用")
                                .contentGravity(GravityEnum.CENTER)
                                .canceledOnTouchOutside(true)
                                .show();
                        return;
                    }
                    LogUtils.i("position---", "  " + position);
                    Intent intent = new Intent(getActivity(), BookDetailActivity.class);
                    intent.putExtra("book_id", mBookStack.getBook_id());
                    startActivity(intent);
                }else {
                    // 购买了的
                    Dialogutil dialogutil = new Dialogutil(getActivity(),(MyBookStack.ListBean)fragmentone.getDatas().get(position));
                    dialogutil.ShowDialog(getActivity());
                }

            }
            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, final int position) {
                showShortToast("删除这个？");
                MaterialDialog mDialog = new MaterialDialog.Builder(getActivity())
                        .content("删除？")
                        .positiveText("是")
                        .onPositive(new MaterialDialog.SingleButtonCallback(){
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                showShortToast("删除");
                                OkGo.post(Urls.URL_CONSTANT+Urls.URL_DeleteStack)
                                        .params("user_id",KooReaderApplication.myuserinfo.getUserId())
                                        .params("book_id",fragmentone.getDatas().get(position).getBook_id().trim())
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onSuccess(String s, Call call, Response response) {
                                                if(s.contains("success")){
                                                    fragmentone.removeAt(position);
//                                                    fragmentone.notifyItemRemoved(position);

                                                    showShortToast("删除成功"+position);
                                                }else if(s.contains("error")){
                                                    showShortToast("删除失败"+position);
                                                }
                                            }
                                        });
                            }
                        })
                        .negativeText("否")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                             showShortToast("取消");
                            }
                        })
                        .contentGravity(GravityEnum.START)
                        .canceledOnTouchOutside(true)
                        .show();
                return true;
            }
        });
        mybookfgOne.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mybookfgOne.setAdapter(fragmentone);
        // 请求书架的数据
        if(!TextUtils.isEmpty(KooReaderApplication.myuserinfo.getUserId())){
            RequestFGOne();
        }
        mMyboradRecever = new MyboradRecever();
        IntentFilter mIntenFilter = new IntentFilter("com.example.lxj.addbook");
        getActivity().registerReceiver(mMyboradRecever,mIntenFilter);
    }

    // 判断是否登陆 登陆了  就请求网络
    // 请求网络失败 那就读取缓存 上一次作为 本地保留的数据 也就不需要数据库了
    private void RequestFGOne() {
        OkGo.post(Urls.URL_CONSTANT + Urls.URL_bookrack)
                .params("user_id", KooReaderApplication.myuserinfo.getUserId())
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        try {
                            JSONObject myJsonObject = new JSONObject(s);
                            if (myJsonObject.getString("list").contains("null")) {
                                showShortToast("书架无数据");
                            } else {
                                MyBookStack myBookStack = GsonUtil.GsonToBean(s, MyBookStack.class);
                                mListfgone = myBookStack.getList();
                                fragmentone.clear();
                                fragmentone.addAll(mListfgone);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onCacheSuccess(String s, Call call) {
                        super.onCacheSuccess(s, call);
                        try {
                            JSONObject myJsonObject = new JSONObject(s);
                            if (myJsonObject.getString("list").contains("null")) {
                                showShortToast("书架无数据");
                            } else {
                                MyBookStack myBookStack = GsonUtil.GsonToBean(s, MyBookStack.class);
                                mListfgone = myBookStack.getList();
                                fragmentone.clear();
                                fragmentone.addAll(mListfgone);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(mMyboradRecever);
        LogUtils.i("MyboradRecever","广播注销了");
    }

    // 接收到广播 则请求一下数据
    private class  MyboradRecever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtils.i("MyboradRecever","广播触犯了");
            RequestFGOne();
        }
    }
}
