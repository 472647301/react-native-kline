package com.kline.adapter;

import android.util.Log;

import com.kline.model.KLineEntity;
import com.kline.utils.DataTools;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.kline.adapter
 * @FileName     : KLineChartAdapter.java
 * @Author       : chao
 * @Date         : 2019/4/8
 * @Email        : icechliu@gmail.com
 * @version      : V1
 *************************************************************************/

public class KLineChartAdapter<T extends KLineEntity> extends BaseKLineChartAdapter<T> {

    private int dataCount;

    private boolean resetShowPosition,resetLastAnim;

    public List<T> getDataSource() {
        return dataSource;
    }

    private DataTools dataTools;

    public <Q extends DataTools> void setDataTools(Q dataTools) {
        this.dataTools = dataTools;
    }

    private final List<T> dataSource = new ArrayList<>();
    private float[] points;

    public float[] getPoints() {
        return points;
    }

    public KLineChartAdapter() {
        dataTools = new DataTools();
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Date getDate(int position) {
        if (position >= dataCount) {
            return new Date();
        }
        return new Date(dataSource.get(position).getDate());
    }

    @Override
    public long getDateMillion(int position) {
        return dataSource.get(position).getDate();
    }

    /**
     * 重置K线数据
     *
     * @param data              K线数据
     * @param resetShowPosition 重置K线显示位置default true,如不需重置K线传入false
     */
    public void resetData(List<T> data, boolean resetShowPosition){
        resetData(data,resetShowPosition,false);
    }

    /**
     * 重置K线数据
     *
     * @param data              K线数据
     * @param resetShowPosition 重置K线显示位置default true,如不需重置K线传入false
     * @param resetLastAnim     清楚最后一要柱子的动画,如果切换币需要使用方法传true
     */
    public void resetData(List<T> data, boolean resetShowPosition, boolean resetLastAnim) {
        notifyDataWillChanged();
        dataSource.clear();
        dataSource.addAll(data);
        this.dataCount = dataSource.size();
        if (dataCount > 0) {
            points = dataTools.calculate(dataSource);
        } else {
            points = new float[]{};
        }
        setResetShowPosition(resetShowPosition);
        setResetLastAnim(resetLastAnim);
        notifyDataSetChanged();
    }

    /**
     * 重置K线数据
     *
     * @param data K线数据
     */
    public void resetData(List<T> data) {
        resetData(data, true);
    }

    /**
     * 通知K线显示位置发和变化,需要重置时需先设置resetShowPosition为true后调用此方法
     */
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    /**
     * 向尾部添加数据
     */
    public void addLast(T entity) {
        addLast(entity, false);
    }

    /**
     * 向尾部添加数据
     */
    public void addLast(T entity, boolean resetShowPosition) {
        if (null != entity) {
            dataSource.add(entity);
            this.dataCount++;
            points = dataTools.calculate(dataSource);
            setResetShowPosition(resetShowPosition);
            notifyDataSetChanged();
        }
    }

    /**
     * 向前面追加多个数据
     */
    public void appendData(List<T> data) {
      dataSource.addAll(0, data);
      this.dataCount = dataSource.size();
      points = dataTools.calculate(dataSource);
      notifyDataSetChanged();
    }

    /**
     * 改变某个点的值
     *
     * @param position 索引值
     */
    public void changeItem(int position, T data) {
        dataSource.set(position, data);
        points = dataTools.calculate(dataSource);
        notifyDataSetChanged();
    }

    public void setResetLastAnim(boolean resetLastAnim) {
        this.resetLastAnim = resetLastAnim;
    }

    public boolean getResetShowPosition() {
        return resetShowPosition;
    }

    public void setResetShowPosition(boolean resetShowPosition) {
        this.resetShowPosition = resetShowPosition;
    }

    @Override
    public boolean getResetLastAnim() {
        return resetLastAnim;
    }
}
