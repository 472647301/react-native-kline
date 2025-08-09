import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';
import type { BubblingEventHandler } from 'react-native/Libraries/Types/CodegenTypes';

import type { Int32, Float } from 'react-native/Libraries/Types/CodegenTypes';
import type { ViewProps } from 'react-native';

export interface NativeProps extends ViewProps {
  /**
   * 显示loading的同时显示K线
   */
  loading?: boolean;
  /**
   * 只显示loading不会显示后面的K线,K线只显示背景
   */
  justShowLoading?: boolean;
  /**
   * 是否显示加载动画
   */
  animLoadData?: boolean;
  /**
   * 主图MA/BOLL切换
   * @enum `MainDrawType`
   */
  mainDrawType?: Int32;
  /**
   * 子图指标图切换
   * @enum `IndexdDrawType`
   */
  indexdDrawType?: Int32;
  /**
   * K线与分时线切换
   * @enum `KLineState`
   */
  kLineState?: Int32;
  /**
   * 设置十字线触发模式
   * @enum SelectedTouchModle
   */
  selectedTouchModle?: Int32;
  /**
   * 主视图所占高度比例 占比,用0-1之间的数表示
   */
  mainPresent?: Float;
  /**
   * 成交量视图所占高度比例 占比,用0-1之间的数表示
   */
  volPresent?: Float;
  /**
   * 切换显示/隐藏交易量
   */
  volShowState?: boolean;
  /**
   * 设置交易量绘制 BarChart/LineChart
   * @enum VolChartType
   */
  volChartStatues?: Int32;
  /**
   * 是否隐藏信息框 默认false
   */
  hideMarketInfoBox?: boolean;
  /**
   * 网格列
   */
  gridColumns?: Int32;
  /**
   * 网格行
   */
  gridRows?: Int32;
  /**
   * Slide left K line inside indent width
   */
  overScrollRange?: Float;
  /**
   * Y值精度格式化 `%.03f`
   */
  valueFormatter?: string;
  /**
   * 成交量格式化 `%.03f`
   */
  volFormatter?: string;
  /**
   * 时间格式化 `yyyy-MM-dd hh:mm`
   */
  dateTimeFormatter?: string;
  /**
   * 主视图Formatte `%.03f`
   */
  mainValueFormatter?: string;
  /**
   * 设置选中X轴坐标背景色
   */
  selectedXLabelBackgroundColor?: string;
  /**
   * 设置价格线上的文字颜色
   */
  priceLabelInLineTextColor?: string;
  /**
   * 设置价格线上的文字大小
   */
  priceLabelInLineTextSize?: Float;
  /**
   * 设置选中X坐标文字颜色
   */
  selectedLabelTextColor?: string;
  /**
   * 设置选中X坐标文字大小
   */
  selectedLabelTextSize?: Float;
  /**
   * 设置价格线上价格框离右距离
   */
  priceLabelInLineBoxMarginRight?: Float;
  /**
   * 价格线上价格图形宽
   */
  priceLabelInLineShapeWidth?: Float;
  /**
   * 价格线上价格图形高
   */
  priceLabelInLineShapeHeight?: Float;
  /**
   * 设置价格线上价格框高度
   */
  priceLabelInLineBoxHeight?: Float;
  /**
   * 设置价格线上价格框内边距
   */
  priceLabelInLineBoxPadding?: Float;
  /**
   * 设置价格线价格框可点击
   */
  priceLabelInLineClickable?: boolean;
  /**
   * 设置右侧价格框背景色
   */
  priceLabelInLineBoxBackgroundColor?: string;
  /**
   * 设置价格线右侧框背景
   */
  priceLabelRightBackgroundColor?: string;
  /**
   * 设置价格线右侧框边框颜色
   */
  priceLabelInLineBoxBorderColor?: string;
  /**
   * 设置价格线框边框宽度
   */
  priceLabelInLineBoxBorderWidth?: Float;
  /**
   * 设置价格线上价格框圆角半径
   */
  priceLabelInLineBoxRadius?: Float;
  /**
   * 设置价格线右侧价格文字的颜色
   */
  priceLabelRightTextColor?: string;
  /**
   * 设置价格线右侧的颜色
   */
  priceLineRightColor?: string;
  /**
   * 设置价格线的宽度
   */
  priceLineWidth?: Float;
  /**
   * 设置显示价格线
   */
  showPriceLine?: boolean;
  /**
   * 设置显示价格线上的价格
   */
  showPriceLabelInLine?: boolean;
  /**
   * 设置价格线颜色
   */
  priceLineColor?: string;
  /**
   * 价格线虚线实心宽度
   */
  priceLineDotSolidWidth?: Float;
  /**
   * 价格线实心间隔
   */
  priceLineDotStrokeWidth?: Float;
  /**
   * 显示右侧虚线和价格
   */
  showRightDotPriceLine?: boolean;
  /**
   * 设置选择器横线宽
   */
  selectedXLineWidth?: Float;
  /**
   * 设置十字线竖线宽度
   */
  selectedYLineWidth?: Float;
  /**
   * 设置十字线横线颜色
   */
  selectedXLineColor?: string;
  /**
   * 设置十字线竖线画笔颜色
   */
  selectedYLineColor?: string;
  /**
   * 选中的线的Y轴颜色
   */
  selectedYColor?: string;
  /**
   * 设置都十字线选中点外圆颜色
   */
  selectedCrossBigColor?: string;
  /**
   * 设置选中时是否显示十字线的交点圆
   */
  selectedShowCrossPoint?: boolean;
  /**
   * 设置选中Y值背景色
   */
  selectedPriceBoxBackgroundColor?: string;
  /**
   * 设置选择器文字大小
   */
  selectedInfoTextSize?: Float;
  /**
   * 选中时价格label的横向padding
   */
  selectedPriceBoxHorizontalPadding?: Float;
  /**
   * 选中时价格label的纵向padding
   */
  selectedPriceBoxVerticalPadding?: Float;
  /**
   * 选中信息框内边距,上下为此值*2
   */
  selectedInfoBoxPadding?: Float;
  /**
   * 选中行弹出框与边缘的距离
   */
  selectedInfoBoxMargin?: Float;
  /**
   * 设置选择器弹出框
   */
  selectedInfoBox?: {
    textColor: string;
    borderColor: string;
    backgroundColor: string;
  };
  /**
   * 选中时X坐标边框线宽
   */
  selectedLabelBorderWidth?: Float;
  /**
   * 选中时X坐标边框线颜色
   */
  selectedLabelBorderColor?: string;
  /**
   * 设置选中框的文本
   * 时间 开 高 低 收 涨跌额 涨跌幅 成交量 组成的文字数组
   */
  selectedInfoLabels?: string[];
  /**
   * 设置十字线跟随手势移动/显示收盘价
   * @enum `CrossFollowTouch`
   */
  crossFollowTouch?: Int32;
  /**
   * 设置y轴上Label与视图右边距
   */
  yLabelMarginBorder?: Float;
  /**
   * 设置背景色顶部颜色
   */
  backgroundFillTopColor?: string;
  /**
   * 设置背景色底部颜色
   */
  backgroundFillBottomColor?: string;
  /**
   * 设置分时线颜色
   */
  timeLineColor?: string;
  /**
   * 设置分时线填充渐变的顶部颜色
   */
  timeLineFillTopColor?: string;
  /**
   * 设置分时线填充渐变的底部颜色
   */
  timeLineFillBottomColor?: string;
  /**
   * 分时线呼吸灯的颜色
   */
  timeLineEndPointColor?: string;
  /**
   * 分时线呼吸灯的颜色半径
   */
  timeLineEndRadius?: Float;
  /**
   * timeLineEndMultiply
   */
  timeLineEndMultiply?: Float;
  /**
   * 选中十字线X轴坐标连框纵向内边距
   */
  selectedDateBoxVerticalPadding?: Float;
  /**
   * 选中十字线X轴坐标连框横向内边距
   */
  selectedDateBoxHorizontalPadding?: Float;
  /**
   * 设置主实图图例距离视图上边缘的距离
   */
  mainLegendMarginTop?: Float;
  /**
   * 设置图例距离视图左边缘的距离
   */
  legendMarginLeft?: Float;
  /**
   * 设置涨的颜色
   */
  increaseColor?: string;
  /**
   * 设置跌的颜色
   */
  decreaseColor?: string;
  /**
   * 设置是否自适应X左右边轴坐标的位置,默认true
   */
  betterXLabel?: boolean;
  /**
   * 设置坐标文字大小
   */
  labelTextSize?: Float;
  /**
   * 设置坐标轴坐标文字颜色
   */
  labelTextColor?: string;
  /**
   * 设置Y轴显示在左侧/右侧 false=右侧，默认右侧
   */
  yLabelAlign?: boolean;
  /**
   * 设置是否自适应X左右边轴坐标的位置,默认true
   */
  betterSelectedXLabel?: boolean;
  /**
   * 统一设置设置文字大小
   */
  commonTextSize?: Float;
  /**
   * 设置上方padding
   */
  mainMarginTop?: Float;
  /**
   * 设置下方padding
   */
  paddingBottom?: Float;
  /**
   * 子视图的顶部padding
   */
  childPaddingTop?: Float;
  /**
   * 设置通用文字颜色
   */
  commonTextColor?: string;
  /**
   * 全局通用线宽
   */
  lineWidth?: Float;
  /**
   * 设置每根K线总宽度(包含外间隙)
   */
  itemWidth?: Float;
  /**
   * 设置每根蜡烛图宽度
   */
  candleWidth?: Float;
  /**
   * 设置蜡烛线画笔宽(空心时的线宽)
   */
  candleLineWidth?: Float;
  /**
   * 设置主视图最大/最小值文字颜色
   */
  limitTextColor?: string;
  /**
   * 设置主图片最大/最小值文字大小
   */
  limitTextSize?: Float;
  /**
   * 蜡烛空心模式
   * @enum `CandleHollow`
   */
  candleHollow?: Int32;
  /**
   * 设置背景网格线宽
   */
  gridLineWidth?: Float;
  /**
   * 设置背景网格线颜色
   */
  gridLineColor?: string;
  /**
   * macd空心时线宽
   */
  macdStrokeWidth?: Float;
  /**
   * 设置macd 线空心模式
   * @enum `CandleHollow`
   */
  macdHolow?: Int32;
  /**
   * macd柱状图宽
   */
  macdWidth?: Float;
  /**
   * 设置DIF颜色
   */
  difColor?: string;
  /**
   * 设置DEA颜色
   */
  deaColor?: string;
  /**
   * 设置MACD颜色
   */
  macdColor?: string;
  /**
   * 设置K颜色
   */
  kColor?: string;
  /**
   * 设置D颜色
   */
  dColor?: string;
  /**
   * 设置J颜色
   */
  jColor?: string;
  /**
   * 设置RSI1颜色
   */
  rsi1Color?: string;
  /**
   * 设置RSI2颜色
   */
  rsi2Color?: string;
  /**
   * 设置RSI3颜色
   */
  rsi3Color?: string;
  /**
   * 设置MA1颜色
   */
  ma1Color?: string;
  /**
   * 设置MA2颜色
   */
  ma2Color?: string;
  /**
   * 设置MA3颜色
   */
  ma3Color?: string;
  /**
   * 设置交易量MA1颜色
   */
  volMa1Color?: string;
  /**
   * 设置交易量MA2颜色
   */
  volMa2Color?: string;
  /**
   * 交易量图例颜色
   */
  volLegendColor?: string;
  /**
   * 设置当成交量视图显示为线时的颜色
   */
  volLineChartColor?: string;
  /**
   * 交易量图例距离量视图上边缘的距离
   */
  volLegendMarginTop?: Float;
  /**
   * 设置Y轴显示模式独立显示/与网格同时显示
   * @enum `YLabelState`
   */
  yLabelState?: Int32;
  /**
   * 最大最小值计算模式,影响Y轴的最大最小值
   * @enum `MaxMinCalcModel`
   */
  maxMinCalcModel?: Int32;
  /**
   * 滑动边界监听 - 滑动到最左边
   * @todo ⚠️注意会多次触发
   */
  onSlidLeft?: BubblingEventHandler<null> | null;
  /**
   * 滑动边界监听 - 滑动到最右边
   * @todo ⚠️注意会多次触发
   */
  onSlidRight?: BubblingEventHandler<null> | null;
}

