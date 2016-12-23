package com.sxzx.base.pager.fragmentmybook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.koolearn.android.kooreader.KooReaderApplication;
import com.koolearn.klibrary.ui.android.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.sxzx.SlideAdapter.MenuAdapter;
import com.sxzx.SlideAdapter.OnItemClickListener;
import com.sxzx.base.BasePager;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.Utils.GsonUtil;
import com.sxzx.base.bean.MyCollect;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Response;


public class MyBookfragmenttwo extends BasePager {

    @Bind(R.id.mybookfg_two)
    SwipeMenuRecyclerView mybookfgTwo;
//    private CommonAdapter<MyCollect.ListBean> fragmenttwo;
    private List<MyCollect.ListBean> mListfgtwo;
    private MycollectbookRecever mMycollectbookRecever;
    private MenuAdapter mMenuAdapter;
    @Override
    protected int getLayoutResource() {
        return R.layout.mybkfgtwo;
    }

    @Override
    protected void initView() {
        mListfgtwo = new ArrayList<MyCollect.ListBean>();
      /*  fragmenttwo = new CommonAdapter<MyCollect.ListBean>(getActivity(), R.layout.item_readbook_rcy, mListfgtwo) {
            @Override
            protected void convert(ViewHolder holder, MyCollect.ListBean listBean, int position) {
                TextView bookname = holder.getView(R.id.readbook_name);
                TextView bookauther = holder.getView(R.id.readbook_author_name);
                TextView bookdescribe = holder.getView(R.id.readbook_describe);
                TextView bookshuping = holder.getView(R.id.readbook_pinglun);
                bookname.setText(listBean.getBook_name());
                bookauther.setText(listBean.getCommittee_ren_name());
                bookshuping.setText(listBean.getCommittee_book_title());
                bookdescribe.setText(listBean.getCommittee_book_details());
                // 这是 cropcenter
                glideLoaderUtils.display(mContext, (ImageView) holder.getView(R.id.readbook_author_img), Urls.URL_CONSTANT + listBean.getCommittee_ren_head());
                glideLoaderUtils.ItemintoView(mContext, (ImageView) holder.getView(R.id.iv_readbook), Urls.URL_CONSTANT + listBean.getBook_pic());
            }
        };*/
        mybookfgTwo.setLayoutManager(new LinearLayoutManager(getActivity()));
        mybookfgTwo.setHasFixedSize(true);
        mMenuAdapter = new MenuAdapter(getActivity(),mListfgtwo);
        mMenuAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showShortToast("点我是看书");
            }
        });
        mybookfgTwo.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
//        mybookfgTwo.addItemDecoration(new ListViewDecoration());// 添加分割线。
        mybookfgTwo.setAdapter(mMenuAdapter);
        mybookfgTwo.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        mybookfgTwo.setSwipeMenuItemClickListener(menuItemClickListener);
        if (!TextUtils.isEmpty(KooReaderApplication.myuserinfo.getUserId())) {
            RequestFGtwo();
        }
        mMycollectbookRecever = new MycollectbookRecever();
        IntentFilter mIntentFiter = new IntentFilter("com.example.lxj.collectbook");
        getActivity().registerReceiver(mMycollectbookRecever, mIntentFiter);
    }

    // 判断是否登陆 登陆了  就请求网络
    private void RequestFGtwo() {
        OkGo.post(Urls.URL_CONSTANT + Urls.URL_Collect)
                .params("user_id", KooReaderApplication.myuserinfo.getUserId())
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            JSONObject myJsonObject = new JSONObject(s);
                            if (myJsonObject.getString("list").contains("null")) {
                                showShortToast("暂无收藏");
                            } else {
                                MyCollect myCollect = GsonUtil.GsonToBean(s, MyCollect.class);
                                mListfgtwo = myCollect.getList();
                              /*  fragmenttwo.clear();
                                fragmenttwo.addAll(mListfgtwo);*/
                                mMenuAdapter.clear();
                                mMenuAdapter.addAll(mListfgtwo);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    private class MycollectbookRecever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            RequestFGtwo();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(mMycollectbookRecever);
    }

    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.delete_width);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加左侧的，如果不添加，则左侧不会出现菜单。


            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity())
                        .setBackgroundDrawable(R.color.tab_text_color)
                        .setImage(R.mipmap.delete)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。


            }
        }
    };
    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView#RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, final int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。
            OkGo.post(Urls.URL_CONSTANT+Urls.URL_DeleCollect)
                    .cacheMode(CacheMode.NO_CACHE)
                    .params("committee_id",mListfgtwo.get(adapterPosition).getCommittee_id())
                    .params("user_id",KooReaderApplication.myuserinfo.getUserId())
                    .params("com_user_id",mListfgtwo.get(adapterPosition).getCom_user_id())
                    .params("book_id",mListfgtwo.get(adapterPosition).getBook_id())
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {

                            if(s.contains("success")){
                                showShortToast("删除收藏成功");
                                mListfgtwo.remove(adapterPosition);
                                mMenuAdapter.removeAt(adapterPosition);
                            }else {
                                showShortToast("删除收藏失败");
                            }
                        }
                    });
         /*   if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(getActivity(), "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(getActivity(), "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }

            // TODO 如果是删除：推荐调用Adapter.notifyItemRemoved(position)，不推荐Adapter.notifyDataSetChanged();
            if (menuPosition == 0) {// 删除按钮被点击。
                mMenuAdapter.removeAt(adapterPosition);
//                fragmenttwo.notifyItemRemoved(adapterPosition);
            }*/
        }
    };


    }
