package com.sxzx.ZHYrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;

import com.sxzx.ZHYrecyclerview.base.ItemViewDelegate;
import com.sxzx.ZHYrecyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by zhy on 16/4/9.
 */
public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T>
{
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public CommonAdapter(final Context context, final int layoutId, List<T> datas)
    {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(new ItemViewDelegate<T>()
        {
            @Override
            public int getItemViewLayoutId()
            {
                return layoutId;
            }

            @Override
            public boolean isForViewType( T item, int position)
            {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position)
            {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T t, int position);
//新增加的方法
    public void add(T elem) {
        mDatas.add(elem);
        notifyDataSetChanged();
    }

    public void addAt(int location, T elem) {
        mDatas.add(location, elem);
        notifyDataSetChanged();
    }

    public void addAll(List<T> elements) {
        mDatas.addAll(elements);
        notifyDataSetChanged();
    }
    // 获取到 当前展示数据的条目
    public int getDataSize(){
       return mDatas.size() ;
    }
    public void addAllAt(int location, List<T> elements) {
        mDatas.addAll(location, elements);
        notifyDataSetChanged();
    }

    public void remove(T elem) {
        mDatas.remove(elem);
        notifyDataSetChanged();
    }

    public void removeAt(int index) {
        mDatas.remove(index);
        notifyItemRemoved(index);
//        notifyDataSetChanged();
    }

    public void removeAll(List<T> elements) {
        mDatas.removeAll(elements);
        notifyDataSetChanged();
    }

    public void clear() {
        if (mDatas != null && mDatas.size() > 0) {
            mDatas.clear();
            notifyDataSetChanged();
        }
    }

    public void replace(T oldElem, T newElem) {
        replaceAt(mDatas.indexOf(oldElem), newElem);
    }

    public void replaceAt(int index, T elem) {
        mDatas.set(index, elem);
        notifyDataSetChanged();
    }

    public void replaceAll(List<T> elements) {
        if (mDatas.size() > 0) {
            mDatas.clear();
        }
        mDatas.addAll(elements);
        notifyDataSetChanged();
    }

    public T get(int position) {
        if (position >= mDatas.size())
            return null;
        return mDatas.get(position);
    }

    public List<T> getAll() {
        return mDatas;
    }

    public int getSize() {
        return mDatas.size();
    }

    public boolean contains(T elem) {
        return mDatas.contains(elem);
    }
}
