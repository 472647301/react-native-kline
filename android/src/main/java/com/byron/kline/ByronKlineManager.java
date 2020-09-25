package com.byron.kline;

import android.annotation.SuppressLint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.byron.kline.adapter.KLineChartAdapter;
import com.byron.kline.formatter.ValueFormatter;
import com.byron.kline.view.KChartView;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import org.jetbrains.annotations.NotNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Locale;
import java.util.Map;


public class ByronKlineManager extends ViewGroupManager {

    private LinearLayout _mContainer;
    private ThemedReactContext _mContext;
    private KChartView _chartView;
    private KLineChartAdapter _adapter;
    private List<KChartBean> _datas;
    private int _pricePrecision = 2;
    private int _volumePrecision = 2;

    public static final String REACT_CLASS = "ByronKline";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public View createViewInstance(ThemedReactContext context) {
        _mContext = context;
        @SuppressLint("InflateParams") View layout = LayoutInflater.from(context).inflate(R.layout.kline, null);
        layout.setLayoutParams(
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );
        _mContainer = layout.findViewById(R.id.container);
        return layout;
    }

    @ReactProp(name = "datas")
    public void setDatas(View view, ReadableArray datas) {
        int size = datas.toArrayList().size();
        if (size == 0) {
            return;
        }
        Gson gson = new Gson();
        String json = gson.toJson(datas.toArrayList());
        _datas = gson.fromJson(
                json,
                new TypeToken<List<KChartBean>>() {
                }.getType()
        );
        if (_chartView == null || _adapter == null) {
            return;
        }
        _adapter.resetData(_datas, true);
    }

    @ReactProp(name = "locales")
    public void setLocales(View view, ReadableArray locales) {
        Log.d("setLocales", "value: " + locales);
    }

    @ReactProp(name = "indicators")
    public void setIndicators(View view, ReadableArray indicators) {
        Log.d("setIndicators", "value: " + indicators);
    }

    @ReactProp(name = "pricePrecision")
    public void setPricePrecision(View view, int pricePrecision) {
        _pricePrecision = pricePrecision;
        if (_chartView == null || _adapter == null) {
            return;
        }
        _chartView.setValueFormatter(
                new ValueFormatter() {
                    @Override
                    public String format(float value) {
                        return String.format(Locale.CHINA, "%." + _pricePrecision + "f", value);
                    }
                }
        );
    }

    @ReactProp(name = "volumePrecision")
    public void setVolumePrecision(View view, int volumePrecision) {
        _volumePrecision = volumePrecision;
        if (_chartView == null || _adapter == null) {
            return;
        }
        _chartView.setVolFormatter(
                new ValueFormatter() {
                    @Override
                    public String format(float value) {
                        return String.format(Locale.CHINA, "%." + _volumePrecision + "f", value);
                    }
                }
        );
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(
                "byronController", 1001
        );

    }

    @Override
    public void receiveCommand(@NotNull View view, int commandId, @Nullable ReadableArray args) {
        assert args != null;
        Gson gson = new Gson();
        List<Object> arrayList = args.toArrayList();
        String json = gson.toJson(arrayList.get(0));
        ByronController options = gson.fromJson(
                json,
                new TypeToken<ByronController>() {
                }.getType()
        );
        if (options.event.equals("init")) {
            initChartView();
        }
        if (options.event.equals("update") && _adapter != null) {
            List<KChartBean> datas = _adapter.getDatas();
            if (datas.size() < 2) {
                return;
            }
            KChartBean bar = options.list.get(0);
            int count = datas.size();
            int last1 = count - 1;
            int last2 = count - 2;
            long differ = datas.get(last1).id - datas.get(last2).id;
            long limit = datas.get(last1).id + differ;
            if (bar.id < limit) {
                bar.id = datas.get(last1).id;
                _adapter.changeItem(last1, bar);
            } else {
                _adapter.addLast(bar);
            }
        }
        if (options.event.equals("add") && _adapter != null) {
            _adapter.addFooterData(options.list);
        }
    }

    private void initChartView() {
        _adapter = new KLineChartAdapter();
        _mContainer.setVisibility(View.VISIBLE);
        _chartView = _mContainer.findViewById(R.id.kLineChartView);
        DisplayMetrics dm2 = _mContext.getResources().getDisplayMetrics();
        _chartView.setGridColumns(5).setGridRows(5).setOverScrollRange(dm2.widthPixels / 5);
        _chartView.setAdapter(_adapter).setValueFormatter(
                new ValueFormatter() {
                    @Override
                    public String format(float value) {
                        return String.format(Locale.CHINA, "%." + _pricePrecision + "f", value);
                    }
                }
        ).setVolFormatter(
                new ValueFormatter() {
                    @Override
                    public String format(float value) {
                        return String.format(Locale.CHINA, "%." + _volumePrecision + "f", value);
                    }
                }
        );

        if (_datas != null) {
            _adapter.resetData(_datas, true);
        }
    }
}
