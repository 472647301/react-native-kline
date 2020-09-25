package com.byron.kline.model;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.byron.kline.entity
 * @FileName     : DepthEntity.java
 * @Author       : chao
 * @Date         : 2019/4/9
 * @Email        : icechliu@gmail.com
 * @version      : V1
 *************************************************************************/


public class DepthEntity implements com.byron.kline.model.IDepth {

    private float price;
    private float vol;

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public float getVol() {
        return vol;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setVol(float vol) {
        this.vol = vol;
    }
}
