package com.byron.kline.adapter;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Looper;

import com.byron.kline.adapter.IAdapter;


/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.byron.kline.utils
 * @FileName     : BaseKLineChartAdapter.java
 * @Author       : chao
 * @Date         : 2019/4/8
 * @Email        : icechliu@gmail.com
 * @version      : V1
 *************************************************************************/

public abstract class BaseKLineChartAdapter<T> implements IAdapter<T> {

    private Handler handler = new Handler(Looper.getMainLooper());
    private DataSetObservable mDataSetObservable = new DataSetObservable();

    private Runnable notifyDataChangeRunable = () -> mDataSetObservable.notifyChanged();
    private Runnable notifyDataWillChangeRunnable = () -> mDataSetObservable.notifyInvalidated();

    @Override
    public void notifyDataSetChanged() {
        handler.post(notifyDataChangeRunable);
    }


    @Override
    public void notifyDataWillChanged() {
        handler.post(notifyDataWillChangeRunnable);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.unregisterObserver(observer);
    }
}
