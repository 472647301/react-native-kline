package com.byron.kline;

import android.annotation.SuppressLint;
import android.graphics.Color;
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
import com.byron.kline.utils.SlidListener;
import com.byron.kline.utils.Status;
import com.byron.kline.view.KChartView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
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

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static java.security.AccessController.getContext;


public class ByronKlineManager extends ViewGroupManager {

    public static final String REACT_CLASS = "ByronKline";
    private LinearLayout _mContainer;
    private ThemedReactContext _mContext;
    private KChartView _chartView;
    private KLineChartAdapter _adapter;
    private ReadableArray _datas;
    private int _pricePrecision = 2;
    private int _volumePrecision = 2;
    private ReadableArray _locales;
    private ReadableArray _indicators;
    private Boolean _requestStatus = false;
    private String _increaseColor = "#00BD9A";
    private String _decreaseColor = "#FF6960";
    private String _selectedXLabelBackgroundColor;
    private String _priceLabelInLineTextColor;
    private float _priceLabelInLineTextSize;
    private String _selectedLabelTextColor;
    private float _selectedLabelTextSize;
    private float _priceLabelInLineBoxMarginRight;
    private float _priceLabelInLineShapeWidth;
    private float _priceLabelInLineShapeHeight;
    private float _priceLabelInLineBoxHeight;
    private float _priceLabelInLineBoxPadding;
    private float _priceLabelInLineBoxShapeTextMargin;
    private Boolean _priceLabelInLineClickable;
    private String _priceLabelInLineBoxBackgroundColor;
    private String _priceLabelRightBackgroundColor;
    private String _priceLabelInLineBoxBorderColor;
    private float _priceLabelInLineBoxBorderWidth;
    private float _priceLabelInLineBoxRadius;
    private int _priceLineRightLabelBackGroundAlpha;
    private String _priceLabelRightTextColor;
    private String _priceLineRightColor;
    private float _priceLineWidth;
    private String _priceLineColor;
    private float _priceLineDotSolidWidth;
    private float _priceLineDotStrokeWidth;
    private float _selectedXLineWidth;
    private float _selectedYLineWidth;
    private String _selectedXLineColor;
    private String _selectedYLineColor;
    private String _selectedYColor;
    private String _selectedCrossBigColor;
    private float _selectedCrossPointRadius;
    private String _selectedCrossPointColor;
    private Boolean _selectedShowCrossPoint;
    private String _selectedPriceBoxBackgroundColor;
    private float _selectedInfoTextSize;
    private float _selectedPriceBoxHorizontalPadding;
    private float _selectedPriceBoxVerticalPadding;
    private float _selectedInfoBoxPadding;
    private float _selectedInfoBoxMargin;
    private String _selectedInfoBoxTextColor;
    private String _selectedInfoBoxBorderColor;
    private String _selectedInfoBoxBackgroundColor;
    private float _selectedLabelBorderWidth;
    private String _selectedLabelBorderColor;
    private String _backgroundFillTopColor;
    private String _backgroundFillBottomColor;
    private String _timeLineColor;
    private String _timeLineFillTopColor;
    private String _timeLineFillBottomColor;
    private String _timeLineEndPointColor;
    private float _timeLineEndRadius;
    private float _selectedDateBoxVerticalPadding;
    private float _selectedDateBoxHorizontalPadding;
    private float _mainLegendMarginTop;
    private float _legendMarginLeft;
    private Boolean _betterXLabel;
    private float _labelTextSize;
    private String _labelTextColor;
    private Boolean _yLabelAlign;
    private Boolean _betterSelectedXLabel;
    private float _commonTextSize;
    private float _mainMarginTop;
    private float _paddingBottom;
    private float _childPaddingTop;
    private String _commonTextColor;
    private float _lineWidth;
    private float _candleWidth;
    private float _candleLineWidth;
    private String _limitTextColor;
    private int _candleHollow;
    private float _gridLineWidth;
    private String _gridLineColor;
    private int _gridLineRows;
    private int _gridLineColumns;
    private float _macdStrokeWidth;
    private String _macdIncreaseColor;
    private String _macdDecreaseColor;
    private float _macdWidth;
    private String _difColor;
    private String _deaColor;
    private String _macdColor;
    private String _wr1Color;
    private String _wr2Color;
    private String _wr3Color;
    private String _kColor;
    private String _dColor;
    private String _jColor;
    private String _rsi1Color;
    private String _rsi2Color;
    private String _rsi3Color;
    private String _ma1Color;
    private String _ma2Color;
    private String _ma3Color;
    private String _volMa1Color;
    private String _volMa2Color;
    private String _volLegendColor;
    private String _volLineChartColor;
    private float _volLegendMarginTop;
    private String _mainBackgroundColor;

    @NotNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NotNull
    @Override
    public View createViewInstance(@NotNull ThemedReactContext context) {
        _mContext = context;
        @SuppressLint("InflateParams") View layout = LayoutInflater.from(context).inflate(R.layout.kline, null);
        layout.setLayoutParams(
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );
        _mContainer = layout.findViewById(R.id.container);
        _adapter = new KLineChartAdapter();
        _mContainer.setVisibility(View.VISIBLE);
        _chartView = _mContainer.findViewById(R.id.kLineChartView);
        return layout;
    }

    @ReactProp(name = "selectedXLabelBackgroundColor")
    public void setSelectedXLabelBackgroundColor(View view, String selectedXLabelBackgroundColor) {
        if (selectedXLabelBackgroundColor == null) {
            return;
        }
        _selectedXLabelBackgroundColor = selectedXLabelBackgroundColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedXLabelBackgroundColor(Color.parseColor(_selectedXLabelBackgroundColor));
    }

