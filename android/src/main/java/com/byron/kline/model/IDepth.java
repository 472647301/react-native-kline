package com.byron.kline.model;

import java.io.Serializable;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.byron.kline.entity
 * @FileName     : IDepth.java
 * @Author       : chao
 * @Date         : 2019/4/8
 * @Email        : icechliu@gmail.com
 * @version      : V1
 *************************************************************************/
public interface IDepth extends Serializable {

    float getPrice();

    float getVol();
}
