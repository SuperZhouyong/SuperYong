package com.sxzx;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.koolearn.klibrary.ui.android.R;
import com.sxzx.ZHYrecyclerview.CommonAdapter;
import com.sxzx.ZHYrecyclerview.base.ViewHolder;
import com.sxzx.base.baseActivity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator
 * on 2016/11/21.
 */

public class NoteDetailActivity extends BaseActivity {
    @Bind(R.id.note_detail_rcy)
    RecyclerView noteDetailRcy;
    private CommonAdapter notedetailAdapter;
    private List mList = new ArrayList();

    @Override
    public int getLayoutId() {
        return R.layout.notedeatil_activity;
    }

    @Override
    public void initView() {
        notedetailAdapter = new CommonAdapter(this, R.layout.item_note_detail, mList) {
            @Override
            protected void convert(ViewHolder holder, Object o, int position) {

            }


        };
        noteDetailRcy.setLayoutManager(new LinearLayoutManager(this));
        noteDetailRcy.setAdapter(notedetailAdapter);
        RequestDate();
    }

    private void RequestDate() {

    }


}
