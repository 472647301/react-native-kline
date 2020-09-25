package com.byron.kline;

import com.byron.kline.model.KLineEntity;

public class KChartBean  extends KLineEntity {
    /*-------------------------------------*/
    //实现数据返回
    @Override
    public Long getDate() {
        return id * 1000;
    }

    @Override
    public float getOpenPrice() {
        return (float) open;
    }

    @Override
    public float getHighPrice() {
        return (float) high;
    }

    @Override
    public float getLowPrice() {
        return (float) low;
    }

    @Override
    public float getClosePrice() {
        return (float) close;
    }

    @Override
    public float getVolume() {
        return (float) vol;
    }

    /*-------------------------------------*/


    public double open;
    public double close;
    public double high;
    public double low;
    public double vol;
    public long id;
}