    @ReactProp(name = "priceLabelInLineTextColor")
    public void setPriceLabelInLineTextColor(View view, String priceLabelInLineTextColor) {
        if (priceLabelInLineTextColor == null) {
            return;
        }
        _priceLabelInLineTextColor = priceLabelInLineTextColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLabelInLineTextColor(Color.parseColor(_priceLabelInLineTextColor));
    }

    @ReactProp(name = "priceLabelInLineTextSize")
    public void setPriceLabelInLineTextSize(View view, float priceLabelInLineTextSize) {
        if (priceLabelInLineTextSize > -0.000001 && priceLabelInLineTextSize < 0.000001) {
            return;
        }
        _priceLabelInLineTextSize = priceLabelInLineTextSize;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLabelInLineTextSize(_priceLabelInLineTextSize);
    }

    @ReactProp(name = "selectedLabelTextColor")
    public void setSelectedLabelTextColor(View view, String selectedLabelTextColor) {
        if (selectedLabelTextColor == null) {
            return;
        }
        _selectedLabelTextColor = selectedLabelTextColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedXLabelTextColor(Color.parseColor(_selectedLabelTextColor));
    }

    @ReactProp(name = "selectedLabelTextSize")
    public void setSelectedLabelTextSize(View view, float selectedLabelTextSize) {
        if (selectedLabelTextSize > -0.000001 && selectedLabelTextSize < 0.000001) {
            return;
        }
        _selectedLabelTextSize = selectedLabelTextSize;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedXLabelTextSize(_selectedLabelTextSize);
    }

    @ReactProp(name = "priceLabelInLineBoxMarginRight")
    public void setPriceLabelInLineBoxMarginRight(View view, float priceLabelInLineBoxMarginRight) {
        if (priceLabelInLineBoxMarginRight > -0.000001 && priceLabelInLineBoxMarginRight < 0.000001) {
            return;
        }
        _priceLabelInLineBoxMarginRight = priceLabelInLineBoxMarginRight;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLabelInLineMarginRight(_priceLabelInLineBoxMarginRight);
    }

    @ReactProp(name = "priceLabelInLineShapeWidth")
    public void setPriceLabelInLineShapeWidth(View view, float priceLabelInLineShapeWidth) {
        if (priceLabelInLineShapeWidth > -0.000001 && priceLabelInLineShapeWidth < 0.000001) {
            return;
        }
        _priceLabelInLineShapeWidth = priceLabelInLineShapeWidth;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLabelInLineShapeWidth(_priceLabelInLineShapeWidth);
    }

    @ReactProp(name = "priceLabelInLineShapeHeight")
    public void setPriceLabelInLineShapeHeight(View view, float priceLabelInLineShapeHeight) {
        if (priceLabelInLineShapeHeight > -0.000001 && priceLabelInLineShapeHeight < 0.000001) {
            return;
        }
        _priceLabelInLineShapeHeight = priceLabelInLineShapeHeight;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLabelInLineShapeHeight(_priceLabelInLineShapeHeight);
    }

    @ReactProp(name = "priceLabelInLineBoxHeight")
    public void setPriceLabelInLineBoxHeight(View view, float priceLabelInLineBoxHeight) {
        if (priceLabelInLineBoxHeight > -0.000001 && priceLabelInLineBoxHeight < 0.000001) {
            return;
        }
        _priceLabelInLineBoxHeight = priceLabelInLineBoxHeight;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLabelInLineBoxHeight(_priceLabelInLineBoxHeight);
    }

    @ReactProp(name = "priceLabelInLineBoxPadding")
    public void setPriceLabelInLineBoxPadding(View view, float priceLabelInLineBoxPadding) {
        if (priceLabelInLineBoxPadding > -0.000001 && priceLabelInLineBoxPadding < 0.000001) {
            return;
        }
        _priceLabelInLineBoxPadding = priceLabelInLineBoxPadding;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLabelInLineBoxPadding(_priceLabelInLineBoxPadding);
    }

    @ReactProp(name = "priceLabelInLineBoxShapeTextMargin")
    public void setPriceLabelInLineBoxShapeTextMargin(View view, float priceLabelInLineBoxShapeTextMargin) {
        if (priceLabelInLineBoxShapeTextMargin > -0.000001 && priceLabelInLineBoxShapeTextMargin < 0.000001) {
            return;
        }
        _priceLabelInLineBoxShapeTextMargin = priceLabelInLineBoxShapeTextMargin;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLabelInLineShapeTextMargin(_priceLabelInLineBoxShapeTextMargin);
    }

    @ReactProp(name = "priceLabelInLineClickable")
    public void setPriceLabelInLineClickable(View view, Boolean priceLabelInLineClickable) {
        if (priceLabelInLineClickable == null) {
            return;
        }
        _priceLabelInLineClickable = priceLabelInLineClickable;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLabelInLineClickable(_priceLabelInLineClickable);
    }

    @ReactProp(name = "priceLabelInLineBoxBackgroundColor")
    public void setPriceLabelInLineBoxBackgroundColor(View view, String priceLabelInLineBoxBackgroundColor) {
        if (priceLabelInLineBoxBackgroundColor == null) {
            return;
        }
        _priceLabelInLineBoxBackgroundColor = priceLabelInLineBoxBackgroundColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLabelInLineBoxBackgroundColor(Color.parseColor(_priceLabelInLineBoxBackgroundColor));
    }

    @ReactProp(name = "priceLabelRightBackgroundColor")
    public void setPriceLabelRightBackgroundColor(View view, String priceLabelRightBackgroundColor) {
        if (priceLabelRightBackgroundColor == null) {
            return;
        }
        _priceLabelRightBackgroundColor = priceLabelRightBackgroundColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLabelRightBackgroundColor(Color.parseColor(_priceLabelRightBackgroundColor));
    }

