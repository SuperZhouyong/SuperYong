package com.sxzx.base.pager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.koolearn.klibrary.ui.android.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.sxzx.BookDetailActivity;
import com.sxzx.SearchActivity;
import com.sxzx.ZHYrecyclerview.CircleLayoutManager;
import com.sxzx.ZHYrecyclerview.CommonAdapter;
import com.sxzx.ZHYrecyclerview.MultiItemTypeAdapter;
import com.sxzx.ZHYrecyclerview.ScrollZoomLayoutManager;
import com.sxzx.ZHYrecyclerview.base.ViewHolder;
import com.sxzx.ZHYrecyclerview.wrapper.HeaderAndFooterWrapper;
import com.sxzx.base.BasePager;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.Utils.GsonUtil;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.base.bean.ActivityInfoUi;
import com.sxzx.base.bean.NewbookHead;
import com.sxzx.base.bean.NewbookRcy;
import com.sxzx.utils.FastBlurUtil;
import com.sxzx.utils.NetworkUtils;
import com.sxzx.utils.glideLoaderUtils;
import com.sxzx.view.AdDialog;
import com.sxzx.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;


/**
 * 新书推荐
 *
 * @author Kevin
 * @date 2015-10-18
 */
public class NewsBookPager extends BasePager implements View.OnClickListener {
    private static String TAG = "NewsBookPager---";
    @Bind(R.id.irc)
    RecyclerView irc;
    @Bind(R.id.rcy_refresh)
    MaterialRefreshLayout rcyRefresh;
 /*   @Bind(R.id.fab)
    FloatingActionButton fab;*/
//    @Bind(R.id.newbook_head_comment)
   private RelativeLayout newbookHeadComment;
//    @Bind(R.id.newbook_head_search)
    private  RelativeLayout newbookHeadSearch;

    // 头布局的四个元素
    private int page = 1;


    private TextView newbookVpBookname;

    private RecyclerView vpHomeShuf;
    // head 和 RCY 的 数据集合
    private List<NewbookRcy.ListBean> Rcylistbean;
    //private List<NewbookHead.ListBean> headlistbean;
    // Rcy 适配器
    private CommonAdapter<NewbookRcy.ListBean> adapterRcy;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    // vP 适配器
    private CommonAdapter<NewbookHead.ListBean> adapterHead;
    //    private HomeShufAdapter mHomeadapter;
    private List<NewbookHead.ListBean> mHeadRcy;
    // 放置头布局的View
//    private List<ImageView> mVPheadList;
    // 刷新数据的因子
    private Boolean isloadingHead, isloadingRcy, isCanRefresh = false;


    @Override
    protected int getLayoutResource() {
        return R.layout.newbook_pager;
    }

    @Override
    protected void initView() {
        mHeadRcy = new ArrayList<>();
//        mVPheadList = new ArrayList<>();
        Rcylistbean = new ArrayList<>();
//        headlistbean = new ArrayList<>();
//        找到上面的两个控件
//        newbookHeadComment.setOnClickListener(this);
//        newbookHeadSearch.setOnClickListener(this);
        irc.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterRcy = new CommonAdapter<NewbookRcy.ListBean>(getActivity(), R.layout.item_newboo_rcy, Rcylistbean) {
            @Override
            protected void convert(ViewHolder holder, NewbookRcy.ListBean listBean, int position) {
                TextView Tv_rank = holder.getView(R.id.id_rank);
                if (position == 1) {

                    Tv_rank.setVisibility(View.VISIBLE);
                }else {

                    Tv_rank.setVisibility(View.GONE);
                }
                TextView bookname = holder.getView(R.id.new_book_name);
                TextView bookauther = holder.getView(R.id.new_book_author);
                TextView bookdes = holder.getView(R.id.new_book_type);
                bookname.setText(listBean.getBook_name());
                bookauther.setText(listBean.getBook_author());
                bookdes.setText(listBean.getBook_description());
                glideLoaderUtils.ItemintoView(mContext, (ImageView) holder.getView(R.id.iv_photo), Urls.URL_CONSTANT + listBean.getBook_pic());
            }
        };
        adapterRcy.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                // 需要传一部分数据
//                BookDetailActivity.startAction(getActivity());
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
                intent.putExtra("book_id", adapterRcy.getAll().get(position - 1).getBook_id());
                startActivity(intent);
               /* BookdetailDialog mBookDialog = new BookdetailDialog(getActivity(),adapterRcy.getAll().get(position - 1).getBook_id());
                mBookDialog.show();*/
               /* Dialogutil dialogutil = new Dialogutil();
                dialogutil.ShowDialog(getActivity());*/
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        initHeadView();
        // 需要包裹
        irc.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));


        irc.setAdapter(mHeaderAndFooterWrapper);
