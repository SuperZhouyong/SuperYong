package com.sxzx.utils;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.koolearn.klibrary.ui.android.R;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;

/**
 * Created by Administrator
 * on 2016/10/25.
 */

public class GlideImageLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
       /* Glide.with(activity).load(new File(path))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_image_loading)
                .error(R.mipmap.ic_empty_picture)
                .fitCenter()
                .crossFade().into(imageView);*/
        Glide.with(activity)                             //配置上下文
                .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(R.mipmap.mybookpage2)           //设置错误图片
                .placeholder(R.mipmap.mybookpage2)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {
    }
}
