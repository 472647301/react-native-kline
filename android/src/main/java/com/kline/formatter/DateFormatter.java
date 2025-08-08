package com.kline.formatter;

import com.kline.utils.DateUtil;

import java.util.Date;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.kline.formatter
 * @FileName     : DateFormatter.java
 * @Author       : chao
 * @Date         : 2019/4/8
 * @Email        : icechliu@gmail.com
 * @version      : V1
 *************************************************************************/

public class DateFormatter implements IDateTimeFormatter {
    @Override
    public String format(Date date) {
        if (null != date) {
            return DateUtil.yyyyMMddFormat.format(date);
        } else {
            return "";
        }
    }
}
