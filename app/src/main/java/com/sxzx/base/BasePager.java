package com.sxzx.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sxzx.base.commouutils.ToastUitl;
import com.sxzx.view.SafeProgressDialog;

import butterknife.ButterKnife;


/**
 * 五个标签页的基类
 *
 * @author Kevin
 * @date 2015-10-18
 */
public abstract class BasePager extends Fragment {
    protected View rootView;
    SafeProgressDialog myProgressDialog;
//    public RxManager mRxManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //mActivity = getActivity();
        if (rootView == null)
            rootView = inflater.inflate(getLayoutResource(), container, false);
//        mRxManager = new RxManager();
        ButterKnife.bind(this, rootView);

        initView();

        return rootView;
    }

    protected abstract int getLayoutResource();

    protected abstract void initView();

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUitl.showShort(text);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
        ToastUitl.showShort(resId);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId) {

        ToastUitl.showLong(resId);
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text) {
        ToastUitl.showLong(text);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
//        mRxManager.clear();
    }

    public void showProDialog(String str) {

        myProgressDialog = new SafeProgressDialog(getActivity());
        myProgressDialog.setMessage(str);
        myProgressDialog.show();
    }

    public void cancelProDialog() {

        if (myProgressDialog != null) {

            myProgressDialog.cancel();
            myProgressDialog = null;
        }
    }
}
