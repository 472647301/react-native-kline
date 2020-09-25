package com.byron.kline.formatter;

import com.byron.kline.utils.DateUtil;

import java.util.Date;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.byron.kline.utils
 * @FileName     : DateFormatter.java
 * @Author       : chao
 * @Date         : 2019/4/8
 * @Email        : icechliu@gmail.com
 * @version      : V1
 *************************************************************************/

public class DateFormatter implements IDateTimeFormatter {
    public static int period = -1;

    @Override
    public String format(Date date) {
        if (null != date) {
            if (period < 60) {
                return DateUtil.HHmmTimeFormat.format(date);
            } else if (period < 60 * 24) {
                return DateUtil.HHmmTimeFormat.format(date);
            } else  {
                return DateUtil.yyyyMMddFormat.format(date);
            }
        } else {
            return "";
        }
    }
}
