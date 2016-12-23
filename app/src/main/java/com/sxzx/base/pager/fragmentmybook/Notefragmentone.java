package com.sxzx.base.pager.fragmentmybook;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.koolearn.android.kooreader.KooReaderApplication;
import com.koolearn.klibrary.ui.android.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.sxzx.ZHYrecyclerview.CommonAdapter;
import com.sxzx.ZHYrecyclerview.MultiItemTypeAdapter;
import com.sxzx.ZHYrecyclerview.base.ViewHolder;
import com.sxzx.base.BasePager;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.Utils.GsonUtil;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.base.bean.NoteBean;
import com.sxzx.view.DividerItemDecoration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;


public class Notefragmentone extends BasePager {


    @Bind(R.id.mynotefg_one)
    RecyclerView mynotefgOne;
    private CommonAdapter fragmentone;
    private List mListfgone;
    private String NoteTitle;
    private String URl_NoteFragment;
    private String TAG = "Notefragmentone" ;

    @Override
    protected int getLayoutResource() {
        return R.layout.mynotefgone;
    }

    @Override
    protected void initView() {
        mListfgone = new ArrayList();

        fragmentone = new CommonAdapter<NoteBean.ListBean>(getActivity(), R.layout.item_noteinfo_frag, mListfgone) {
            @Override
            protected void convert(ViewHolder holder, NoteBean.ListBean listBean, int position) {
                TextView ivtime = holder.getView(R.id.TV_time);
                TextView ivnote = holder.getView(R.id.TV_note);
                TextView ivzhujie = holder.getView(R.id.TV_zhujie);
//                glideLoaderUtils.ItemintoView(getActivity(), ivBook, listBean.getBook_id());
//                .substring(5,10)
                String s = listBean.getNote_date();
                ivtime.setText(s);
                ivnote.setText(listBean.getNote_description());
            }
        };
        fragmentone.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        mynotefgOne.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        mynotefgOne.setLayoutManager(new LinearLayoutManager(getActivity()));
        mynotefgOne.setAdapter(fragmentone);
        // 请求书架的数据
        RequestFGOne();
    }

    // 判断是否登陆 登陆了  就请求网络
    private void RequestFGOne() {
        OkGo.post(Urls.URL_CONSTANT + Urls.URL_myNotedetile)
                .params("user_id", KooReaderApplication.myuserinfo.getUserId())
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            JSONObject myJsonObject = new JSONObject(s);
                            if(myJsonObject.getString("list").contains("null")){
                                    showShortToast("未保存笔记");
                            }else {
                                LogUtils.i(TAG,"             ------------"+s);
                                NoteBean mNote = GsonUtil.GsonToBean(s, NoteBean.class);
                                mListfgone = mNote.getList();
                                fragmentone.addAll(mListfgone);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
