package com.sxzx.base.Utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/9/1.
 */
public class LogUtils {
   private static Boolean isdebug =true ;

    public static void v(String tag, String msg) {
        if(isdebug){

            Log.v(tag, msg);

        }

    }

    public static void d(String tag, String msg) {
        if(isdebug){

            Log.d(tag, msg);

        }

    }

    public static void i(String tag, String msg) {
        if(isdebug){

            Log.i(tag, msg);

        }
    }


    public static void w(String tag, String msg) {
        if(isdebug){

            Log.w(tag, msg);

        }

    }

    public static void e(String tag, String msg) {
        if(isdebug){

            Log.e(tag, msg);

        }

    }


}
