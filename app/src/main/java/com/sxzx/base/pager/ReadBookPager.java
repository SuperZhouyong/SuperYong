package com.sxzx.base.pager;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.sxzx.ReadDetilActivity;
import com.sxzx.SearchActivity;
import com.sxzx.ZHYrecyclerview.CommonAdapter;
import com.sxzx.ZHYrecyclerview.MultiItemTypeAdapter;
import com.sxzx.ZHYrecyclerview.base.ViewHolder;
import com.sxzx.ZHYrecyclerview.wrapper.HeaderAndFooterWrapper;
import com.sxzx.base.BasePager;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.Utils.GsonUtil;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.base.bean.ReadBookHead;
import com.sxzx.base.bean.ReadBookRecommend;
import com.sxzx.utils.NetworkUtils;
import com.sxzx.utils.glideLoaderUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;


/**
 * 委员读书
 *
 * @author Kevin
 * @date 2015-10-18
 */
public class ReadBookPager extends BasePager {


 /*   @Bind(R.id.fab)
    FloatingActionButton fab;*/
    @Bind(R.id.readbook_sousuo)
    RelativeLayout readbookSousuo;
    @Bind(R.id.irc_readbook)
    RecyclerView ircReadbook;
    @Bind(R.id.rcy_refresh_read)
    MaterialRefreshLayout rcyRefreshRead;

    private int page = 1 ;

    // 头部的四个元素

    private List<ReadBookRecommend.ListBean> readbookRCylist = new ArrayList();
    private List<ReadBookHead.ListBean> mReadBookheadlist = new ArrayList<>();
    // Rcy 数据的适配器
    private CommonAdapter<ReadBookRecommend.ListBean> readbookadapter;
    // 增加头布局
    private HeaderAndFooterWrapper mreadbookHeadAdapter;
    // 头与bady 的刷新因子
    private Boolean isloadinghead ;
    private Boolean isloadingData ;

    @Override
    protected int getLayoutResource() {
        return R.layout.readbook_pager;
    }
    @OnClick(R.id.readbook_sousuo)
    public void toSerachActivity(View v){
        // 进入搜索界面
        startActivity(SearchActivity.class);
    }

