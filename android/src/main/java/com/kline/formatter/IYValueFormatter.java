package com.kline.formatter;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.kline.utils
 * @FileName     : IValueFormatter.java
 * @Author       : chao
 * @Date         : 2019/4/8
 * @Email        : icechliu@gmail.com
 * @version      : V1
 *************************************************************************/
public interface IYValueFormatter {

    /**
     * 格式化value
     *
     * @param value 传入的value值
     * @return 返回字符串
     */
    String format(double max,double min,double value);
}
