package com.sxzx.base.pager;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.koolearn.klibrary.ui.android.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.sxzx.BookDetailActivity;
import com.sxzx.ZHYrecyclerview.CommonAdapter;
import com.sxzx.ZHYrecyclerview.MultiItemTypeAdapter;
import com.sxzx.ZHYrecyclerview.base.ViewHolder;
import com.sxzx.base.BasePager;
import com.sxzx.base.ConfigKey.AppConstant;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.Utils.GsonUtil;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.base.bean.LibraryBook;
import com.sxzx.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator
 * on 2016/10/21.
 */

public class BooksFrament extends BasePager {

    private String TAG = "BooksFrament--";
    @Bind(R.id.irc_bookfragment)
    RecyclerView ircBookfragment;
    @Bind(R.id.rcy_refresh_bookfragmen)
    MaterialRefreshLayout rcyRefreshBookfragmen;

    private CommonAdapter<LibraryBook.ListBean> lirbrayAdapter ;
    private List<LibraryBook.ListBean> librarydatas ;
    private String bookfragment_id ;
    @Override
    protected int getLayoutResource() {
        return R.layout.epub_booksfragment;
    }

    @Override
    protected void initView() {
        librarydatas = new ArrayList<>() ;
        if (getArguments() != null) {
            bookfragment_id = getArguments().getString(AppConstant.lirbraybook_ID);
        }

        ircBookfragment.setLayoutManager(new GridLayoutManager(getActivity(),3));
        lirbrayAdapter = new CommonAdapter<LibraryBook.ListBean>(getActivity(),R.layout.library_books_fragment,librarydatas) {
            @Override
            protected void convert(ViewHolder holder, LibraryBook.ListBean listBean, int position) {
                TextView bookname = holder.getView(R.id.lirbray_fragment_bookname);
                ImageView bookimg =  holder.getView(R.id.lirbray_fragmnet_bookimg) ;
                bookname.setText(listBean.getBook_name());
                LogUtils.i(TAG, Urls.URL_CONSTANT + listBean.getBook_pic());
                Glide.with(mContext).load(Urls.URL_CONSTANT + listBean.getBook_pic())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.mipmap.ic_image_loading)
                        .error(R.mipmap.ic_empty_picture)
                        .fitCenter()
                        .crossFade().into(bookimg);
            }
        };
        lirbrayAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if(!NetworkUtils.isAvailableByPing(getActivity())){
                    new MaterialDialog.Builder(getActivity())
                            .content("网络不可用")
                            .contentGravity(GravityEnum.CENTER)
                            .canceledOnTouchOutside(true)
                            .show();
                    return;
                }
                Intent intent = new Intent(getActivity(),BookDetailActivity.class);
                LogUtils.i("position---","  "+position);
                intent.putExtra("book_id", lirbrayAdapter.getAll().get(position).getBook_id());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        rcyRefreshBookfragmen.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                lirbrayAdapter.clear();
                lirbrayAdapter.clear();
                RequestBookFragmentData(bookfragment_id) ;
            }
        });

        ircBookfragment.setAdapter(lirbrayAdapter);
        // 防止每次都被初始化     加载更多也在这里调试

        RequestBookFragmentData(bookfragment_id);
    }

    private void RequestBookFragmentData(String id) {
        OkGo.get(Urls.URL_CONSTANT+Urls.URL_lirbray_fragmnet+"id/"+id)
                .cacheKey(Urls.URL_CONSTANT+Urls.URL_lirbray_fragmnet+"id/"+id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        // 转化的每个fragment的数据
                        LibraryBook mlibrarybean = GsonUtil.GsonToBean(s, LibraryBook.class);
                        librarydatas = mlibrarybean.getList() ;
                        lirbrayAdapter.addAll(librarydatas);
                        rcyRefreshBookfragmen.finishRefresh();
                    }
                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);

                    }
                    @Override
                    public void onCacheSuccess(String s, Call call) {
                        super.onCacheSuccess(s, call);
                        LibraryBook mlibrarybean = GsonUtil.GsonToBean(s, LibraryBook.class);
                        librarydatas = mlibrarybean.getList() ;
                        lirbrayAdapter.addAll(librarydatas);
                        rcyRefreshBookfragmen.finishRefresh();
                    }
                });


    }


}