    @Override
    protected void initView() {
        readbookadapter = new CommonAdapter<ReadBookRecommend.ListBean>(getActivity(), R.layout.item_readbook_rcy, readbookRCylist) {
            @Override
            protected void convert(ViewHolder holder, ReadBookRecommend.ListBean readbookbean, int position) {
                TextView bookname = holder.getView(R.id.readbook_name);
                TextView bookauther = holder.getView(R.id.readbook_author_name);
                TextView bookdescribe = holder.getView(R.id.readbook_describe);
                TextView bookshuping = holder.getView(R.id.readbook_pinglun);
                bookname.setText(readbookbean.getBook_name());
                bookauther.setText(readbookbean.getCommittee_ren_name());
                bookshuping.setText(readbookbean.getCommittee_book_title());
                bookdescribe.setText(readbookbean.getCommittee_book_details());
                // 这是 cropcenter
                glideLoaderUtils.display(mContext,(ImageView) holder.getView(R.id.readbook_author_img),Urls.URL_CONSTANT + readbookbean.getCommittee_ren_head());
                glideLoaderUtils.ItemintoView(mContext,(ImageView) holder.getView(R.id.iv_readbook),Urls.URL_CONSTANT + readbookbean.getBook_pic());
            }
        };
        ircReadbook.setLayoutManager(new LinearLayoutManager(getActivity()));
        initheadView();
        rcyRefreshRead.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                // 刷新就重新请求 数据
                page = 1 ;
                readbookadapter.clear();
                RequestReadbookHead();
                RequestReadbookRcy(page);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                rcyRefreshRead.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 暂时未加载数据
                        page ++ ;
                        RequestReadbookRcy(page);
                    }
                }, 1000);
            }
        });


        ircReadbook.setAdapter(mreadbookHeadAdapter);
        mreadbookHeadAdapter.notifyDataSetChanged();
        RequestAlldate(page);
        // 条目点击事件
        readbookadapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
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
                Intent intent = new Intent(getActivity(),ReadDetilActivity.class);
                LogUtils.i("position---","  "+position);
                // 头布局之后的 postion 发生变化了
                intent.putExtra("committee_id", readbookadapter.getAll().get(position-1).getCommittee_id());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        // fab 的点击事件
      /*  fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ircReadbook.smoothScrollToPosition(0);
            }
        });*/
    }

    private void RequestAlldate(int p) {


        RequestReadbookHead();
        RequestReadbookRcy(p);
    }

    // 请求Rcy的数据
    private void RequestReadbookRcy(int p) {
        isloadingData = false ;
        OkGo.get(Urls.URL_CONSTANT + Urls.URL_ReadBook_Recommend+"p/"+p)
                .cacheKey("readbook_Rcy")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (s.contains("null")) {
                            // 请求数据为null 那就没有更多数据了  page 的数字怎么不能更改
                            page--;
                            showShortToast("没有更多啦");
                        }else {
                            LogUtils.i("sss",s);

                            s = s.replace("\\s+","");
                            LogUtils.i("sss",s);
                            ReadBookRecommend mReadBookRecommend = GsonUtil.GsonToBean(s, ReadBookRecommend.class);
//                          readbookRCylist.addAll(mReadBookRecommend.getList());
                            readbookadapter.addAll(mReadBookRecommend.getList());
                            mreadbookHeadAdapter.notifyDataSetChanged();
                        }

                    }
                    // 读取缓存
                    @Override
                    public void onCacheSuccess(String s, Call call) {
                        super.onCacheSuccess(s, call);
                        if( page == 1){
                            readbookadapter.clear();
                            ReadBookRecommend mReadBookRecommend = GsonUtil.GsonToBean(s, ReadBookRecommend.class);
//                            readbookRCylist.addAll(mReadBookRecommend.getList());
                            readbookadapter.addAll(mReadBookRecommend.getList());
                            mreadbookHeadAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        isloadingData = true ;
                        if(isloadinghead){
                            rcyRefreshRead.finishRefresh();
                        }
                        rcyRefreshRead.finishRefreshLoadMore();
                    }
                });

    }
    /*
    * 头布局四个
    * */
    private ImageView readbookHeadImg;
    private TextView readbookHeadBookdesir;
    private TextView readbookHeadAuthername;
    private ImageView readbookHeadRightImg;

    // 初始化头布局
    private void initheadView() {
        mreadbookHeadAdapter = new HeaderAndFooterWrapper(readbookadapter);
        View readbookhead = getActivity().getLayoutInflater().inflate(R.layout.readbook_head, ircReadbook, false);
        readbookHeadImg = (ImageView) readbookhead.findViewById(R.id.readbook_head_img);
        readbookHeadBookdesir = (TextView) readbookhead.findViewById(R.id.readbook_head_bookdesir);
        readbookHeadAuthername = (TextView) readbookhead.findViewById(R.id.readbook_head_authername);
        readbookHeadRightImg = (ImageView) readbookhead.findViewById(R.id.readbook_head_right_img);
        mreadbookHeadAdapter.addHeaderView(readbookhead);

    }

    // 请求头部的数据
    private void RequestReadbookHead() {
        isloadinghead = false ;
        OkGo.get(Urls.URL_CONSTANT + Urls.getURL_ReadBook_Head)
                .cacheKey(Urls.URL_CONSTANT + Urls.getURL_ReadBook_Head)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        InitheadCache(s);

                    }

                    @Override
                    public void onCacheSuccess(String s, Call call) {
                        super.onCacheSuccess(s, call);
                        InitheadCache(s);

                    }



                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        // 头部刷新完毕
                        isloadinghead = true ;
                        if(isloadingData){
                            rcyRefreshRead.finishRefresh();
                        }
                    }
                });
    }

    private void InitheadCache(String s) {
        ReadBookHead mReadBookHead = GsonUtil.GsonToBean(s, ReadBookHead.class);
        mReadBookheadlist = mReadBookHead.getList();
        readbookHeadBookdesir.setText(mReadBookheadlist.get(0).getCommittee_first());
        readbookHeadAuthername.setText(mReadBookheadlist.get(0).getCommittee_ren_name());
        // 先写一张图片吧
        Glide.with(getActivity()).load(Urls.URL_CONSTANT + mReadBookheadlist.get(0).getCommittee_bgpic())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_image_loading)
                .error(R.mipmap.ic_empty_picture)
                .centerCrop()
                .crossFade().into(readbookHeadImg);
        Glide.with(getActivity()).load(Urls.URL_CONSTANT + mReadBookheadlist.get(0).getBook_pic())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_image_loading)
                .centerCrop()
                .error(R.mipmap.ic_empty_picture)
                .crossFade().into(readbookHeadRightImg);
        readbookHeadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!NetworkUtils.isAvailableByPing(getActivity())){
                    new MaterialDialog.Builder(getActivity())
                            .content("网络不可用")
                            .contentGravity(GravityEnum.CENTER)
                            .canceledOnTouchOutside(true)
                            .show();
                    return;
                }
                Intent intent = new Intent(getActivity(),ReadDetilActivity.class);
                // 头布局之后的 postion 发生变化了
                intent.putExtra("committee_id", mReadBookheadlist.get(0).getCommittee_id());
                startActivity(intent);
            }
        });
    }
}
