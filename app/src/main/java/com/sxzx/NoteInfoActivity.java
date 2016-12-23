package com.sxzx;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.koolearn.klibrary.ui.android.R;
import com.sxzx.ZHYrecyclerview.BaseFragmentAdapter;
import com.sxzx.base.baseActivity.BaseActivity;
import com.sxzx.base.pager.fragmentmybook.Notefragmentone;
import com.sxzx.base.pager.fragmentmybook.Notefragmenttwo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator
 * on 2016/11/10.
 */

public class NoteInfoActivity extends BaseActivity {

    @Bind(R.id.Note_return)
    RelativeLayout noteinfoReturn;
    @Bind(R.id.noteinfo_tablayout)
    TabLayout noteinfoTablayout;
    @Bind(R.id.noteinfo_Vp)
    ViewPager noteinfoVp;
    @Bind(R.id.Note_Title)
    TextView NoteTitle;
    private String[] mybp = {"我的笔记", "购买记录",};
    private List<Fragment> mNoteFragmentList = new ArrayList<>();
    private BaseFragmentAdapter mybpfragmentAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_noteinfo;
    }

    @Override
    public void initView() {
        List<String> tabList = Arrays.asList(mybp);
        mNoteFragmentList.add(new Notefragmentone());
        mNoteFragmentList.add(new Notefragmenttwo());
        mybpfragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), mNoteFragmentList, tabList);
        noteinfoVp.setAdapter(mybpfragmentAdapter);
        noteinfoTablayout.setupWithViewPager(noteinfoVp);
        noteinfoTablayout.setTabMode(TabLayout.MODE_FIXED);
        setPageChangeListener();

    }

    @OnClick(R.id.Note_return)
    public void NoteReturn(View v) {
        finish();
    }

    private void setPageChangeListener() {
        noteinfoVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                NoteTitle.setText(mybp[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



}