    @ReactProp(name = "priceLabelInLineBoxBorderColor")
    public void setPriceLabelInLineBoxBorderColor(View view, String priceLabelInLineBoxBorderColor) {
        if (priceLabelInLineBoxBorderColor == null) {
            return;
        }
        _priceLabelInLineBoxBorderColor = priceLabelInLineBoxBorderColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLabelInLineBoxBorderColor(Color.parseColor(_priceLabelInLineBoxBorderColor));
    }

    @ReactProp(name = "priceLabelInLineBoxBorderWidth")
    public void setPriceLabelInLineBoxBorderWidth(View view, float priceLabelInLineBoxBorderWidth) {
        if (priceLabelInLineBoxBorderWidth > -0.000001 && priceLabelInLineBoxBorderWidth < 0.000001) {
            return;
        }
        _priceLabelInLineBoxBorderWidth = priceLabelInLineBoxBorderWidth;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLabelInLineBoxBorderWidth(_priceLabelInLineBoxBorderWidth);
    }

    @ReactProp(name = "priceLabelInLineBoxRadius")
    public void setPriceLabelInLineBoxRadius(View view, float priceLabelInLineBoxRadius) {
        if (priceLabelInLineBoxRadius > -0.000001 && priceLabelInLineBoxRadius < 0.000001) {
            return;
        }
        _priceLabelInLineBoxRadius = priceLabelInLineBoxRadius;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLabelInLineBoxRadius(_priceLabelInLineBoxRadius);
    }

    @ReactProp(name = "priceLineRightLabelBackGroundAlpha")
    public void setPriceLineRightLabelBackGroundAlpha(View view, int priceLineRightLabelBackGroundAlpha) {
        if (priceLineRightLabelBackGroundAlpha == 0) {
            return;
        }
        _priceLineRightLabelBackGroundAlpha = priceLineRightLabelBackGroundAlpha;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLabelRightBackgroundAlpha(_priceLineRightLabelBackGroundAlpha);
    }

    @ReactProp(name = "priceLabelRightTextColor")
    public void setPriceLabelRightTextColor(View view, String priceLabelRightTextColor) {
        if (priceLabelRightTextColor == null) {
            return;
        }
        _priceLabelRightTextColor = priceLabelRightTextColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLabelRightTextColor(Color.parseColor(_priceLabelRightTextColor));
    }

    @ReactProp(name = "priceLineRightColor")
    public void setPriceLineRightColor(View view, String priceLineRightColor) {
        if (priceLineRightColor == null) {
            return;
        }
        _priceLineRightColor = priceLineRightColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLineRightColor(Color.parseColor(_priceLineRightColor));
    }

    @ReactProp(name = "priceLineWidth")
    public void setPriceLineWidth(View view, float priceLineWidth) {
        if (priceLineWidth > -0.000001 && priceLineWidth < 0.000001) {
            return;
        }
        _priceLineWidth = priceLineWidth;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLineWidth(_priceLineWidth);
    }

    @ReactProp(name = "priceLineColor")
    public void setPriceLineColor(View view, String priceLineColor) {
        if (priceLineColor == null) {
            return;
        }
        _priceLineColor = priceLineColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLineColor(Color.parseColor(_priceLineColor));
    }

    @ReactProp(name = "priceLineDotSolidWidth")
    public void setPriceLineDotSolidWidth(View view, float priceLineDotSolidWidth) {
        if (priceLineDotSolidWidth > -0.000001 && priceLineDotSolidWidth < 0.000001) {
            return;
        }
        _priceLineDotSolidWidth = priceLineDotSolidWidth;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLineDotSolidWidth(_priceLineDotSolidWidth);
    }

    @ReactProp(name = "priceLineDotStrokeWidth")
    public void setPriceLineDotStrokeWidth(View view, float priceLineDotStrokeWidth) {
        if (priceLineDotStrokeWidth > -0.000001 && priceLineDotStrokeWidth < 0.000001) {
            return;
        }
        _priceLineDotStrokeWidth = priceLineDotStrokeWidth;
        if (_chartView == null) {
            return;
        }
        _chartView.setPriceLineDotStrokeWidth(_priceLineDotStrokeWidth);
    }

    @ReactProp(name = "selectedXLineWidth")
    public void setSelectedXLineWidth(View view, float selectedXLineWidth) {
        if (selectedXLineWidth > -0.000001 && selectedXLineWidth < 0.000001) {
            return;
        }
        _selectedXLineWidth = selectedXLineWidth;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedXLineWidth(_selectedXLineWidth);
    }

    @ReactProp(name = "selectedYLineWidth")
    public void setSelectedYLineWidth(View view, float selectedYLineWidth) {
        if (selectedYLineWidth > -0.000001 && selectedYLineWidth < 0.000001) {
            return;
        }
        _selectedYLineWidth = selectedYLineWidth;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedYLineWidth(_selectedYLineWidth);
    }

    @ReactProp(name = "selectedXLineColor")
    public void setSelectedXLineColor(View view, String selectedXLineColor) {
        if (selectedXLineColor == null) {
            return;
        }
        _selectedXLineColor = selectedXLineColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedXLineColor(Color.parseColor(_selectedXLineColor));
    }

    @ReactProp(name = "selectedYLineColor")
    public void setSelectedYLineColor(View view, String selectedYLineColor) {
        if (selectedYLineColor == null) {
            return;
        }
        _selectedYLineColor = selectedYLineColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedYLineColor(Color.parseColor(_selectedYLineColor));
    }

    @ReactProp(name = "selectedYColor")
    public void setSelectedYColor(View view, String selectedYColor) {
        if (selectedYColor == null) {
            return;
        }
        _selectedYColor = selectedYColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedYColor(Color.parseColor(_selectedYColor));
    }