export enum CrossFollowTouch {
  /** 跟随手指移动 */
  TOUCH_FOLLOW_FINGERS = 2001,
  /** y值显示当前价格 */
  TOUCH_SHOW_CLOSE,
}

export enum YLabelState {
  /** Y轴显示在网格上 */
  LABEL_WITH_GRID = 8001,
  /** Y轴独立显示与网格不重叠 */
  LABEL_NONE_GRID,
}

export enum MaxMinCalcModel {
  /** 计算显示的线全部值 */
  CALC_NORMAL_WITH_SHOW = 7001,
  /** 计算显示的线的close值 */
  CALC_CLOSE_WITH_SHOW,
  /** 计算显示的线和最新线的全部值 */
  CALC_NORMAL_WITH_LAST,
  /** 计算显示的线和最新线的close值 */
  CALC_CLOSE_WITH_LAST,
}

export enum MainDrawType {
  /** 显示ma */
  MA = 1001,
  /** 显示boll */
  BOLL,
  /** 只显示CandleLine */
  NONE,
}

export enum IndexdDrawType {
  /** 不显示子图 */
  NONE = 4001,
  MACD,
  KDJ,
  RSI,
  WR,
  EMA,
}

export enum KLineState {
  TIME_LINE = 4102,
  K_LINE,
}

export enum SelectedTouchModle {
  /** 点击显示 */
  SELECT_TOUCHE = 5001,
  /** 长按显示 */
  SELECT_PRESS,
  /** 点击长按混合 */
  SELECT_BOTH,
  SELECT_NONE,
}

export enum VolChartType {
  /** 柱状图 */
  BAR_CHART = 4100,
  /** 线状图 */
  VERTICAL_BAR,
}

export enum CandleHollow {
  /** 全实心 */
  NONE_HOLLOW = 6001,
  /** 全空心 */
  ALL_HOLLOW,
  /** 上涨的值空心 */
  DECREASE_HOLLOW,
  /** 下跌的值空心 */
  INCREASE_HOLLOW,
}

export default codegenNativeComponent<NativeProps>('KlineView');
