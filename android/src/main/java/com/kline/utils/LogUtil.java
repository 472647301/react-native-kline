package com.kline.utils;

import android.util.Log;

import com.kline.BuildConfig;


/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.kline.utils
 * @FileName     : LogUtil.java
 * @Author       : chao
 * @Date         : 2019/4/10
 * @Email        : icechliu@gmail.com
 * @version      : V1
 *************************************************************************/
public class LogUtil {
    private final static String TAG = "LOG=>";

    public static void e(Object o) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, String.valueOf(o));
        }
    }
}
