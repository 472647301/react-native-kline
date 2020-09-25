package com.byron.kline.model;

import java.io.Serializable;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.byron.kline.utils
 * @FileName     : IWR.java
 * @Author       : chao
 * @Date         : 2019/4/8
 * @Email        : icechliu@gmail.com
 * @version      : V1
 *************************************************************************/
public interface IWR extends Serializable {

    /**
     * %R值
     */
    Float getWrOne();

    Float getWrTwo();

    Float getWrThree();

}