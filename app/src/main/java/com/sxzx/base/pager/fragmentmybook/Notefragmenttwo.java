package com.sxzx.base.pager.fragmentmybook;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.koolearn.android.kooreader.KooReaderApplication;
import com.koolearn.klibrary.ui.android.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.sxzx.ZHYrecyclerview.CommonAdapter;
import com.sxzx.ZHYrecyclerview.base.ViewHolder;
import com.sxzx.base.BasePager;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.Utils.GsonUtil;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.base.bean.NoteBuybean;
import com.sxzx.utils.glideLoaderUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Response;


public class Notefragmenttwo extends BasePager {


    @Bind(R.id.mynotefg_two)
    RecyclerView mynotefgTwo;
    private CommonAdapter fragmenttwo;
    private List<NoteBuybean.ListBean> mListfgtwo = new ArrayList();
    ;
    private String NoteTitle;
    private String URl_NoteFragment;
    private Boolean IsSendBorad = false;

    @Override
    protected int getLayoutResource() {
        return R.layout.mynotefgtwo;
    }

    @Override
    protected void initView() {
        fragmenttwo = new CommonAdapter<NoteBuybean.ListBean>(getActivity(), R.layout.item_notebuy_rd, mListfgtwo) {
            @Override
            protected void convert(ViewHolder holder, NoteBuybean.ListBean listBean, final int position) {

                TextView TV_title = holder.getView(R.id.tvTitle);
                final ImageView Tv_Add = holder.getView(R.id.add_TV);
                TextView Tv_Auther = holder.getView(R.id.tvAuther);
                TV_title.setText(listBean.getBook_name());
                Tv_Auther.setText(listBean.getBook_author());
                if ("0".equals(listBean.getBook_add_bookcase().trim())) {
//                    Tv_Add.setBackgroundResource(R.drawable.shape_note_buycd_soml);
                  /*  Tv_Add.setTextColor(Color.RED);
                    Tv_Add.setText("加入书库");*/
                    Tv_Add.setBackgroundResource(R.mipmap.goin);
                    Tv_Add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            OkGo.post(Urls.URL_CONSTANT + Urls.URL_AddBookrack)
                                    .params("user_id", KooReaderApplication.myuserinfo.getUserId())
                                    .params("book_id", mListfgtwo.get(position).getBook_id().trim())
                                    .cacheMode(CacheMode.NO_CACHE)
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(String s, Call call, Response response) {
//                            showShortToast("加入书架成功");
                                            try {
                                                JSONObject mJson = new JSONObject(s);
                                                String result = mJson.getString("result");
                                                if (result.equals("1")) {
                                                    new MaterialDialog.Builder(getActivity())
                                                            .content("加入书架成功,请在我的书库查看")
                                                            .contentGravity(GravityEnum.CENTER)
                                                            .canceledOnTouchOutside(true)
                                                            .show();
                                                    RequestFGOne();
                                                    IsSendBorad = true;
                                                    Tv_Add.setBackgroundResource(R.mipmap.havesave);
                                                } else if (result.equals("0")) {
                                                    new MaterialDialog.Builder(getActivity())
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
                    });
                } else {
                    Tv_Add.setBackgroundResource(R.mipmap.havesave);

                }
//                Tv_Add.setText(mListfgtwo.get(position).getBook_add_bookcase());
                glideLoaderUtils.ItemintoView(getActivity(), (ImageView) holder.getView(R.id.ivBook), Urls.URL_CONSTANT + mListfgtwo.get(position).getBook_pic());
            }
        };
        mynotefgTwo.setLayoutManager(new LinearLayoutManager(getActivity()));
        mynotefgTwo.setAdapter(fragmenttwo);
        // 请求书架的数据
        RequestFGOne();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (IsSendBorad) {
            SenBorad();
        }
    }

    private void SenBorad() {
        Intent intent = new Intent();
        //设置intent的动作为com.example.broadcast，可以任意定义
        intent.setAction("com.example.lxj.addbook");
        //发送无序广播
        getActivity().sendBroadcast(intent);
        LogUtils.i("MyboradRecever", "广播发出来了");
    }

    // 判断是否登陆 登陆了  就请求网络
    private void RequestFGOne() {
        OkGo.post(Urls.URL_CONSTANT + Urls.URL_buyRcored)
                .params("user_id", KooReaderApplication.myuserinfo.getUserId())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        try {
                            JSONObject myJsonObject = new JSONObject(s);
                            if (myJsonObject.getString("list").contains("null")) {
                                showShortToast("未保存笔记");
                            } else {
                                NoteBuybean mNoteBuy = GsonUtil.GsonToBean(s, NoteBuybean.class);
                                mListfgtwo = mNoteBuy.getList();
                                fragmenttwo.clear();
                                fragmenttwo.addAll(mListfgtwo);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

    }



}
