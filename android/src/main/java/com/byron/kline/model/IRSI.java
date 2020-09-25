package com.byron.kline.model;

import java.io.Serializable;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.byron.kline.utils
 * @FileName     : IRSI.java
 * @Author       : chao
 * @Date         : 2019/4/8
 * @Email        : icechliu@gmail.com
 * @version      : V1
 *************************************************************************/

public interface IRSI extends Serializable {

    /**
     * RSI值
     */
    float getRsiOne();

    float getRsiTwo();

    float getRsiThree();

}