    @ReactProp(name = "selectedCrossBigColor")
    public void setSelectedCrossBigColor(View view, String selectedCrossBigColor) {
        if (selectedCrossBigColor == null) {
            return;
        }
        _selectedCrossBigColor = selectedCrossBigColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedCrossBigColor(Color.parseColor(_selectedCrossBigColor));
    }

    @ReactProp(name = "selectedCrossPointRadius")
    public void setSelectedCrossPointRadius(View view, float selectedCrossPointRadius) {
        if (selectedCrossPointRadius > -0.000001 && selectedCrossPointRadius < 0.000001) {
            return;
        }
        _selectedCrossPointRadius = selectedCrossPointRadius;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedPointRadius(_selectedCrossPointRadius);
    }

    @ReactProp(name = "selectedCrossPointColor")
    public void setSelectedCrossPointColor(View view, String selectedCrossPointColor) {
        if (selectedCrossPointColor == null) {
            return;
        }
        _selectedCrossPointColor = selectedCrossPointColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedPointColor(Color.parseColor(_selectedCrossPointColor));
    }

    @ReactProp(name = "selectedShowCrossPoint")
    public void setSelectedShowCrossPoint(View view, Boolean selectedShowCrossPoint) {
        if (selectedShowCrossPoint == null) {
            return;
        }
        _selectedShowCrossPoint = selectedShowCrossPoint;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedShowCrossPoint(_selectedShowCrossPoint);
    }

    @ReactProp(name = "selectedPriceBoxBackgroundColor")
    public void setSelectedPriceBoxBackgroundColor(View view, String selectedPriceBoxBackgroundColor) {
        if (selectedPriceBoxBackgroundColor == null) {
            return;
        }
        _selectedPriceBoxBackgroundColor = selectedPriceBoxBackgroundColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedPriceBoxBackgroundColor(Color.parseColor(_selectedPriceBoxBackgroundColor));
    }

    @ReactProp(name = "selectedInfoTextSize")
    public void setSelectedInfoTextSize(View view, float selectedInfoTextSize) {
        if (selectedInfoTextSize > -0.000001 && selectedInfoTextSize < 0.000001) {
            return;
        }
        _selectedInfoTextSize = selectedInfoTextSize;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedInfoTextSize(_selectedInfoTextSize);
    }

    @ReactProp(name = "selectedPriceBoxHorizontalPadding")
    public void setSelectedPriceBoxHorizontalPadding(View view, float selectedPriceBoxHorizontalPadding) {
        if (selectedPriceBoxHorizontalPadding > -0.000001 && selectedPriceBoxHorizontalPadding < 0.000001) {
            return;
        }
        _selectedPriceBoxHorizontalPadding = selectedPriceBoxHorizontalPadding;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedPriceBoxHorizentalPadding(_selectedPriceBoxHorizontalPadding);
    }

    @ReactProp(name = "selectedPriceBoxVerticalPadding")
    public void setSelectedPriceBoxVerticalPadding(View view, float selectedPriceBoxVerticalPadding) {
        if (selectedPriceBoxVerticalPadding > -0.000001 && selectedPriceBoxVerticalPadding < 0.000001) {
            return;
        }
        _selectedPriceBoxVerticalPadding = selectedPriceBoxVerticalPadding;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedPriceboxVerticalPadding(_selectedPriceBoxVerticalPadding);
    }

    @ReactProp(name = "selectedInfoBoxPadding")
    public void setSelectedInfoBoxPadding(View view, float selectedInfoBoxPadding) {
        if (selectedInfoBoxPadding > -0.000001 && selectedInfoBoxPadding < 0.000001) {
            return;
        }
        _selectedInfoBoxPadding = selectedInfoBoxPadding;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectInfoBoxPadding(_selectedInfoBoxPadding);
    }

    @ReactProp(name = "selectedInfoBoxMargin")
    public void setSelectedInfoBoxMargin(View view, float selectedInfoBoxMargin) {
        if (selectedInfoBoxMargin > -0.000001 && selectedInfoBoxMargin < 0.000001) {
            return;
        }
        _selectedInfoBoxMargin = selectedInfoBoxMargin;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectInfoBoxMargin(_selectedInfoBoxMargin);
    }

    @ReactProp(name = "selectedInfoBoxTextColor")
    public void setSelectedInfoBoxTextColor(View view, String selectedInfoBoxTextColor) {

        _selectedInfoBoxTextColor = selectedInfoBoxTextColor;
        if (_chartView == null) {
            return;
        }
        if (_selectedInfoBoxTextColor == null || _selectedInfoBoxBorderColor == null || _selectedInfoBoxBackgroundColor == null) {
            return;
        }
        _chartView.setSelectInfoBoxColors(
                Color.parseColor(_selectedInfoBoxTextColor),
                Color.parseColor(_selectedInfoBoxBorderColor),
                Color.parseColor(_selectedInfoBoxBackgroundColor)
        );
    }

    @ReactProp(name = "selectedInfoBoxBorderColor")
    public void setSelectedInfoBoxBorderColor(View view, String selectedInfoBoxBorderColor) {
        _selectedInfoBoxBorderColor = selectedInfoBoxBorderColor;
        if (_chartView == null) {
            return;
        }
        if (_selectedInfoBoxTextColor == null || _selectedInfoBoxBorderColor == null || _selectedInfoBoxBackgroundColor == null) {
            return;
        }
        _chartView.setSelectInfoBoxColors(
                Color.parseColor(_selectedInfoBoxTextColor),
                Color.parseColor(_selectedInfoBoxBorderColor),
                Color.parseColor(_selectedInfoBoxBackgroundColor)
        );
    }

