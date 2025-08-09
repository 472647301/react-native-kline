package com.kline

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.graphics.toColorInt
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.UIManagerHelper
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.viewmanagers.KlineViewManagerDelegate
import com.facebook.react.viewmanagers.KlineViewManagerInterface
import com.kline.adapter.KLineChartAdapter
import com.kline.callback.SlidListener
import com.kline.formatter.DateFormatter
import com.kline.formatter.ValueFormatter
import com.kline.model.KLineEntity
import com.kline.view.KChartView
import java.text.SimpleDateFormat
import java.util.Date


@ReactModule(name = KlineViewManager.NAME)
class KlineViewManager : ViewGroupManager<LinearLayout>(),
  KlineViewManagerInterface<LinearLayout> {

  private val mDelegate: ViewManagerDelegate<LinearLayout> = KlineViewManagerDelegate(this)
  private var _container: LinearLayout? = null
  private var _chartView: KChartView? = null

  override fun getDelegate(): ViewManagerDelegate<LinearLayout> {
    return mDelegate
  }

  override fun getName(): String {
    return NAME
  }

  @SuppressLint("InflateParams")
  public override fun createViewInstance(context: ThemedReactContext): LinearLayout {
    val layout = LayoutInflater.from(context).inflate(R.layout.kline_chart, null)
    layout.layoutParams = FrameLayout.LayoutParams(
      ViewGroup.LayoutParams.MATCH_PARENT,
      ViewGroup.LayoutParams.MATCH_PARENT
    )
    _container = layout.findViewById(R.id.kLineContainer)
    _container?.visibility = View.VISIBLE;
    _chartView = _container?.findViewById(R.id.kLineChart)
    _chartView?.setAdapter(adapter)
    _chartView?.setSlidListener(object : SlidListener {
      override fun onSlidLeft() {
        val dispatcher = UIManagerHelper.getEventDispatcherForReactTag(context, _container!!.id)
        val surfaceId: Int = UIManagerHelper.getSurfaceId(_container!!)
        dispatcher?.dispatchEvent(
          SlidListenerEvent(surfaceId, _container!!.id, "onSlidLeft", null)
        )
      }
      override fun onSlidRight() {
        val dispatcher = UIManagerHelper.getEventDispatcherForReactTag(context, _container!!.id)
        val surfaceId: Int = UIManagerHelper.getSurfaceId(_container!!)
        dispatcher?.dispatchEvent(
          SlidListenerEvent(surfaceId, _container!!.id, "onSlidRight", null)
        )
      }
    })
    return _container!!
  }

  companion object {
    const val NAME = "KlineView"
    val adapter = KLineChartAdapter<KLineEntity>()
  }

  @ReactProp(name = "loading")
  override fun setLoading(view: LinearLayout?, value: Boolean) {
    if (value) {
      _chartView?.showLoading()
    } else {
      _chartView?.hideLoading()
    }
  }

  @ReactProp(name = "justShowLoading")
  override fun setJustShowLoading(view: LinearLayout?, value: Boolean) {
    if (value) {
      _chartView?.justShowLoading()
    } else {
      _chartView?.hideLoading()
    }
  }

  @ReactProp(name = "animLoadData")
  override fun setAnimLoadData(view: LinearLayout?, value: Boolean) {
    _chartView?.setAnimLoadData(value)
  }

  @ReactProp(name = "mainDrawType")
  override fun setMainDrawType(view: LinearLayout?, value: Int) {
    _chartView?.changeMainDrawType(value)
  }

  @ReactProp(name = "indexdDrawType")
  override fun setIndexdDrawType(view: LinearLayout?, value: Int) {
    _chartView?.setIndexDraw(value)
  }

  @ReactProp(name = "kLineState")
  override fun setKLineState(view: LinearLayout?, value: Int) {
    _chartView?.setKlineState(value)
  }

  @ReactProp(name = "selectedTouchModle")
  override fun setSelectedTouchModle(view: LinearLayout?, value: Int) {
    _chartView?.setSelectedTouchModel(value)
  }

  @ReactProp(name = "mainPresent")
  override fun setMainPresent(view: LinearLayout?, value: Float) {
    _chartView?.setMainPercent(value)
  }

  @ReactProp(name = "volPresent")
  override fun setVolPresent(view: LinearLayout?, value: Float) {
    _chartView?.setVolPercent(value)
  }

  @ReactProp(name = "volShowState")
  override fun setVolShowState(view: LinearLayout?, value: Boolean) {
    _chartView?.setVolShowState(value)
  }

  @ReactProp(name = "volChartStatues")
  override fun setVolChartStatues(view: LinearLayout?, value: Int) {
    _chartView?.setVolChartStatues(value)
  }

  @ReactProp(name = "hideMarketInfoBox")
  override fun setHideMarketInfoBox(view: LinearLayout?, value: Boolean) {
    _chartView?.setHideMarketInfo(value)
  }

  @ReactProp(name = "gridColumns")
  override fun setGridColumns(view: LinearLayout?, value: Int) {
    _chartView?.setGridColumns(value)
  }

  @ReactProp(name = "gridRows")
  override fun setGridRows(view: LinearLayout?, value: Int) {
    _chartView?.setGridRows(value)
  }

  @ReactProp(name = "overScrollRange")
  override fun setOverScrollRange(view: LinearLayout?, value: Float) {
    _chartView?.setOverScrollRange(value)
  }

  @ReactProp(name = "valueFormatter")
  override fun setValueFormatter(view: LinearLayout?, format: String?) {
    if (format == null) return
    _chartView?.setIndexValueFormatter(object : ValueFormatter() {
      override fun format(value: Double): String {
        return String.format(format, value)
      }
    })
  }

  @ReactProp(name = "volFormatter")
  override fun setVolFormatter(view: LinearLayout?, format: String?) {
    if (format == null) return
    _chartView?.setVolFormatter(object : ValueFormatter() {
      override fun format(value: Double): String {
        return String.format(format, value)
      }
    })
  }

  @ReactProp(name = "dateTimeFormatter")
  override fun setDateTimeFormatter(view: LinearLayout?, format: String?) {
    if (format == null) return
    _chartView?.setDateTimeFormatter(object : DateFormatter() {
      @SuppressLint("SimpleDateFormat")
      override fun format(date: Date): String {
        return SimpleDateFormat(format).format(date)
      }
    })
  }

  @ReactProp(name = "mainValueFormatter")
  override fun setMainValueFormatter(view: LinearLayout?, format: String?) {
    if (format == null) return
    _chartView?.setMainValueFormatter(object : ValueFormatter() {
      override fun format(value: Double): String {
        return String.format(format, value)
      }
    })
  }

  @ReactProp(name = "selectedXLabelBackgroundColor")
  override fun setSelectedXLabelBackgroundColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setSelectedXLabelBackgroundColor(value.toColorInt())
  }

  @ReactProp(name = "priceLabelInLineTextColor")
  override fun setPriceLabelInLineTextColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setPriceLabelInLineTextColor(value.toColorInt())
  }

  @ReactProp(name = "priceLabelInLineTextSize")
  override fun setPriceLabelInLineTextSize(view: LinearLayout?, value: Float) {
    _chartView?.setPriceLabelInLineTextSize(value)
  }

  @ReactProp(name = "selectedLabelTextColor")
  override fun setSelectedLabelTextColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setSelectedXLabelTextColor(value.toColorInt())
  }

  @ReactProp(name = "selectedLabelTextSize")
  override fun setSelectedLabelTextSize(view: LinearLayout?, value: Float) {
    _chartView?.setSelectedXLabelTextSize(value)
  }

  @ReactProp(name = "priceLabelInLineBoxMarginRight")
  override fun setPriceLabelInLineBoxMarginRight(view: LinearLayout?, value: Float) {
    _chartView?.setPriceLabelInLineMarginRight(value)
  }

  @ReactProp(name = "priceLabelInLineShapeWidth")
  override fun setPriceLabelInLineShapeWidth(view: LinearLayout?, value: Float) {
    _chartView?.setPriceLabelInLineShapeWidth(value)
  }

  @ReactProp(name = "priceLabelInLineShapeHeight")
  override fun setPriceLabelInLineShapeHeight(view: LinearLayout?, value: Float) {
    _chartView?.setPriceLabelInLineShapeHeight(value)
  }

  @ReactProp(name = "priceLabelInLineBoxHeight")
  override fun setPriceLabelInLineBoxHeight(view: LinearLayout?, value: Float) {
    _chartView?.setPriceLabelInLineBoxHeight(value)
  }

  @ReactProp(name = "priceLabelInLineBoxPadding")
  override fun setPriceLabelInLineBoxPadding(view: LinearLayout?, value: Float) {
    _chartView?.setPriceLabelInLineBoxPadding(value)
  }

  @ReactProp(name = "priceLabelInLineClickable")
  override fun setPriceLabelInLineClickable(view: LinearLayout?, value: Boolean) {
    _chartView?.setPriceLabelInLineClickable(value)
  }

  @ReactProp(name = "priceLabelInLineBoxBackgroundColor")
  override fun setPriceLabelInLineBoxBackgroundColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setPriceLabelInLineBoxBackgroundColor(value.toColorInt())
  }

  @ReactProp(name = "priceLabelRightBackgroundColor")
  override fun setPriceLabelRightBackgroundColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setPriceLabelRightBackgroundColor(value.toColorInt())
  }

  @ReactProp(name = "priceLabelInLineBoxBorderColor")
  override fun setPriceLabelInLineBoxBorderColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setPriceLabelInLineBoxBorderColor(value.toColorInt())
  }

  @ReactProp(name = "priceLabelInLineBoxBorderWidth")
  override fun setPriceLabelInLineBoxBorderWidth(view: LinearLayout?, value: Float) {
    _chartView?.setPriceLabelInLineBoxBorderWidth(value)
  }

  @ReactProp(name = "priceLabelInLineBoxRadius")
  override fun setPriceLabelInLineBoxRadius(view: LinearLayout?, value: Float) {
    _chartView?.setPriceLabelInLineBoxRadius(value)
  }

  @ReactProp(name = "priceLabelRightTextColor")
  override fun setPriceLabelRightTextColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setPriceLabelRightTextColor(value.toColorInt())
  }

  @ReactProp(name = "priceLineRightColor")
  override fun setPriceLineRightColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setPriceLineRightColor(value.toColorInt())
  }

  @ReactProp(name = "priceLineWidth")
  override fun setPriceLineWidth(view: LinearLayout?, value: Float) {
    _chartView?.setPriceLineWidth(value)
  }

  @ReactProp(name = "showPriceLine")
  override fun setShowPriceLine(view: LinearLayout?, value: Boolean) {
    _chartView?.setShowPriceLine(value)
  }

  @ReactProp(name = "showPriceLabelInLine")
  override fun setShowPriceLabelInLine(view: LinearLayout?, value: Boolean) {
    _chartView?.setShowPriceLabelInLine(value)
  }

  @ReactProp(name = "priceLineColor")
  override fun setPriceLineColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setPriceLineColor(value.toColorInt())
  }

  @ReactProp(name = "priceLineDotSolidWidth")
  override fun setPriceLineDotSolidWidth(view: LinearLayout?, value: Float) {
    _chartView?.setPriceLineDotSolidWidth(value)
  }

  @ReactProp(name = "priceLineDotStrokeWidth")
  override fun setPriceLineDotStrokeWidth(view: LinearLayout?, value: Float) {
    _chartView?.setPriceLineDotStrokeWidth(value)
  }

  @ReactProp(name = "showRightDotPriceLine")
  override fun setShowRightDotPriceLine(view: LinearLayout?, value: Boolean) {
    _chartView?.setShowRightDotPriceLine(value)
  }

  @ReactProp(name = "selectedXLineWidth")
  override fun setSelectedXLineWidth(view: LinearLayout?, value: Float) {
    _chartView?.setSelectedXLineWidth(value)
  }

  @ReactProp(name = "selectedYLineWidth")
  override fun setSelectedYLineWidth(view: LinearLayout?, value: Float) {
    _chartView?.setSelectedYLineWidth(value)
  }

  @ReactProp(name = "selectedXLineColor")
  override fun setSelectedXLineColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setSelectedXLineColor(value.toColorInt())
  }

  @ReactProp(name = "selectedYLineColor")
  override fun setSelectedYLineColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setSelectedYLineColor(value.toColorInt())
  }

  @ReactProp(name = "selectedYColor")
  override fun setSelectedYColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setSelectedYColor(value.toColorInt())
  }

  @ReactProp(name = "selectedCrossBigColor")
  override fun setSelectedCrossBigColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setSelectedCrossBigColor(value.toColorInt())
  }

  @ReactProp(name = "selectedShowCrossPoint")
  override fun setSelectedShowCrossPoint(view: LinearLayout?, value: Boolean) {
    _chartView?.setSelectedShowCrossPoint(value)
  }

  @ReactProp(name = "selectedPriceBoxBackgroundColor")
  override fun setSelectedPriceBoxBackgroundColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setSelectedPriceBoxBackgroundColor(value.toColorInt())
  }

  @ReactProp(name = "selectedInfoTextSize")
  override fun setSelectedInfoTextSize(view: LinearLayout?, value: Float) {
    _chartView?.setSelectedInfoTextSize(value)
  }

  @ReactProp(name = "selectedPriceBoxHorizontalPadding")
  override fun setSelectedPriceBoxHorizontalPadding(view: LinearLayout?, value: Float) {
    _chartView?.setSelectedPriceBoxHorizentalPadding(value)
  }

  @ReactProp(name = "selectedPriceBoxVerticalPadding")
  override fun setSelectedPriceBoxVerticalPadding(view: LinearLayout?, value: Float) {
    _chartView?.setSelectedDateBoxVerticalPadding(value)
  }

  @ReactProp(name = "selectedInfoBoxPadding")
  override fun setSelectedInfoBoxPadding(view: LinearLayout?, value: Float) {
    _chartView?.setSelectInfoBoxPadding(value)
  }

  @ReactProp(name = "selectedInfoBoxMargin")
  override fun setSelectedInfoBoxMargin(view: LinearLayout?, value: Float) {
    _chartView?.setSelectInfoBoxMargin(value)
  }

  @ReactProp(name = "selectedLabelBorderWidth")
  override fun setSelectedLabelBorderWidth(view: LinearLayout?, value: Float) {
    _chartView?.setSelectedXLabelBorderWidth(value)
  }

  @ReactProp(name = "selectedLabelBorderColor")
  override fun setSelectedLabelBorderColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setSelectedXLabelBorderColor(value.toColorInt())
  }

  @ReactProp(name = "selectedInfoLabels")
  override fun setSelectedInfoLabels(view: LinearLayout?, value: ReadableArray?) {
    if (value == null || value.size() != 8) return
    val stringList = mutableListOf<String>()
    for (i in 0 until value.size()) {
      // Ensure the element at index i is a String before attempting to get it
      if (value.getType(i) == com.facebook.react.bridge.ReadableType.String) {
        value.getString(i)?.let {
          stringList.add(it)
        }
      }
    }
    _chartView?.setSelectedInfoLabels(stringList.toTypedArray())
  }

  @ReactProp(name = "crossFollowTouch")
  override fun setCrossFollowTouch(view: LinearLayout?, value: Int) {
    _chartView?.setCrossFollowTouch(value)
  }

  @ReactProp(name = "yLabelMarginBorder")
  override fun setYLabelMarginBorder(view: LinearLayout?, value: Float) {
    _chartView?.setyLabelMarginBorder(value)
  }

  @ReactProp(name = "backgroundFillTopColor")
  override fun setBackgroundFillTopColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setBackGroundFillTopColor(value.toColorInt())
  }

  @ReactProp(name = "backgroundFillBottomColor")
  override fun setBackgroundFillBottomColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setBackGroundFillBottomColor(value.toColorInt())
  }

  @ReactProp(name = "timeLineColor")
  override fun setTimeLineColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setTimeLineColor(value.toColorInt())
  }

  @ReactProp(name = "timeLineFillTopColor")
  override fun setTimeLineFillTopColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setTimeLineFillTopColor(value.toColorInt())
  }

  @ReactProp(name = "timeLineFillBottomColor")
  override fun setTimeLineFillBottomColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setTimeLineFillBottomColor(value.toColorInt())
  }

  @ReactProp(name = "timeLineEndPointColor")
  override fun setTimeLineEndPointColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setTimeLineEndColor(value.toColorInt())
  }

  @ReactProp(name = "timeLineEndRadius")
  override fun setTimeLineEndRadius(view: LinearLayout?, value: Float) {
    _chartView?.setTimeLineEndRadius(value)
  }

  @ReactProp(name = "timeLineEndMultiply")
  override fun setTimeLineEndMultiply(view: LinearLayout?, value: Float) {
    _chartView?.setTimeLineEndMultiply(value)
  }

  @ReactProp(name = "selectedDateBoxVerticalPadding")
  override fun setSelectedDateBoxVerticalPadding(view: LinearLayout?, value: Float) {
    _chartView?.setSelectedDateBoxVerticalPadding(value)
  }

  @ReactProp(name = "selectedDateBoxHorizontalPadding")
  override fun setSelectedDateBoxHorizontalPadding(view: LinearLayout?, value: Float) {
    _chartView?.setSelectedDateBoxHorizontalPadding(value)
  }

  @ReactProp(name = "mainLegendMarginTop")
  override fun setMainLegendMarginTop(view: LinearLayout?, value: Float) {
    _chartView?.setMainLegendMarginTop(value)
  }

  @ReactProp(name = "legendMarginLeft")
  override fun setLegendMarginLeft(view: LinearLayout?, value: Float) {
    _chartView?.setLegendMarginLeft(value)
  }

  @ReactProp(name = "increaseColor")
  override fun setIncreaseColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setIncreaseColor((value.toColorInt()))
  }

  @ReactProp(name = "decreaseColor")
  override fun setDecreaseColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setDecreaseColor((value.toColorInt()))
  }

  @ReactProp(name = "betterXLabel")
  override fun setBetterXLabel(view: LinearLayout?, value: Boolean) {
    _chartView?.setBetterX(value)
  }

  @ReactProp(name = "labelTextSize")
  override fun setLabelTextSize(view: LinearLayout?, value: Float) {
    _chartView?.setYLabelTextSize(value)
  }

  @ReactProp(name = "labelTextColor")
  override fun setLabelTextColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setYLabelTextColor(value.toColorInt())
  }

  @ReactProp(name = "yLabelAlign")
  override fun setYLabelAlign(view: LinearLayout?, value: Boolean) {
    _chartView?.setYlabelAlign(value)
  }

  @ReactProp(name = "betterSelectedXLabel")
  override fun setBetterSelectedXLabel(view: LinearLayout?, value: Boolean) {
    _chartView?.setBetterSelectedX(value)
  }

  @ReactProp(name = "commonTextSize")
  override fun setCommonTextSize(view: LinearLayout?, value: Float) {
    _chartView?.setCommonTextSize(value)
  }

  @ReactProp(name = "mainMarginTop")
  override fun setMainMarginTop(view: LinearLayout?, value: Float) {
    _chartView?.setMainLegendMarginTop(value)
  }

  @ReactProp(name = "paddingBottom")
  override fun setPaddingBottom(view: LinearLayout?, value: Float) {
    _chartView?.setChartPaddingBottom(value)
  }

  @ReactProp(name = "childPaddingTop")
  override fun setChildPaddingTop(view: LinearLayout?, value: Float) {
    _chartView?.setChildPaddingTop(value)
  }

  @ReactProp(name = "commonTextColor")
  override fun setCommonTextColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setCommonTextColor(value.toColorInt())
  }

  @ReactProp(name = "lineWidth")
  override fun setLineWidth(view: LinearLayout?, value: Float) {
    _chartView?.setLineWidth(value)
  }

  @ReactProp(name = "itemWidth")
  override fun setItemWidth(view: LinearLayout?, value: Float) {
    _chartView?.setChartItemWidth(value)
  }

  @ReactProp(name = "candleWidth")
  override fun setCandleWidth(view: LinearLayout?, value: Float) {
    _chartView?.setCandleWidth(value)
  }

  @ReactProp(name = "candleLineWidth")
  override fun setCandleLineWidth(view: LinearLayout?, value: Float) {
    _chartView?.setCandleLineWidth(value)
  }

  @ReactProp(name = "limitTextColor")
  override fun setLimitTextColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setLimitTextColor(value.toColorInt())
  }

  @ReactProp(name = "limitTextSize")
  override fun setLimitTextSize(view: LinearLayout?, value: Float) {
    _chartView?.setLimitTextSize(value)
  }

  @ReactProp(name = "candleHollow")
  override fun setCandleHollow(view: LinearLayout?, value: Int) {
    _chartView?.setCandleHollow(value)
  }

  @ReactProp(name = "gridLineWidth")
  override fun setGridLineWidth(view: LinearLayout?, value: Float) {
    _chartView?.setGridLineWidth(value)
  }

  @ReactProp(name = "gridLineColor")
  override fun setGridLineColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setGridLineColor(value.toColorInt())
  }

  @ReactProp(name = "macdStrokeWidth")
  override fun setMacdStrokeWidth(view: LinearLayout?, value: Float) {
    _chartView?.setMacdStrokeWidth(value)
  }

  @ReactProp(name = "macdHolow")
  override fun setMacdHolow(view: LinearLayout?, value: Int) {
    _chartView?.setMacdHollow(value)
  }

  @ReactProp(name = "macdWidth")
  override fun setMacdWidth(view: LinearLayout?, value: Float) {
    _chartView?.setMACDWidth(value)
  }

  @ReactProp(name = "difColor")
  override fun setDifColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setDIFColor(value.toColorInt())
  }

  @ReactProp(name = "deaColor")
  override fun setDeaColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setDEAColor(value.toColorInt())
  }

  @ReactProp(name = "macdColor")
  override fun setMacdColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setMACDColor(value.toColorInt())
  }

  @ReactProp(name = "kColor")
  override fun setKColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setKColor(value.toColorInt())
  }

  @ReactProp(name = "dColor")
  override fun setDColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setDColor(value.toColorInt())
  }

  @ReactProp(name = "jColor")
  override fun setJColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setJColor(value.toColorInt())
  }

  @ReactProp(name = "rsi1Color")
  override fun setRsi1Color(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setRSI1Color(value.toColorInt())
  }

  @ReactProp(name = "rsi2Color")
  override fun setRsi2Color(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setRSI2Color(value.toColorInt())
  }

  @ReactProp(name = "rsi3Color")
  override fun setRsi3Color(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setRSI3Color(value.toColorInt())
  }

  @ReactProp(name = "ma1Color")
  override fun setMa1Color(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setMa1Color(value.toColorInt())
  }

  @ReactProp(name = "ma2Color")
  override fun setMa2Color(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setMa2Color(value.toColorInt())
  }

  @ReactProp(name = "ma3Color")
  override fun setMa3Color(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setMa3Color(value.toColorInt())
  }

  @ReactProp(name = "volMa1Color")
  override fun setVolMa1Color(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setVolMa1Color(value.toColorInt())
  }

  @ReactProp(name = "volMa2Color")
  override fun setVolMa2Color(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setVolMa2Color(value.toColorInt())
  }

  @ReactProp(name = "volLegendColor")
  override fun setVolLegendColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setVolLegendColor(value.toColorInt())
  }

  @ReactProp(name = "volLineChartColor")
  override fun setVolLineChartColor(view: LinearLayout?, value: String?) {
    if (value == null) return
    _chartView?.setVolLineChartColor(value.toColorInt())
  }

  @ReactProp(name = "volLegendMarginTop")
  override fun setVolLegendMarginTop(view: LinearLayout?, value: Float) {
    _chartView?.setVolLegendMarginTop(value)
  }

  @ReactProp(name = "yLabelState")
  override fun setYLabelState(view: LinearLayout?, value: Int) {
    _chartView?.setYLabelState(value)
  }

  @ReactProp(name = "maxMinCalcModel")
  override fun setMaxMinCalcModel(view: LinearLayout?, value: Int) {
    _chartView?.setMaxMinCalcModel(value)
  }

  @ReactProp(name = "selectedInfoBox")
  override fun setSelectedInfoBox(view: LinearLayout?, value: ReadableMap?) {
    if (value == null) return
    val textColor = value.getString("textColor")
    val borderColor = value.getString("borderColor")
    val backgroundColor = value.getString("backgroundColor")
    if (textColor == null || borderColor == null || backgroundColor == null) return
    _chartView?.setSelectInfoBoxColors(textColor.toColorInt(), borderColor.toColorInt(), backgroundColor.toColorInt())
  }
}
