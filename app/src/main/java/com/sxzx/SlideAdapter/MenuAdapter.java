/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sxzx.SlideAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koolearn.klibrary.ui.android.R;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.bean.MyCollect;
import com.sxzx.utils.glideLoaderUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

/**
 * Created by YOLANDA on 2016/7/22.
 */
public class MenuAdapter extends SwipeMenuAdapter<MenuAdapter.DefaultViewHolder> {

    private List<MyCollect.ListBean> titles;
    private Context mContext ;
    private OnItemClickListener mOnItemClickListener;

    public MenuAdapter(Context mContext, List<MyCollect.ListBean> titles) {
        this.titles = titles;
        this.mContext = mContext ;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return titles == null ? 0 : titles.size();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mybooktwo_rcy, parent, false);
    }

    @Override
    public MenuAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(MenuAdapter.DefaultViewHolder holder, int position) {
        holder.setData(titles.get(position));
        holder.setOnItemClickListener(mOnItemClickListener);
    }

     class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
         TextView bookname ;
         TextView bookauther ;
        TextView bookdescribe ;
        TextView bookshuping ;
         ImageView imgicon ;
         ImageView imgBook ;
//         glideLoaderUtils.display(mContext, (ImageView) holder.getView(R.id.readbook_author_img), Urls.URL_CONSTANT + listBean.getCommittee_ren_head());
//         glideLoaderUtils.ItemintoView(mContext, (ImageView) holder.getView(R.id.iv_readbook), Urls.URL_CONSTANT + listBean.getBook_pic());

        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            bookname = (TextView) itemView.findViewById(R.id.readbook_name);
            bookauther = (TextView) itemView.findViewById(R.id.readbook_name);
            bookdescribe = (TextView) itemView.findViewById(R.id.readbook_describe);
            bookshuping = (TextView) itemView.findViewById(R.id.readbook_pinglun);
            imgicon = (ImageView) itemView.findViewById(R.id.readbook_author_img);
            imgBook = (ImageView) itemView.findViewById(R.id.iv_readbook);
//            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public void setData(MyCollect.ListBean listBean) {
            bookname.setText(listBean.getBook_name());
            bookauther.setText(listBean.getCommittee_ren_name());
            bookshuping.setText(listBean.getCommittee_book_title());
            bookdescribe.setText(listBean.getCommittee_book_details());
            glideLoaderUtils.display(mContext,  imgicon, Urls.URL_CONSTANT + listBean.getCommittee_ren_head());
            glideLoaderUtils.ItemintoView(mContext, imgBook, Urls.URL_CONSTANT + listBean.getBook_pic());
//            this.tvTitle.setText(title);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }

    }
/*
*    TextView bookname = holder.getView(R.id.readbook_name);
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
*
*
* */  public void addAll(List elements) {
    titles.addAll(elements);
    notifyDataSetChanged();
}
    public void clear() {
        if (titles != null && titles.size() > 0) {
            titles.clear();
            notifyDataSetChanged();
        }
    }
    public void removeAt(int index) {
        titles.remove(index);
        notifyDataSetChanged();
    }
}