//        mHeaderAndFooterWrapper.notifyDataSetChanged();
     /*   fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irc.smoothScrollToPosition(0);
            }
        });*/
        // 刷新监听
        adapterHead = new CommonAdapter<NewbookHead.ListBean>(getActivity(), R.layout.my_image, mHeadRcy) {
            @Override
            protected void convert(ViewHolder holder, NewbookHead.ListBean listBean, int position) {
                ImageView imgHead = holder.getView(R.id.image);
                glideLoaderUtils.ItemintoView(getActivity(), imgHead, Urls.URL_CONSTANT + listBean.getBook_pic());
            }
        };
        adapterHead.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (!NetworkUtils.isAvailableByPing(getActivity())) {
                    new MaterialDialog.Builder(getActivity())
                            .content("网络不可用")
                            .contentGravity(GravityEnum.CENTER)
                            .canceledOnTouchOutside(true)
                            .show();
                    return;
                }
                Intent mintent = new Intent(getActivity(), BookDetailActivity.class);
                mintent.putExtra("book_id", mHeadRcy.get(0).getBook_id());
                startActivity(mintent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        vpHomeShuf.addOnScrollListener(new CenterScrollListener());
        vpHomeShuf.setLayoutManager(new ScrollZoomLayoutManager(getActivity(), Dp2px(10)));
        vpHomeShuf.setAdapter(adapterHead);
        rcyRefresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                // 请求全部布局的数据
                // 清空 Rcy 的数据
                adapterRcy.clear();
                page = 1;
                ReQuestAllCurrentData(page);
            }

            @Override
            public void onfinish() {
                super.onfinish();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
//                showShortToast("加载更多数据--Rcy数据更新--加载更多数据接口没看到");
                // 请求 Rcy 的数据   数据正在刷新
                if (isloadingHead && isloadingRcy) {
                    return;
                }


                rcyRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        RequestRcyData(page);
                    }
                }, 1000);
//                TODO
            }
        });
        // 请求网络
        ReQuestAllCurrentData(page);
    }

    public int Dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /*
    *   请求当前界面所有数据
    *
    * */
    private void ReQuestAllCurrentData(int page) {

        RequestRcyData(page);
        mHeaderAndFooterWrapper.notifyDataSetChanged();
    }

    /*
    *    请求头部数据
    * */
    private void RequestHeadDate() {
        OkGo.get(Urls.URL_CONSTANT + Urls.URL_NEW_NEWBOOK_recommed_head)
                .cacheKey(Urls.URL_CONSTANT + Urls.URL_NEW_NEWBOOK_recommed_head)
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        isloadingHead = true;
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        RefreshheadDataVP(s);
                    }

                    @Override
                    public void onCacheSuccess(String s, Call call) {
                        super.onCacheSuccess(s, call);

                        RefreshheadDataVP(s);
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        isloadingHead = false;
//                        mHeaderAndFooterWrapper.notifyItemChanged(0);
                        if (!isloadingRcy) {
                            // 判断 Rcy 的数据是否加载完毕了
                            rcyRefresh.finishRefresh();
                        }
                    }
                });
    }

    /*
    *    对头布局的数据经行重新生成
    *
    * */
    private void RefreshheadDataVP(String s) {
        NewbookHead newbookHead = GsonUtil.GsonToBean(s, NewbookHead.class);
//        headlistbean.clear();
//        mVPheadList.clear();
//        mHeadRcy.clear();
        adapterHead.clear();
        mHeadRcy.clear();
        mHeadRcy = newbookHead.getList();
        // 告诉头部适配器 我数据请求回来了
        LogUtils.i(TAG, "头布局开始初始化" + mHeadRcy.toString() + s);
//        mHeadRcy.addAll(mHeadRcy);
        adapterHead.addAll(mHeadRcy);
        vpHomeShuf.smoothScrollToPosition(2);


    }


    /*
    * 请求RcyView的是数据
    * */
    private void RequestRcyData(int p) {
        OkGo.get(Urls.URL_CONSTANT + Urls.URL_NEWBOOK_recommed_recy + "p/" + p)     // 请求方式和请求url
                .cacheKey(Urls.URL_CONSTANT + Urls.URL_NEWBOOK_recommed_recy + "p/" + p)            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        isloadingRcy = true;
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        showShortToast("请稍后再试");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LogUtils.i(TAG, s);
                        if (s.contains("null")) {
                            // 请求数据为null 那就没有更多数据了  page 的数字怎么不能更改
                            page--;
                            showShortToast("没有更多啦");
                        } else {
                            NewbookRcy newbookRcy = GsonUtil.GsonToBean(s, NewbookRcy.class);
                            Rcylistbean = newbookRcy.getList();
                            // 告诉 适配器
                            adapterRcy.addAll(Rcylistbean);
//                            mHeaderAndFooterWrapper.notifyDataSetChanged();
                            LogUtils.i(TAG, "数据返回来了------------" + Rcylistbean.toString());
                        }
                    }

                    @Override
                    public void onCacheSuccess(String s, Call call) {
                        super.onCacheSuccess(s, call);
                        // 只读取第一页的缓存
                        if (page == 1) {
                            NewbookRcy newbookRcy = GsonUtil.GsonToBean(s, NewbookRcy.class);
                            Rcylistbean = newbookRcy.getList();
                            // 告诉 适配器
                            adapterRcy.clear();
                            adapterRcy.addAll(Rcylistbean);
//                            mHeaderAndFooterWrapper.notifyDataSetChanged();
                            LogUtils.i(TAG + "rcyRefresh", "刷新数据读取缓存成功");

                        }

                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        isloadingRcy = false;
                        mHeaderAndFooterWrapper.notifyDataSetChanged();
                        RequestHeadDate();
                        if (!isloadingHead) {
                            //刷新的关闭
                            rcyRefresh.finishRefresh();
                        }
                        // 加载更多关闭
                        rcyRefresh.finishRefreshLoadMore();

                    }
                });
    }

    // 初始化头布局
    private void initHeadView() {
        LogUtils.i(TAG, "数据返回来头布局正在sehzhi");
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(adapterRcy);
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.newbook_head, irc, false);
        // 找到头布局的四个元素
        newbookHeadComment = (RelativeLayout) inflate.findViewById(R.id.newbook_head_comment);
        newbookHeadSearch = (RelativeLayout) inflate.findViewById(R.id.newbook_head_search);
        newbookHeadComment.setOnClickListener(this);
        newbookHeadSearch.setOnClickListener(this);
        newbookVpBookname = (TextView) inflate.findViewById(R.id.newbook_Vp_bookname);
        vpHomeShuf = (RecyclerView) inflate.findViewById(R.id.vp_home_shuf);
