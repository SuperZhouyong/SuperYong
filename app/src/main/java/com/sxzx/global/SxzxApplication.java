package com.sxzx.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 *
 */
public class SxzxApplication extends Application {
    private static Context context;
    private static Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mHandler = new Handler();
        //ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        //初始化
    }
    public static Context getContext(){
        return context;
    }
    public static Handler getmHandler(){
        return mHandler;
    }
}