    @ReactProp(name = "selectedInfoBoxBackgroundColor")
    public void setSelectedInfoBoxBackgroundColor(View view, String selectedInfoBoxBackgroundColor) {
        _selectedInfoBoxBackgroundColor = selectedInfoBoxBackgroundColor;
        if (_chartView == null) {
            return;
        }
        if (_selectedInfoBoxTextColor == null || _selectedInfoBoxBorderColor == null || _selectedInfoBoxBackgroundColor == null) {
            return;
        }
        _chartView.setSelectInfoBoxColors(
                Color.parseColor(_selectedInfoBoxTextColor),
                Color.parseColor(_selectedInfoBoxBorderColor),
                Color.parseColor(_selectedInfoBoxBackgroundColor)
        );
    }

    @ReactProp(name = "selectedLabelBorderWidth")
    public void setSelectedLabelBorderWidth(View view, float selectedLabelBorderWidth) {
        if (selectedLabelBorderWidth > -0.000001 && selectedLabelBorderWidth < 0.000001) {
            return;
        }
        _selectedLabelBorderWidth = selectedLabelBorderWidth;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedXLabelBorderWidth(_selectedLabelBorderWidth);
    }

    @ReactProp(name = "selectedLabelBorderColor")
    public void setSelectedLabelBorderColor(View view, String selectedLabelBorderColor) {
        if (selectedLabelBorderColor == null) {
            return;
        }
        _selectedLabelBorderColor = selectedLabelBorderColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedXLabelBorderColor(Color.parseColor(_selectedLabelBorderColor));
    }

    @ReactProp(name = "backgroundFillTopColor")
    public void setBackgroundFillTopColor(View view, String backgroundFillTopColor) {
        if (backgroundFillTopColor == null) {
            return;
        }
        _backgroundFillTopColor = backgroundFillTopColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setBackGroundFillTopColor(Color.parseColor(_backgroundFillTopColor));
    }

    @ReactProp(name = "backgroundFillBottomColor")
    public void setBackgroundFillBottomColor(View view, String backgroundFillBottomColor) {
        if (backgroundFillBottomColor == null) {
            return;
        }
        _backgroundFillBottomColor = backgroundFillBottomColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setBackGroundFillBottomColor(Color.parseColor(_backgroundFillBottomColor));
    }

    @ReactProp(name = "timeLineColor")
    public void setTimeLineColor(View view, String timeLineColor) {
        if (timeLineColor == null) {
            return;
        }
        _timeLineColor = timeLineColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setTimeLineColor(Color.parseColor(_timeLineColor));
    }

    @ReactProp(name = "timeLineFillTopColor")
    public void setTimeLineFillTopColor(View view, String timeLineFillTopColor) {
        if (timeLineFillTopColor == null) {
            return;
        }
        _timeLineFillTopColor = timeLineFillTopColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setTimeLineFillTopColor(Color.parseColor(_timeLineFillTopColor));
    }

    @ReactProp(name = "timeLineFillBottomColor")
    public void setTimeLineFillBottomColor(View view, String timeLineFillBottomColor) {
        if (timeLineFillBottomColor == null) {
            return;
        }
        _timeLineFillBottomColor = timeLineFillBottomColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setTimeLineFillBottomColor(Color.parseColor(_timeLineFillBottomColor));
    }

    @ReactProp(name = "timeLineEndPointColor")
    public void setTimeLineEndPointColor(View view, String timeLineEndPointColor) {
        if (timeLineEndPointColor == null) {
            return;
        }
        _timeLineEndPointColor = timeLineEndPointColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setTimeLineEndColor(Color.parseColor(_timeLineEndPointColor));
    }

    @ReactProp(name = "timeLineEndRadius")
    public void setTimeLineEndRadius(View view, float timeLineEndRadius) {
        if (timeLineEndRadius > -0.000001 && timeLineEndRadius < 0.000001) {
            return;
        }
        _timeLineEndRadius = timeLineEndRadius;
        if (_chartView == null) {
            return;
        }
        _chartView.setTimeLineEndRadius(_timeLineEndRadius);
    }

    @ReactProp(name = "selectedDateBoxVerticalPadding")
    public void setSelectedDateBoxVerticalPadding(View view, float selectedDateBoxVerticalPadding) {
        if (selectedDateBoxVerticalPadding > -0.000001 && selectedDateBoxVerticalPadding < 0.000001) {
            return;
        }
        _selectedDateBoxVerticalPadding = selectedDateBoxVerticalPadding;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedDateBoxVerticalPadding(_selectedDateBoxVerticalPadding);
    }

    @ReactProp(name = "selectedDateBoxHorizontalPadding")
    public void setSelectedDateBoxHorizontalPadding(View view, float selectedDateBoxHorizontalPadding) {
        if (selectedDateBoxHorizontalPadding > -0.000001 && selectedDateBoxHorizontalPadding < 0.000001) {
            return;
        }
        _selectedDateBoxHorizontalPadding = selectedDateBoxHorizontalPadding;
        if (_chartView == null) {
            return;
        }
        _chartView.setSelectedDateBoxHorizontalPadding(_selectedDateBoxHorizontalPadding);
    }

    @ReactProp(name = "mainLegendMarginTop")
    public void setMainLegendMarginTop(View view, float mainLegendMarginTop) {
        if (mainLegendMarginTop > -0.000001 && mainLegendMarginTop < 0.000001) {
            return;
        }
        _mainLegendMarginTop = mainLegendMarginTop;
        if (_chartView == null) {
            return;
        }
        _chartView.setMainLegendMarginTop(_mainLegendMarginTop);
    }

    @ReactProp(name = "legendMarginLeft")
    public void setLegendMarginLeft(View view, float legendMarginLeft) {
        if (legendMarginLeft > -0.000001 && legendMarginLeft < 0.000001) {
            return;
        }
        _legendMarginLeft = legendMarginLeft;
        if (_chartView == null) {
            return;
        }
        _chartView.setLegendMarginLeft(_legendMarginLeft);
    }

