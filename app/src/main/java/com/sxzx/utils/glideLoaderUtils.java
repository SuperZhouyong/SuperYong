package com.sxzx.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.koolearn.klibrary.ui.android.R;

import java.io.File;

/**
 * Description : 图片加载工具类 使用glide框架封装
 */
public class glideLoaderUtils {

    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(placeholder)
                .error(error).crossFade().into(imageView);
    }
    public static void ItemintoView(Context mContext , ImageView imageView , String url ){
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }  Glide.with(mContext).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_image_loading)
                .error(R.mipmap.ic_empty_picture)
                .centerCrop()
                .crossFade().into(imageView);


    }
    public static void ItemintoViewADDailog(Context mContext , ImageView imageView , String url ){
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }  Glide.with(mContext).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_image_loading)
                .error(R.mipmap.ic_empty_picture)
                .fitCenter()
                .crossFade().into(imageView);


    }
    public static void display(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .placeholder(R.mipmap.ic_image_loading)
                .error(R.mipmap.ic_empty_picture)
                .crossFade().into(imageView);
    }
    public static void displayIcon(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()

                .error(R.mipmap.logicon)
                .crossFade().into(imageView);
    }

    public static void display(Context context, ImageView imageView, File url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.mipmap.ic_image_loading)
                .error(R.mipmap.ic_empty_picture)
                .crossFade().into(imageView);
    }
    public static void displaySmallPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_image_loading)
                .error(R.mipmap.ic_empty_picture)
                .thumbnail(0.5f)
                .into(imageView);
    }
    public static void displayBigPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_image_loading)
                .error(R.mipmap.ic_empty_picture)
                .into(imageView);
    }
    public static void display(Context context, ImageView imageView, int url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .crossFade().into(imageView);
    }
    public static void displayRoundint(Context context, ImageView imageView, int url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.logicon)
                .centerCrop().transform(new GlideRoundTransformUtil(context)).into(imageView);
    }
    public static void   displayRound(Context context,ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.logicon)
                .centerCrop().transform(new GlideRoundTransformUtil(context)).into(imageView);
    }

}