//        mHomeadapter = new HomeShufAdapter(mVPheadList, getActivity());
//        mHomeadapter.setOnPageSelectListener(this);
//        vpHomeShuf.setAdapter(mHomeadapter);
//        vpHomeShuf.setOffscreenPageLimit(5);
//        vpHomeShuf.setAdapter(mHomeadapter);
//        vpHomeShuf.addOnPageChangeListener(mHomeadapter);
        mHeaderAndFooterWrapper.addHeaderView(inflate);
    }



    /*
     *  点击事件的 处理
     * */

    private AdDialog mAdDialog;
    Bitmap blurBitmap = null;
    Bitmap Bmp = null;
    private List<ActivityInfoUi.ListBean> mlist;

    public void blurViewshow() {

        if (Bmp == null && blurBitmap == null) {
            LogUtils.i("pageIdx", "blurBitmap==null");
            WindowManager windowManager = getActivity().getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            int w = display.getWidth();
            int h = display.getHeight();
            Bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            View decorview = getActivity().getWindow().getDecorView().getRootView();
            decorview.setDrawingCacheEnabled(true);
            decorview.buildDrawingCache();
            Bmp = decorview.getDrawingCache();
            blurBitmap = FastBlurUtil.toBlur(Bmp, 1);
        }


//        LogUtils.i("drawable","需要设置了");

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newbook_head_comment:
//                showShortToast("点击了Comment");
//                blurViewshow();
//                Drawable drawable = new BitmapDrawable(getResources(),blurBitmap) ;
                mAdDialog = new AdDialog(getActivity(), R.style.FullScreenDialogTheme);
                mAdDialog.show();
//                startActivity(MessageActivity.class);

                break;
            case R.id.newbook_head_search:
//                showShortToast("点击了搜索");
                startActivity(SearchActivity.class);
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



    class CenterScrollListener extends RecyclerView.OnScrollListener {
        private boolean mAutoSet = false;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();


//            adapter.getItemId()
            if (!(layoutManager instanceof CircleLayoutManager) && !(layoutManager instanceof ScrollZoomLayoutManager)) {
                mAutoSet = true;
                return;
            }

            if (!mAutoSet) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                    final int dx;
                    if (layoutManager instanceof CircleLayoutManager) {
                        dx = ((CircleLayoutManager) layoutManager).getOffsetCenterView();
                    } else {
                        dx = ((ScrollZoomLayoutManager) layoutManager).getOffsetCenterView();
                    }
                    int itemCount = recyclerView.getAdapter().getItemCount();
                    itemCount = ((ScrollZoomLayoutManager) layoutManager).getCurrentPosition();

                    LogUtils.i("ItemCount", "---" + itemCount);
                    recyclerView.smoothScrollBy(dx, 0);
                    newbookVpBookname.setText(adapterHead.get(itemCount).getBook_name());
                }
                mAutoSet = true;
            }
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
                mAutoSet = false;
            }
        }
    }
}