    @ReactProp(name = "betterXLabel")
    public void setBetterXLabel(View view, Boolean betterXLabel) {
        if (betterXLabel == null) {
            return;
        }
        _betterXLabel = betterXLabel;
        if (_chartView == null) {
            return;
        }
        _chartView.setBetterX(_betterXLabel);
    }

    @ReactProp(name = "labelTextSize")
    public void setLabelTextSize(View view, float labelTextSize) {
        if (labelTextSize > -0.000001 && labelTextSize < 0.000001) {
            return;
        }
        _labelTextSize = labelTextSize;
        if (_chartView == null) {
            return;
        }
        _chartView.setXLabelTextSize(_labelTextSize);
        _chartView.setYLabelTextSize(_labelTextSize);
    }

    @ReactProp(name = "labelTextColor")
    public void setLabelTextColor(View view, String labelTextColor) {
        if (labelTextColor == null) {
            return;
        }
        _labelTextColor = labelTextColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setXlabelTextColor(Color.parseColor(_labelTextColor));
        _chartView.setYLabelTextColor(Color.parseColor(_labelTextColor));
    }

    @ReactProp(name = "yLabelAlign")
    public void setYLabelAlign(View view, Boolean yLabelAlign) {
        if (yLabelAlign == null) {
            return;
        }
        _yLabelAlign = yLabelAlign;
        if (_chartView == null) {
            return;
        }
        _chartView.setYlabelAlign(_yLabelAlign);
    }

    @ReactProp(name = "betterSelectedXLabel")
    public void setBetterSelectedXLabel(View view, Boolean betterSelectedXLabel) {
        if (betterSelectedXLabel == null) {
            return;
        }
        _betterSelectedXLabel = betterSelectedXLabel;
        if (_chartView == null) {
            return;
        }
        _chartView.setBetterSelectedX(_betterSelectedXLabel);
    }

    @ReactProp(name = "commonTextSize")
    public void setCommonTextSize(View view, float commonTextSize) {
        if (commonTextSize > -0.000001 && commonTextSize < 0.000001) {
            return;
        }
        _commonTextSize = commonTextSize;
        if (_chartView == null) {
            return;
        }
        _chartView.setCommonTextSize(_commonTextSize);
    }

    @ReactProp(name = "mainMarginTop")
    public void setMainMarginTop(View view, float mainMarginTop) {
        if (mainMarginTop > -0.000001 && mainMarginTop < 0.000001) {
            return;
        }
        _mainMarginTop = mainMarginTop;
        if (_chartView == null) {
            return;
        }
        _chartView.setMainLegendMarginTop(_mainMarginTop);
    }

    @ReactProp(name = "paddingBottom")
    public void setPaddingBottom(View view, float paddingBottom) {
        if (paddingBottom > -0.000001 && paddingBottom < 0.000001) {
            return;
        }
        _paddingBottom = paddingBottom;
        if (_chartView == null) {
            return;
        }
        _chartView.setChartPaddingBottom(_paddingBottom);
    }

    @ReactProp(name = "childPaddingTop")
    public void setChildPaddingTop(View view, float childPaddingTop) {
        if (childPaddingTop > -0.000001 && childPaddingTop < 0.000001) {
            return;
        }
        _childPaddingTop = childPaddingTop;
        if (_chartView == null) {
            return;
        }
        _chartView.setChildPaddingTop(_childPaddingTop);
    }

    @ReactProp(name = "commonTextColor")
    public void setCommonTextColor(View view, String commonTextColor) {
        if (commonTextColor == null) {
            return;
        }
        _commonTextColor = commonTextColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setCommonTextColor(Color.parseColor(_commonTextColor));
    }

    @ReactProp(name = "lineWidth")
    public void setLineWidth(View view, float lineWidth) {
        if (lineWidth > -0.000001 && lineWidth < 0.000001) {
            return;
        }
        _lineWidth = lineWidth;
        if (_chartView == null) {
            return;
        }
        _chartView.setLineWidth(_lineWidth);
    }

    @ReactProp(name = "candleWidth")
    public void setCandleWidth(View view, float candleWidth) {
        if (candleWidth > -0.000001 && candleWidth < 0.000001) {
            return;
        }
        _candleWidth = candleWidth;
        if (_chartView == null) {
            return;
        }
        _chartView.setCandleWidth(_candleWidth);
    }


    @ReactProp(name = "candleLineWidth")
    public void setCandleLineWidth(View view, float candleLineWidth) {
        if (candleLineWidth > -0.000001 && candleLineWidth < 0.000001) {
            return;
        }
        _candleLineWidth = candleLineWidth;
        if (_chartView == null) {
            return;
        }
        _chartView.setCandleLineWidth(_candleLineWidth);
    }

    @ReactProp(name = "limitTextColor")
    public void setLimitTextColor(View view, String limitTextColor) {
        if (limitTextColor == null) {
            return;
        }
        _limitTextColor = limitTextColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setLimitTextColor(Color.parseColor(_limitTextColor));
    }

    @ReactProp(name = "candleHollow")
    public void setCandleHollow(View view, int candleHollow) {
        _candleHollow = candleHollow;
        if (_chartView == null) {
            return;
        }
        _chartView.setCandleSolid(Status.HollowModel.getStrokeModel(_candleHollow));
    }

    @ReactProp(name = "gridLineWidth")
    public void setGridLineWidth(View view, float gridLineWidth) {
        if (gridLineWidth > -0.000001 && gridLineWidth < 0.000001) {
            return;
        }
        _gridLineWidth = gridLineWidth;
        if (_chartView == null) {
            return;
        }
        _chartView.setGridLineWidth(_gridLineWidth);
    }

