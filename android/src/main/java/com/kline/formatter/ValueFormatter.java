package com.kline.formatter;


import java.util.Locale;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.kline.utils
 * @FileName     : ValueFormatter.java
 * @Author       : chao
 * @Date         : 2019/4/8
 * @Email        : icechliu@gmail.com
 * @version      : V1
 *************************************************************************/

public class ValueFormatter implements IValueFormatter {
    @Override
    public String format(double value) {
        return String.format(Locale.CHINA, "%.2f", value);
    }
}
