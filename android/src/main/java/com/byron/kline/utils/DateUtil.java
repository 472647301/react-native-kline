package com.byron.kline.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.byron.kline.utils
 * @FileName     : DateUtil.java
 * @Author       : chao
 * @Date         : 2019/4/8
 * @Email        : icechliu@gmail.com
 * @version      : V1
 *************************************************************************/
public class DateUtil {
    @SuppressLint("SimpleDateFormat")
    public static SimpleDateFormat longTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @SuppressLint("SimpleDateFormat")
    public static SimpleDateFormat MMddHHmmTimeFormat = new SimpleDateFormat("MM/dd HH:mm");
    @SuppressLint("SimpleDateFormat")
    public static SimpleDateFormat HHmmTimeFormat = new SimpleDateFormat("HH:mm");
    @SuppressLint("SimpleDateFormat")
    public static SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyy-MM-dd");

}