    @ReactProp(name = "gridLineColor")
    public void setGridLineColor(View view, String gridLineColor) {
        if (gridLineColor == null) {
            return;
        }
        _gridLineColor = gridLineColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setGridLineColor(Color.parseColor(_gridLineColor));
    }

    @ReactProp(name = "gridLineRows")
    public void setGridLineRows(View view, int gridLineRows) {
        if (gridLineRows == 0) {
            return;
        }
        _gridLineRows = gridLineRows;
        if (_chartView == null) {
            return;
        }
        _chartView.setGridRows(_gridLineRows);
    }

    @ReactProp(name = "gridLineColumns")
    public void setGridLineColumns(View view, int gridLineColumns) {
        if (gridLineColumns == 0) {
            return;
        }
        _gridLineColumns = gridLineColumns;
        if (_chartView == null) {
            return;
        }
        _chartView.setGridColumns(_gridLineColumns);
    }

    @ReactProp(name = "macdStrokeWidth")
    public void setMacdStrokeWidth(View view, float macdStrokeWidth) {
        if (macdStrokeWidth > -0.000001 && macdStrokeWidth < 0.000001) {
            return;
        }
        _macdStrokeWidth = macdStrokeWidth;
        if (_chartView == null) {
            return;
        }
        _chartView.setMacdStrockWidth(_macdStrokeWidth);
    }

    @ReactProp(name = "macdIncreaseColor")
    public void setMacdIncreaseColor(View view, String macdIncreaseColor) {
        _macdIncreaseColor = macdIncreaseColor;
        if (_chartView == null) {
            return;
        }
        if (_macdIncreaseColor == null || _macdDecreaseColor == null) {
            return;
        }
        _chartView.setMacdChartColor(
                Color.parseColor(_macdIncreaseColor),
                Color.parseColor(_macdDecreaseColor)
        );
    }

    @ReactProp(name = "macdDecreaseColor")
    public void setMacdDecreaseColor(View view, String macdDecreaseColor) {
        _macdDecreaseColor = macdDecreaseColor;
        if (_chartView == null) {
            return;
        }
        if (_macdIncreaseColor == null || _macdDecreaseColor == null) {
            return;
        }
        _chartView.setMacdChartColor(
                Color.parseColor(_macdIncreaseColor),
                Color.parseColor(_macdDecreaseColor)
        );
    }

    @ReactProp(name = "macdWidth")
    public void setMacdWidth(View view, float macdWidth) {
        if (macdWidth > -0.000001 && macdWidth < 0.000001) {
            return;
        }
        _macdWidth = macdWidth;
        if (_chartView == null) {
            return;
        }
        _chartView.setMACDWidth(_macdWidth);
    }

    @ReactProp(name = "difColor")
    public void setDifColor(View view, String difColor) {
        if (difColor == null) {
            return;
        }
        _difColor = difColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setDIFColor(Color.parseColor(_difColor));
    }

    @ReactProp(name = "deaColor")
    public void setDeaColor(View view, String deaColor) {
        if (deaColor == null) {
            return;
        }
        _deaColor = deaColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setDEAColor(Color.parseColor(_deaColor));
    }

    @ReactProp(name = "macdColor")
    public void setMacdColor(View view, String macdColor) {
        if (macdColor == null) {
            return;
        }
        _macdColor = macdColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setMACDColor(Color.parseColor(_macdColor));
    }

    @ReactProp(name = "wr1Color")
    public void setWr1Color(View view, String wr1Color) {
        if (wr1Color == null) {
            return;
        }
        _wr1Color = wr1Color;
        if (_chartView == null) {
            return;
        }
        _chartView.setR1Color(Color.parseColor(_wr1Color));
    }

    @ReactProp(name = "wr2Color")
    public void setWr2Color(View view, String wr2Color) {
        if (wr2Color == null) {
            return;
        }
        _wr2Color = wr2Color;
        if (_chartView == null) {
            return;
        }
        _chartView.setR2Color(Color.parseColor(_wr2Color));
    }

    @ReactProp(name = "wr3Color")
    public void setWr3Color(View view, String wr3Color) {
        if (wr3Color == null) {
            return;
        }
        _wr3Color = wr3Color;
        if (_chartView == null) {
            return;
        }
        _chartView.setR3Color(Color.parseColor(_wr3Color));
    }

    @ReactProp(name = "kColor")
    public void setKColor(View view, String kColor) {
        if (kColor == null) {
            return;
        }
        _kColor = kColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setKColor(Color.parseColor(_kColor));
    }

    @ReactProp(name = "dColor")
    public void setDColor(View view, String dColor) {
        if (dColor == null) {
            return;
        }
        _dColor = dColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setDColor(Color.parseColor(_dColor));
    }

    @ReactProp(name = "jColor")
    public void setJColor(View view, String jColor) {
        if (jColor == null) {
            return;
        }
        _jColor = jColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setJColor(Color.parseColor(_jColor));
    }

    @ReactProp(name = "rsi1Color")
    public void setRsi1Color(View view, String rsi1Color) {
        if (rsi1Color == null) {
            return;
        }
        _rsi1Color = rsi1Color;
        if (_chartView == null) {
            return;
        }
        _chartView.setRSI1Color(Color.parseColor(_rsi1Color));
    }

    @ReactProp(name = "rsi2Color")
    public void setRsi2Color(View view, String rsi2Color) {
        if (rsi2Color == null) {
            return;
        }
        _rsi2Color = rsi2Color;
        if (_chartView == null) {
            return;
        }
        _chartView.setRSI2Color(Color.parseColor(_rsi2Color));
    }

