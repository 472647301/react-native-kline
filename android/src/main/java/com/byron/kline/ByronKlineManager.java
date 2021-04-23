package com.byron.kline;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.byron.kline.adapter.KLineChartAdapter;
import com.byron.kline.formatter.ValueFormatter;
import com.byron.kline.utils.SlidListener;
import com.byron.kline.utils.Status;
import com.byron.kline.view.KChartView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import org.jetbrains.annotations.NotNull;

import com.facebook.react.uimanager.events.RCTEventEmitter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ByronKlineManager extends ViewGroupManager<ViewGroup> {

    public static final String REACT_CLASS = "ByronKline";
    private LinearLayout _mContainer;
    private ThemedReactContext _mContext;
    private KChartView _chartView;
    private KLineChartAdapter<KChartBean> _adapter;
    private int _pricePrecision = 2;
    private int _volumePrecision = 2;
    private Boolean _requestStatus = false;
    private String _selectedInfoBoxTextColor;
    private String _selectedInfoBoxBorderColor;
    private String _selectedInfoBoxBackgroundColor;
    private String _macdIncreaseColor;
    private String _macdDecreaseColor;

    @NotNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NotNull
    @Override
    public ViewGroup createViewInstance(@NotNull ThemedReactContext context) {
        _mContext = context;
        @SuppressLint("InflateParams") View layout = LayoutInflater.from(context).inflate(R.layout.kline, null);
        layout.setLayoutParams(
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );
        _mContainer = layout.findViewById(R.id.container);
        _adapter = new KLineChartAdapter<>();
        _mContainer.setVisibility(View.VISIBLE);
        _chartView = _mContainer.findViewById(R.id.kLineChartView);
        return _mContainer;
    }

    @ReactProp(name = "selectedXLabelBackgroundColor")
    public void setSelectedXLabelBackgroundColor(ViewGroup view, String selectedXLabelBackgroundColor) {
        _chartView.setSelectedXLabelBackgroundColor(Color.parseColor(selectedXLabelBackgroundColor));
    }

    @ReactProp(name = "priceLabelInLineTextColor")
    public void setPriceLabelInLineTextColor(ViewGroup view, String priceLabelInLineTextColor) {
        _chartView.setPriceLabelInLineTextColor(Color.parseColor(priceLabelInLineTextColor));
    }

    @ReactProp(name = "priceLabelInLineTextSize")
    public void setPriceLabelInLineTextSize(ViewGroup view, float priceLabelInLineTextSize) {
        _chartView.setPriceLabelInLineTextSize(priceLabelInLineTextSize);
    }

    @ReactProp(name = "selectedLabelTextColor")
    public void setSelectedLabelTextColor(ViewGroup view, String selectedLabelTextColor) {
        _chartView.setSelectedXLabelTextColor(Color.parseColor(selectedLabelTextColor));
    }

    @ReactProp(name = "selectedLabelTextSize")
    public void setSelectedLabelTextSize(ViewGroup view, float selectedLabelTextSize) {
        _chartView.setSelectedXLabelTextSize(selectedLabelTextSize);
    }

    @ReactProp(name = "priceLabelInLineBoxMarginRight")
    public void setPriceLabelInLineBoxMarginRight(ViewGroup view, float priceLabelInLineBoxMarginRight) {
        _chartView.setPriceLabelInLineMarginRight(priceLabelInLineBoxMarginRight);
    }

    @ReactProp(name = "priceLabelInLineShapeWidth")
    public void setPriceLabelInLineShapeWidth(ViewGroup view, float priceLabelInLineShapeWidth) {
        _chartView.setPriceLabelInLineShapeWidth(priceLabelInLineShapeWidth);
    }

    @ReactProp(name = "priceLabelInLineShapeHeight")
    public void setPriceLabelInLineShapeHeight(ViewGroup view, float priceLabelInLineShapeHeight) {
        _chartView.setPriceLabelInLineShapeHeight(priceLabelInLineShapeHeight);
    }

    @ReactProp(name = "priceLabelInLineBoxHeight")
    public void setPriceLabelInLineBoxHeight(ViewGroup view, float priceLabelInLineBoxHeight) {
        _chartView.setPriceLabelInLineBoxHeight(priceLabelInLineBoxHeight);
    }

    @ReactProp(name = "priceLabelInLineBoxPadding")
    public void setPriceLabelInLineBoxPadding(ViewGroup view, float priceLabelInLineBoxPadding) {
        _chartView.setPriceLabelInLineBoxPadding(priceLabelInLineBoxPadding);
    }

    @ReactProp(name = "priceLabelInLineBoxShapeTextMargin")
    public void setPriceLabelInLineBoxShapeTextMargin(View view, float priceLabelInLineBoxShapeTextMargin) {
        _chartView.setPriceLabelInLineShapeTextMargin(priceLabelInLineBoxShapeTextMargin);
    }

    @ReactProp(name = "priceLabelInLineClickable")
    public void setPriceLabelInLineClickable(ViewGroup view, Boolean priceLabelInLineClickable) {
        _chartView.setPriceLabelInLineClickable(priceLabelInLineClickable);
    }

    @ReactProp(name = "priceLabelInLineBoxBackgroundColor")
    public void setPriceLabelInLineBoxBackgroundColor(ViewGroup view, String priceLabelInLineBoxBackgroundColor) {
        _chartView.setPriceLabelInLineBoxBackgroundColor(Color.parseColor(priceLabelInLineBoxBackgroundColor));
    }

    @ReactProp(name = "priceLabelRightBackgroundColor")
    public void setPriceLabelRightBackgroundColor(ViewGroup view, String priceLabelRightBackgroundColor) {
        _chartView.setPriceLabelRightBackgroundColor(Color.parseColor(priceLabelRightBackgroundColor));
    }

    @ReactProp(name = "priceLabelInLineBoxBorderColor")
    public void setPriceLabelInLineBoxBorderColor(ViewGroup view, String priceLabelInLineBoxBorderColor) {
        _chartView.setPriceLabelInLineBoxBorderColor(Color.parseColor(priceLabelInLineBoxBorderColor));
    }

    @ReactProp(name = "priceLabelInLineBoxBorderWidth")
    public void setPriceLabelInLineBoxBorderWidth(ViewGroup view, float priceLabelInLineBoxBorderWidth) {
        _chartView.setPriceLabelInLineBoxBorderWidth(priceLabelInLineBoxBorderWidth);
    }

    @ReactProp(name = "priceLabelInLineBoxRadius")
    public void setPriceLabelInLineBoxRadius(ViewGroup view, float priceLabelInLineBoxRadius) {
        _chartView.setPriceLabelInLineBoxRadius(priceLabelInLineBoxRadius);
    }

    @ReactProp(name = "priceLineRightLabelBackGroundAlpha")
    public void setPriceLineRightLabelBackGroundAlpha(ViewGroup view, int priceLineRightLabelBackGroundAlpha) {
        _chartView.setPriceLabelRightBackgroundAlpha(priceLineRightLabelBackGroundAlpha);
    }

    @ReactProp(name = "priceLabelRightTextColor")
    public void setPriceLabelRightTextColor(ViewGroup view, String priceLabelRightTextColor) {
        _chartView.setPriceLabelRightTextColor(Color.parseColor(priceLabelRightTextColor));
    }

    @ReactProp(name = "priceLineRightColor")
    public void setPriceLineRightColor(ViewGroup view, String priceLineRightColor) {
        _chartView.setPriceLineRightColor(Color.parseColor(priceLineRightColor));
    }

    @ReactProp(name = "priceLineWidth")
    public void setPriceLineWidth(ViewGroup view, float priceLineWidth) {
        _chartView.setPriceLineWidth(priceLineWidth);
    }

    @ReactProp(name = "priceLineColor")
    public void setPriceLineColor(ViewGroup view, String priceLineColor) {
        _chartView.setPriceLineColor(Color.parseColor(priceLineColor));
    }

    @ReactProp(name = "priceLineDotSolidWidth")
    public void setPriceLineDotSolidWidth(ViewGroup view, float priceLineDotSolidWidth) {
        _chartView.setPriceLineDotSolidWidth(priceLineDotSolidWidth);
    }

    @ReactProp(name = "priceLineDotStrokeWidth")
    public void setPriceLineDotStrokeWidth(ViewGroup view, float priceLineDotStrokeWidth) {
        _chartView.setPriceLineDotStrokeWidth(priceLineDotStrokeWidth);
    }

    @ReactProp(name = "selectedXLineWidth")
    public void setSelectedXLineWidth(ViewGroup view, float selectedXLineWidth) {
        _chartView.setSelectedXLineWidth(selectedXLineWidth);
    }

    @ReactProp(name = "selectedYLineWidth")
    public void setSelectedYLineWidth(ViewGroup view, float selectedYLineWidth) {
        _chartView.setSelectedYLineWidth(selectedYLineWidth);
    }

    @ReactProp(name = "selectedXLineColor")
    public void setSelectedXLineColor(ViewGroup view, String selectedXLineColor) {
        _chartView.setSelectedXLineColor(Color.parseColor(selectedXLineColor));
    }

    @ReactProp(name = "selectedYLineColor")
    public void setSelectedYLineColor(ViewGroup view, String selectedYLineColor) {
        _chartView.setSelectedYLineColor(Color.parseColor(selectedYLineColor));
    }

    @ReactProp(name = "selectedYColor")
    public void setSelectedYColor(ViewGroup view, String selectedYColor) {
        _chartView.setSelectedYColor(Color.parseColor(selectedYColor));
    }

    @ReactProp(name = "selectedCrossBigColor")
    public void setSelectedCrossBigColor(ViewGroup view, String selectedCrossBigColor) {
        _chartView.setSelectedCrossBigColor(Color.parseColor(selectedCrossBigColor));
    }

    @ReactProp(name = "selectedCrossPointRadius")
    public void setSelectedCrossPointRadius(ViewGroup view, float selectedCrossPointRadius) {
        _chartView.setSelectedPointRadius(selectedCrossPointRadius);
    }

    @ReactProp(name = "selectedCrossPointColor")
    public void setSelectedCrossPointColor(ViewGroup view, String selectedCrossPointColor) {
        _chartView.setSelectedPointColor(Color.parseColor(selectedCrossPointColor));
    }

    @ReactProp(name = "selectedShowCrossPoint")
    public void setSelectedShowCrossPoint(ViewGroup view, Boolean selectedShowCrossPoint) {
        _chartView.setSelectedShowCrossPoint(selectedShowCrossPoint);
    }

    @ReactProp(name = "selectedPriceBoxBackgroundColor")
    public void setSelectedPriceBoxBackgroundColor(ViewGroup view, String selectedPriceBoxBackgroundColor) {
        _chartView.setSelectedPriceBoxBackgroundColor(Color.parseColor(selectedPriceBoxBackgroundColor));
    }

    @ReactProp(name = "selectedInfoTextSize")
    public void setSelectedInfoTextSize(ViewGroup view, float selectedInfoTextSize) {
        _chartView.setSelectedInfoTextSize(selectedInfoTextSize);
    }

    @ReactProp(name = "selectedPriceBoxHorizontalPadding")
    public void setSelectedPriceBoxHorizontalPadding(ViewGroup view, float selectedPriceBoxHorizontalPadding) {
        _chartView.setSelectedPriceBoxHorizentalPadding(selectedPriceBoxHorizontalPadding);
    }

    @ReactProp(name = "selectedPriceBoxVerticalPadding")
    public void setSelectedPriceBoxVerticalPadding(ViewGroup view, float selectedPriceBoxVerticalPadding) {
        _chartView.setSelectedPriceboxVerticalPadding(selectedPriceBoxVerticalPadding);
    }

    @ReactProp(name = "selectedInfoBoxPadding")
    public void setSelectedInfoBoxPadding(ViewGroup view, float selectedInfoBoxPadding) {
        _chartView.setSelectInfoBoxPadding(selectedInfoBoxPadding);
    }

    @ReactProp(name = "selectedInfoBoxMargin")
    public void setSelectedInfoBoxMargin(ViewGroup view, float selectedInfoBoxMargin) {
        _chartView.setSelectInfoBoxMargin(selectedInfoBoxMargin);
    }

    @ReactProp(name = "selectedInfoBoxTextColor")
    public void setSelectedInfoBoxTextColor(ViewGroup view, String selectedInfoBoxTextColor) {
        _selectedInfoBoxTextColor = selectedInfoBoxTextColor;
        _chartView.setSelectInfoBoxColors(
                Color.parseColor(_selectedInfoBoxTextColor),
                Color.parseColor(_selectedInfoBoxBorderColor),
                Color.parseColor(_selectedInfoBoxBackgroundColor)
        );
    }

    @ReactProp(name = "selectedInfoBoxBorderColor")
    public void setSelectedInfoBoxBorderColor(ViewGroup view, String selectedInfoBoxBorderColor) {
        _selectedInfoBoxBorderColor = selectedInfoBoxBorderColor;
        _chartView.setSelectInfoBoxColors(
                Color.parseColor(_selectedInfoBoxTextColor),
                Color.parseColor(_selectedInfoBoxBorderColor),
                Color.parseColor(_selectedInfoBoxBackgroundColor)
        );
    }

    @ReactProp(name = "selectedInfoBoxBackgroundColor")
    public void setSelectedInfoBoxBackgroundColor(ViewGroup view, String selectedInfoBoxBackgroundColor) {
        _selectedInfoBoxBackgroundColor = selectedInfoBoxBackgroundColor;
        _chartView.setSelectInfoBoxColors(
                Color.parseColor(_selectedInfoBoxTextColor),
                Color.parseColor(_selectedInfoBoxBorderColor),
                Color.parseColor(_selectedInfoBoxBackgroundColor)
        );
    }

    @ReactProp(name = "selectedLabelBorderWidth")
    public void setSelectedLabelBorderWidth(ViewGroup view, float selectedLabelBorderWidth) {
        _chartView.setSelectedXLabelBorderWidth(selectedLabelBorderWidth);
    }

    @ReactProp(name = "selectedLabelBorderColor")
    public void setSelectedLabelBorderColor(ViewGroup view, String selectedLabelBorderColor) {
        _chartView.setSelectedXLabelBorderColor(Color.parseColor(selectedLabelBorderColor));
    }

    @ReactProp(name = "backgroundFillTopColor")
    public void setBackgroundFillTopColor(ViewGroup view, String backgroundFillTopColor) {
        _chartView.setBackGroundFillTopColor(Color.parseColor(backgroundFillTopColor));
    }

    @ReactProp(name = "backgroundFillBottomColor")
    public void setBackgroundFillBottomColor(ViewGroup view, String backgroundFillBottomColor) {
        _chartView.setBackGroundFillBottomColor(Color.parseColor(backgroundFillBottomColor));
    }

    @ReactProp(name = "timeLineColor")
    public void setTimeLineColor(ViewGroup view, String timeLineColor) {
        _chartView.setTimeLineColor(Color.parseColor(timeLineColor));
    }

    @ReactProp(name = "timeLineFillTopColor")
    public void setTimeLineFillTopColor(ViewGroup view, String timeLineFillTopColor) {
        _chartView.setTimeLineFillTopColor(Color.parseColor(timeLineFillTopColor));
    }

    @ReactProp(name = "timeLineFillBottomColor")
    public void setTimeLineFillBottomColor(ViewGroup view, String timeLineFillBottomColor) {
        _chartView.setTimeLineFillBottomColor(Color.parseColor(timeLineFillBottomColor));
    }

    @ReactProp(name = "timeLineEndPointColor")
    public void setTimeLineEndPointColor(ViewGroup view, String timeLineEndPointColor) {
        _chartView.setTimeLineEndColor(Color.parseColor(timeLineEndPointColor));
    }

    @ReactProp(name = "timeLineEndRadius")
    public void setTimeLineEndRadius(ViewGroup view, float timeLineEndRadius) {
        _chartView.setTimeLineEndRadius(timeLineEndRadius);
    }

    @ReactProp(name = "selectedDateBoxVerticalPadding")
    public void setSelectedDateBoxVerticalPadding(ViewGroup view, float selectedDateBoxVerticalPadding) {
        _chartView.setSelectedDateBoxVerticalPadding(selectedDateBoxVerticalPadding);
    }

    @ReactProp(name = "selectedDateBoxHorizontalPadding")
    public void setSelectedDateBoxHorizontalPadding(ViewGroup view, float selectedDateBoxHorizontalPadding) {
        _chartView.setSelectedDateBoxHorizontalPadding(selectedDateBoxHorizontalPadding);
    }

    @ReactProp(name = "mainLegendMarginTop")
    public void setMainLegendMarginTop(ViewGroup view, float mainLegendMarginTop) {
        _chartView.setMainLegendMarginTop(mainLegendMarginTop);
    }

    @ReactProp(name = "legendMarginLeft")
    public void setLegendMarginLeft(ViewGroup view, float legendMarginLeft) {
        _chartView.setLegendMarginLeft(legendMarginLeft);
    }

    @ReactProp(name = "betterXLabel")
    public void setBetterXLabel(ViewGroup view, Boolean betterXLabel) {
        _chartView.setBetterX(betterXLabel);
    }

    @ReactProp(name = "labelTextSize")
    public void setLabelTextSize(ViewGroup view, float labelTextSize) {
        _chartView.setXLabelTextSize(labelTextSize);
        _chartView.setYLabelTextSize(labelTextSize);
    }

    @ReactProp(name = "labelTextColor")
    public void setLabelTextColor(ViewGroup view, String labelTextColor) {
        _chartView.setXlabelTextColor(Color.parseColor(labelTextColor));
        _chartView.setYLabelTextColor(Color.parseColor(labelTextColor));
    }

    @ReactProp(name = "betterSelectedXLabel")
    public void setBetterSelectedXLabel(ViewGroup view, Boolean betterSelectedXLabel) {
        _chartView.setBetterSelectedX(betterSelectedXLabel);
    }

    @ReactProp(name = "commonTextSize")
    public void setCommonTextSize(ViewGroup view, float commonTextSize) {
        _chartView.setCommonTextSize(commonTextSize);
    }

    @ReactProp(name = "mainMarginTop")
    public void setMainMarginTop(ViewGroup view, float mainMarginTop) {
        _chartView.setMainLegendMarginTop(mainMarginTop);
    }

    @ReactProp(name = "paddingBottom")
    public void setPaddingBottom(ViewGroup view, float paddingBottom) {
        _chartView.setChartPaddingBottom(paddingBottom);
    }

    @ReactProp(name = "childPaddingTop")
    public void setChildPaddingTop(ViewGroup view, float childPaddingTop) {
        _chartView.setChildPaddingTop(childPaddingTop);
    }

    @ReactProp(name = "commonTextColor")
    public void setCommonTextColor(ViewGroup view, String commonTextColor) {
        _chartView.setCommonTextColor(Color.parseColor(commonTextColor));
    }

    @ReactProp(name = "lineWidth")
    public void setLineWidth(ViewGroup view, float lineWidth) {
        _chartView.setLineWidth(lineWidth);
    }

    @ReactProp(name = "candleWidth")
    public void setCandleWidth(ViewGroup view, float candleWidth) {
        _chartView.setCandleWidth(candleWidth);
    }

    @ReactProp(name = "candleLineWidth")
    public void setCandleLineWidth(ViewGroup view, float candleLineWidth) {
        _chartView.setCandleLineWidth(candleLineWidth);
    }

    @ReactProp(name = "limitTextColor")
    public void setLimitTextColor(ViewGroup view, String limitTextColor) {
        _chartView.setLimitTextColor(Color.parseColor(limitTextColor));
    }

    @ReactProp(name = "candleHollow")
    public void setCandleHollow(ViewGroup view, int candleHollow) {
        _chartView.setCandleSolid(Status.HollowModel.getStrokeModel(candleHollow));
    }

    @ReactProp(name = "gridLineWidth")
    public void setGridLineWidth(ViewGroup view, float gridLineWidth) {
        _chartView.setGridLineWidth(gridLineWidth);
    }

    @ReactProp(name = "gridLineColor")
    public void setGridLineColor(ViewGroup view, String gridLineColor) {
        _chartView.setGridLineColor(Color.parseColor(gridLineColor));
    }

    @ReactProp(name = "gridLineRows")
    public void setGridLineRows(ViewGroup view, int gridLineRows) {
        _chartView.setGridRows(gridLineRows);
    }

    @ReactProp(name = "gridLineColumns")
    public void setGridLineColumns(ViewGroup view, int gridLineColumns) {
        _chartView.setGridColumns(gridLineColumns);
    }

    @ReactProp(name = "macdStrokeWidth")
    public void setMacdStrokeWidth(ViewGroup view, float macdStrokeWidth) {
        _chartView.setMacdStrockWidth(macdStrokeWidth);
    }

    @ReactProp(name = "macdIncreaseColor")
    public void setMacdIncreaseColor(ViewGroup view, String macdIncreaseColor) {
        _macdIncreaseColor = macdIncreaseColor;
        _chartView.setMacdChartColor(
                Color.parseColor(_macdIncreaseColor),
                Color.parseColor(_macdDecreaseColor)
        );
    }

    @ReactProp(name = "macdDecreaseColor")
    public void setMacdDecreaseColor(ViewGroup view, String macdDecreaseColor) {
        _macdDecreaseColor = macdDecreaseColor;
        _chartView.setMacdChartColor(
                Color.parseColor(_macdIncreaseColor),
                Color.parseColor(_macdDecreaseColor)
        );
    }

    @ReactProp(name = "macdWidth")
    public void setMacdWidth(ViewGroup view, float macdWidth) {
        _chartView.setMACDWidth(macdWidth);
    }

    @ReactProp(name = "difColor")
    public void setDifColor(ViewGroup view, String difColor) {
        _chartView.setDIFColor(Color.parseColor(difColor));
    }

    @ReactProp(name = "deaColor")
    public void setDeaColor(ViewGroup view, String deaColor) {
        _chartView.setDEAColor(Color.parseColor(deaColor));
    }

    @ReactProp(name = "macdColor")
    public void setMacdColor(ViewGroup view, String macdColor) {
        _chartView.setMACDColor(Color.parseColor(macdColor));
    }

    @ReactProp(name = "wr1Color")
    public void setWr1Color(ViewGroup view, String wr1Color) {
        _chartView.setR1Color(Color.parseColor(wr1Color));
    }

    @ReactProp(name = "wr2Color")
    public void setWr2Color(ViewGroup view, String wr2Color) {
        _chartView.setR2Color(Color.parseColor(wr2Color));
    }

    @ReactProp(name = "wr3Color")
    public void setWr3Color(ViewGroup view, String wr3Color) {
        _chartView.setR3Color(Color.parseColor(wr3Color));
    }

    @ReactProp(name = "kColor")
    public void setKColor(ViewGroup view, String kColor) {
        _chartView.setKColor(Color.parseColor(kColor));
    }

    @ReactProp(name = "dColor")
    public void setDColor(ViewGroup view, String dColor) {
        _chartView.setDColor(Color.parseColor(dColor));
    }

    @ReactProp(name = "jColor")
    public void setJColor(ViewGroup view, String jColor) {
        _chartView.setJColor(Color.parseColor(jColor));
    }

    @ReactProp(name = "rsi1Color")
    public void setRsi1Color(ViewGroup view, String rsi1Color) {
        _chartView.setRSI1Color(Color.parseColor(rsi1Color));
    }

    @ReactProp(name = "rsi2Color")
    public void setRsi2Color(ViewGroup view, String rsi2Color) {
        _chartView.setRSI2Color(Color.parseColor(rsi2Color));
    }

    @ReactProp(name = "rsi3Color")
    public void setRsi3Color(ViewGroup view, String rsi3Color) {
        _chartView.setRSI3Color(Color.parseColor(rsi3Color));
    }

    @ReactProp(name = "ma1Color")
    public void setMa1Color(ViewGroup view, String ma1Color) {
        _chartView.setMa1Color(Color.parseColor(ma1Color));
    }

    @ReactProp(name = "ma2Color")
    public void setMa2Color(ViewGroup view, String ma2Color) {
        _chartView.setMa2Color(Color.parseColor(ma2Color));
    }

    @ReactProp(name = "ma3Color")
    public void setMa3Color(ViewGroup view, String ma3Color) {
        _chartView.setMa3Color(Color.parseColor(ma3Color));
    }

    @ReactProp(name = "volMa1Color")
    public void setVolMa1Color(ViewGroup view, String volMa1Color) {
        _chartView.setVolMa1Color(Color.parseColor(volMa1Color));
    }

    @ReactProp(name = "volMa2Color")
    public void setVolMa2Color(ViewGroup view, String volMa2Color) {
        _chartView.setVolMa2Color(Color.parseColor(volMa2Color));
    }

    @ReactProp(name = "volLegendColor")
    public void setVolLegendColor(ViewGroup view, String volLegendColor) {
        _chartView.setVolLegendColor(Color.parseColor(volLegendColor));
    }

    @ReactProp(name = "volLineChartColor")
    public void setVolLineChartColor(ViewGroup view, String volLineChartColor) {
        _chartView.setVolLineChartColor(Color.parseColor(volLineChartColor));
    }

    @ReactProp(name = "volLegendMarginTop")
    public void setVolLegendMarginTop(ViewGroup view, float volLegendMarginTop) {
        _chartView.setVolLegendMarginTop(volLegendMarginTop);
    }

    @ReactProp(name = "mainBackgroundColor")
    public void setMainBackgroundColor(ViewGroup view, String mainBackgroundColor) {
        _chartView.setBackgroundColor(Color.parseColor(mainBackgroundColor));
    }

    @ReactProp(name = "datas")
    public void setDatas(ViewGroup view, ReadableArray datas) {
        if (datas == null) {
            return;
        }
        int size = datas.toArrayList().size();
        if (size == 0) {
            return;
        }
        Gson gson = new Gson();
        String json = gson.toJson(datas.toArrayList());
        List<KChartBean> list = gson.fromJson(
                json,
                new TypeToken<List<KChartBean>>() {
                }.getType()
        );
        _adapter.resetData(list, true);
    }

    @ReactProp(name = "locales")
    public void setLocales(ViewGroup view, ReadableArray locales) {
        if (locales.toArrayList().size() == 0) {
            return;
        }
        Gson gson = new Gson();
        String json = gson.toJson(locales.toArrayList());
        String[] list = gson.fromJson(
                json,
                new TypeToken<String[]>() {
                }.getType()
        );
        _chartView.setSelectedInfoLabels(list);
    }

    @ReactProp(name = "indicators")
    public void setIndicators(ViewGroup view, ReadableArray indicators) {
        ArrayList<Object> list = indicators.toArrayList();
        if (list.size() == 0) {
            initKLineState();
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            String status = String.valueOf(list.get(i));
            changeKLineState(status);
        }
    }

    @ReactProp(name = "pricePrecision")
    public void setPricePrecision(ViewGroup view, int pricePrecision) {
        _pricePrecision = pricePrecision;
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
    public void setVolumePrecision(ViewGroup view, int volumePrecision) {
        _volumePrecision = volumePrecision;
        _chartView.setVolFormatter(
                new ValueFormatter() {
                    @Override
                    public String format(float value) {
                        return String.format(Locale.CHINA, "%." + _volumePrecision + "f", value);
                    }
                }
        );
    }

    @ReactProp(name = "increaseColor")
    public void setIncreaseColor(ViewGroup view, String increaseColor) {
        _chartView.setIncreaseColor(Color.parseColor(increaseColor));
    }

    @ReactProp(name = "decreaseColor")
    public void setDecreaseColor(ViewGroup view, String decreaseColor) {
        _chartView.setDecreaseColor(Color.parseColor(decreaseColor));
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(
                "byronController", 1001
        );

    }

    @Override
    public void receiveCommand(@NotNull ViewGroup view, String commandId, @Nullable ReadableArray args) {
        assert args != null;
//        System.out.println(">> receiveCommand:" + args.toString());
        if (!commandId.equals("ByronKline")) {
            return;
        }
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
            if (_requestStatus) {
                _requestStatus = false;
            }
        }
    }

    @Override
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.<String, Object>builder().put(
                "onMoreKLineData",
                MapBuilder.of(
                        "phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onRNMoreKLineData"))
        ).build();
    }

    public void onReceiveNativeEvent() {
        List<KChartBean> list = _adapter.getDatas();
        WritableMap event = Arguments.createMap();
        event.putDouble("id", list.get(0).id);
        _mContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                _mContainer.getId(),
                "onMoreKLineData",
                event
        );
    }

    private void initChartView() {
        DisplayMetrics dm2 = _mContext.getResources().getDisplayMetrics();
        _chartView.setGridColumns(5).setGridRows(5).setOverScrollRange((float) (dm2.widthPixels / 5.0));
        _chartView.setAdapter(_adapter);
        _chartView.setSlidListener(new SlidListener() {
            @Override
            public void onSlidLeft() {
                if (_requestStatus) {
                    return;
                }
                _requestStatus = true;
                onReceiveNativeEvent();
            }

            @Override
            public void onSlidRight() {

            }
        });
    }

    private void initKLineState() {
        if (_chartView == null) {
            return;
        }
        _chartView.hideSelectData();
        _chartView.changeMainDrawType(Status.MainStatus.NONE);
        _chartView.setIndexDraw(Status.IndexStatus.NONE);
        _chartView.setKlineState(Status.KlineStatus.K_LINE);
        _chartView.setVolShowState(false);
    }

    private void changeKLineState(Object status) {
        if (status == null) {
            return;
        }
        if ("0.0".equals(status)) { // 显示MA
            _chartView.hideSelectData();
            _chartView.changeMainDrawType(Status.MainStatus.MA);
        } else if ("1.0".equals(status)) {  // BOLL
            _chartView.hideSelectData();
            _chartView.changeMainDrawType(Status.MainStatus.BOLL);
        } else if ("2.0".equals(status)) { // MainStateNONE
            _chartView.hideSelectData();
            _chartView.changeMainDrawType(Status.MainStatus.NONE);
        } else if ("3.0".equals(status)) { // SecondaryStateMacd
            _chartView.hideSelectData();
            _chartView.setIndexDraw(Status.IndexStatus.MACD);
        } else if ("4.0".equals(status)) { // SecondaryStateKDJ
            _chartView.hideSelectData();
            _chartView.setIndexDraw(Status.IndexStatus.KDJ);
        } else if ("5.0".equals(status)) { // SecondaryStateRSI
            _chartView.hideSelectData();
            _chartView.setIndexDraw(Status.IndexStatus.RSI);
        } else if ("6.0".equals(status)) { // SecondaryStateWR
            _chartView.hideSelectData();
            _chartView.setIndexDraw(Status.IndexStatus.WR);
        } else if ("7.0".equals(status)) { // SecondaryStateNONE
            _chartView.hideSelectData();
            _chartView.setIndexDraw(Status.IndexStatus.NONE);
        } else if ("8.0".equals(status)) { // ShowLine 显示分时图
            _chartView.hideSelectData();
            _chartView.setKlineState(Status.KlineStatus.TIME_LINE);
        } else if ("9.0".equals(status)) { // HideLine 隐藏分时图
            _chartView.hideSelectData();
            _chartView.setKlineState(Status.KlineStatus.K_LINE);
        } else if ("10.0".equals(status)) { // 显示成交量
            _chartView.setVolShowState(true);
        } else if ("11.0".equals(status)) { // 隐藏成交量
            _chartView.setVolShowState(false);
        }
    }
}
