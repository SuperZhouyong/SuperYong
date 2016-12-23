package com.sxzx.base.pager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.koolearn.klibrary.ui.android.R;
import com.sxzx.SearchActivity;
import com.sxzx.ZHYrecyclerview.BaseFragmentAdapter;
import com.sxzx.base.BasePager;
import com.sxzx.base.ConfigKey.AppConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 政协书库
 *
 * @author Kevin
 * @date 2015-10-18
 */
public class LibraryPager extends BasePager {


    @Bind(R.id.view_pager)
    ViewPager viewPager;
   /* @Bind(R.id.fab)
    FloatingActionButton fab;*/
    @Bind(R.id.library_sousuo)
    RelativeLayout librarySousuo;
    @Bind(R.id.tabs)
    TabLayout tabs;
    // adapter的 适配器
    private BaseFragmentAdapter mBaseFragmentAdapter;
    private List<String> TitleNames;
    private List<Fragment> mBooksFragmentList;
    private String[] BookStackRoom = { "文史资料","委员文库","政协工作","政协研究","政协年鉴" };

    @OnClick(R.id.library_sousuo)
    public void click() {
        // 搜索 数据
        // NewsChannelActivity.startAction(getContext());
        startActivity(SearchActivity.class);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.libabry_pager;
    }

    @Override
    protected void initView() {
//        BookStackRoom
        TitleNames = new ArrayList<>();
        mBooksFragmentList = new ArrayList<>();
        for (int i = 2 ; i<BookStackRoom.length+2 ;i++) {
            mBooksFragmentList.add(createListFragments(""+(i+1)));
        }
        TitleNames = Arrays.asList(BookStackRoom);
        // 传入的 管理器 fragment集合 名字的集合
        mBaseFragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), mBooksFragmentList, TitleNames);
        viewPager.setAdapter(mBaseFragmentAdapter);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        setPageChangeListener();
     /*   fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRxManager.post(AppConstant.NEWS_LIST_TO_TOP, "");
            }
        });*/
    }

    /*
    *  设置viewpager 的 page 的章节监听
    *
    * */
    private void setPageChangeListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /*
    * 创建新的 fragment 的工厂
    * */
    // 传入每一个界面一个id
    private BooksFrament createListFragments(String s) {
        BooksFrament fragment = new BooksFrament();
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.lirbraybook_ID,s);
       /* bundle.putString(AppConstant.NEWS_ID, newsChannel.getNewsChannelId());
        bundle.putString(AppConstant.NEWS_TYPE, newsChannel.getNewsChannelType());
        bundle.putInt(AppConstant.CHANNEL_POSITION, newsChannel.getNewsChannelIndex());*/
        fragment.setArguments(bundle);
        return fragment;
    }


}