    @ReactProp(name = "rsi3Color")
    public void setRsi3Color(View view, String rsi3Color) {
        if (rsi3Color == null) {
            return;
        }
        _rsi3Color = rsi3Color;
        if (_chartView == null) {
            return;
        }
        _chartView.setRSI3Color(Color.parseColor(_rsi3Color));
    }

    @ReactProp(name = "ma1Color")
    public void setMa1Color(View view, String ma1Color) {
        if (ma1Color == null) {
            return;
        }
        _ma1Color = ma1Color;
        if (_chartView == null) {
            return;
        }
        _chartView.setMa1Color(Color.parseColor(_ma1Color));
    }

    @ReactProp(name = "ma2Color")
    public void setMa2Color(View view, String ma2Color) {
        if (ma2Color == null) {
            return;
        }
        _ma2Color = ma2Color;
        if (_chartView == null) {
            return;
        }
        _chartView.setMa2Color(Color.parseColor(_ma2Color));
    }

    @ReactProp(name = "ma3Color")
    public void setMa3Color(View view, String ma3Color) {
        if (ma3Color == null) {
            return;
        }
        _ma3Color = ma3Color;
        if (_chartView == null) {
            return;
        }
        _chartView.setMa3Color(Color.parseColor(_ma3Color));
    }

    @ReactProp(name = "volMa1Color")
    public void setVolMa1Color(View view, String volMa1Color) {
        if (volMa1Color == null) {
            return;
        }
        _volMa1Color = volMa1Color;
        if (_chartView == null) {
            return;
        }
        _chartView.setVolMa1Color(Color.parseColor(_volMa1Color));
    }

    @ReactProp(name = "volMa2Color")
    public void setVolMa2Color(View view, String volMa2Color) {
        if (volMa2Color == null) {
            return;
        }
        _volMa2Color = volMa2Color;
        if (_chartView == null) {
            return;
        }
        _chartView.setVolMa2Color(Color.parseColor(_volMa2Color));
    }

    @ReactProp(name = "volLegendColor")
    public void setVolLegendColor(View view, String volLegendColor) {
        if (volLegendColor == null) {
            return;
        }
        _volLegendColor = volLegendColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setVolLegendColor(Color.parseColor(_volLegendColor));
    }

    @ReactProp(name = "volLineChartColor")
    public void setVolLineChartColor(View view, String volLineChartColor) {
        if (volLineChartColor == null) {
            return;
        }
        _volLineChartColor = volLineChartColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setVolLineChartColor(Color.parseColor(_volLineChartColor));
    }

    @ReactProp(name = "volLegendMarginTop")
    public void setVolLegendMarginTop(View view, float volLegendMarginTop) {
        if (volLegendMarginTop > -0.000001 && volLegendMarginTop < 0.000001) {
            return;
        }
        _volLegendMarginTop = volLegendMarginTop;
        if (_chartView == null) {
            return;
        }
        _chartView.setVolLegendMarginTop(_volLegendMarginTop);
    }

    @ReactProp(name = "mainBackgroundColor")
    public void setMainBackgroundColor(View view, String mainBackgroundColor) {
        if (mainBackgroundColor == null) {
            return;
        }
        _mainBackgroundColor = mainBackgroundColor;
        if (_chartView == null) {
            return;
        }
        _chartView.setBackgroundColor(Color.parseColor(_mainBackgroundColor));
    }

    @ReactProp(name = "datas")
    public void setDatas(View view, ReadableArray datas) {
        if (datas == null) {
            return;
        }
        _datas = datas;
        int size = _datas.toArrayList().size();
        if (size == 0) {
            return;
        }
        if (_chartView == null || _adapter == null) {
            return;
        }
        Gson gson = new Gson();
        String json = gson.toJson(_datas.toArrayList());
        List<KChartBean> list = gson.fromJson(
                json,
                new TypeToken<List<KChartBean>>() {
                }.getType()
        );
        _adapter.resetData(list, true);
    }

    @ReactProp(name = "locales")
    public void setLocales(View view, ReadableArray locales) {
        Log.d("setLocales", "value: " + locales);
        if (locales == null) {
            return;
        }
        _locales = locales;
        if (_chartView == null || _adapter == null) {
            return;
        }
        if (_locales.toArrayList().size() == 0) {
            return;
        }
        Gson gson = new Gson();
        String json = gson.toJson(_locales.toArrayList());
        String[] list = gson.fromJson(
                json,
                new TypeToken<String[]>() {
                }.getType()
        );
        _chartView.setSelectedInfoLabels(list);
    }

    @ReactProp(name = "indicators")
    public void setIndicators(View view, ReadableArray indicators) {
        Log.d("setIndicators", "value: " + indicators);
        if (indicators == null) {
            return;
        }
        _indicators = indicators;
        Log.d("_chartView", String.valueOf(_chartView == null));
        Log.d("_adapter", String.valueOf(_adapter == null));
        if (_chartView == null || _adapter == null) {
            return;
        }
        List list = _indicators.toArrayList();
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

    @ReactProp(name = "increaseColor")
    public void setIncreaseColor(View view, String increaseColor) {
        _increaseColor = increaseColor;
        if (_chartView == null || _adapter == null) {
            return;
        }
        _chartView.setIncreaseColor(Color.parseColor(_increaseColor));
    }

    @ReactProp(name = "decreaseColor")
    public void setDecreaseColor(View view, String decreaseColor) {
        _decreaseColor = decreaseColor;
        if (_chartView == null || _adapter == null) {
            return;
        }
        _chartView.setDecreaseColor(Color.parseColor(_decreaseColor));
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
            _adapter.resetData(options.list);
            if (_requestStatus) {
                _requestStatus = false;
            }
        }
    }

    @Override
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().put(
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
        _chartView.setGridColumns(5).setGridRows(5).setOverScrollRange(dm2.widthPixels / 5);
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
