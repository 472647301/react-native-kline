import * as React from "react";
import { ViewProps } from "react-native";

export interface KLineBar {
  amount: number;
  open: number;
  close: number;
  high: number;
  id: number;
  low: number;
  vol: number;
}

export type KLineEvent = "update" | "add";

export interface KLineCallbackParams {
  id: number;
}

export declare enum CandleHollow {
  /**
   * 全实心
   */
  NONE_HOLLOW,
  /**
   * 全空心
   */
  ALL_HOLLOW,
  /**
   * 涨空心
   */
  DECREASE_HOLLOW,
  /**
   * 跌实心
   */
  INCREASE_HOLLOW,
}

export declare enum KLineIndicator {
  MainMA,
  MainBOLL,
  MainNONE,
  ChildMACD,
  ChildKDJ,
  ChildRSI,
  ChildWR,
  ChildNONE,
  /**
   * 分时图显示
   */
  TimeLineShow,
  TimeLineHide,
  /**
   * 成交量显示
   */
  VolumeShow,
  VolumeHide,
}

export interface ByronKlineProps {
  /**
   * K线历史数据
   */
  datas: Array<KLineBar>;
  /**
   * 多语言配置
   */
  locales: Array<string>;
  /**
   * 指标配置
   */
  indicators: Array<KLineIndicator>;
  /**
   * 价格精度
   */
  pricePrecision: number;
  /**
   * 数量精度
   */
  volumePrecision: number;
  /**
   * 图表请求加载更多历史数据
   */
  onMoreKLineData: (params: KLineCallbackParams) => void;
  /**
   * 周期
   */
  period: number;
  /**
   *主图背景色
   */
  mainBackgroundColor: string;
  /**
   * 设置选中X轴坐标背景色
   */
  selectedXLabelBackgroundColor: string;
  /**
   * 设置价格线上的文字颜色
   */
  priceLabelInLineTextColor: string;
  /**
   * 设置价格线上的文字大小
   */
  priceLabelInLineTextSize: number;
  /**
   * 设置选中X坐标文字颜色
   */
  selectedLabelTextColor: string;
  /**
   * 设置选中X坐标文字大小
   */
  selectedLabelTextSize: number;
  /**
   * 设置价格线上价格框离右距离
   */
  priceLabelInLineBoxMarginRight: number;
  /**
   * 价格线上价格图形宽
   */
  priceLabelInLineShapeWidth: number;
  /**
   * 价格线上价格图形高
   */
  priceLabelInLineShapeHeight: number;
  /**
   * 设置价格线上价格框高度
   */
  priceLabelInLineBoxHeight: number;
  /**
   * 设置价格线上价格框内边距
   */
  priceLabelInLineBoxPadding: number;
  /**
   * 价格线上价格文字与图形的间隔
   */
  priceLabelInLineBoxShapeTextMargin: number;
  /**
   * 设置价格线价格框可点击
   */
  priceLabelInLineClickable: boolean;
  /**
   * 设置右侧价格框背景色
   */
  priceLabelInLineBoxBackgroundColor: string;
  /**
   * 设置价格线右侧框背景
   */
  priceLabelRightBackgroundColor: string;
  /**
   * 设置价格线右侧框边框颜色
   */
  priceLabelInLineBoxBorderColor: string;
  /**
   * 设置价格线框边框宽度
   */
  priceLabelInLineBoxBorderWidth: number;
  /**
   * 设置价格线上价格框圆角半径
   */
  priceLabelInLineBoxRadius: number;
  /**
   * 设置价格线右侧标签的背景透明度
   */
  priceLineRightLabelBackGroundAlpha: number;
  /**
   * 设置价格线右侧价格文字的颜色
   */
  priceLabelRightTextColor: string;
  /**
   * 设置价格线右侧的颜色
   */
  priceLineRightColor: string;
  /**
   * 设置价格线的宽度
   */
  priceLineWidth: number;
  /**
   * 设置价格线颜色
   */
  priceLineColor: string;
  /**
   * 价格线虚线实心宽度
   */
  priceLineDotSolidWidth: number;
  /**
   * 价格线实心间隔
   */
  priceLineDotStrokeWidth: number;
  /**
   * 设置选择器横线宽
   */
  selectedXLineWidth: number;
  /**
   * 设置十字线竖线宽度
   */
  selectedYLineWidth: number;
  /**
   * 设置十字线横线颜色
   */
  selectedXLineColor: string;
  /**
   * 设置十字线竖线画笔颜色
   */
  selectedYLineColor: string;
  /**
   * 选中的线的Y轴颜色
   */
  selectedYColor: string;
  /**
   * 设置都十字线选中点外圆颜色
   */
  selectedCrossBigColor: string;
  /**
   * 设置十字线相交小圆半径
   */
  selectedCrossPointRadius: number;
  /**
   * 设置十字线交点小圆颜色
   */
  selectedCrossPointColor: string;
  /**
   * 设置选中时是否显示十字线的交点圆
   */
  selectedShowCrossPoint: boolean;
  /**
   * 设置选中Y值背景色
   */
  selectedPriceBoxBackgroundColor: string;
  /**
   * 设置选择器文字大小
   */
  selectedInfoTextSize: number;
  /**
   * 选中时价格label的横向padding
   */
  selectedPriceBoxHorizontalPadding: number;
  /**
   * 选中时价格label的纵向padding
   */
  selectedPriceBoxVerticalPadding: number;
  /**
   * 选中信息框内边距,上下为此值*2
   */
  selectedInfoBoxPadding: number;
  /**
   * 选中行弹出框与边缘的距离
   */
  selectedInfoBoxMargin: number;
  /**
   * 设置选择器弹出框文字颜色
   */
  selectedInfoBoxTextColor: string;
  /**
   * 设置选择器弹出框边框颜色
   */
  selectedInfoBoxBorderColor: string;
  /**
   * 设置选择器背景颜色
   */
  selectedInfoBoxBackgroundColor: string;
  /**
   * 选中时X坐标边框线宽
   */
  selectedLabelBorderWidth: number;
  /**
   * 选中时X坐标边框线颜色
   */
  selectedLabelBorderColor: string;
  /**
   * 设置背景色顶部颜色
   */
  backgroundFillTopColor: string;
  /**
   * 设置背景色底部颜色
   */
  backgroundFillBottomColor: string;
  /**
   * 设置分时线颜色
   */
  timeLineColor: string;
  /**
   * 设置分时线填充渐变的顶部颜色
   */
  timeLineFillTopColor: string;
  /**
   * 设置分时线填充渐变的底部颜色
   */
  timeLineFillBottomColor: string;
  /**
   * 分时线呼吸灯的颜色
   */
  timeLineEndPointColor: string;
  /**
   * 分时线呼吸灯的颜色半径
   */
  timeLineEndRadius: number;
  /**
   * 选中十字线X轴坐标连框纵向内边距
   */
  selectedDateBoxVerticalPadding: number;
  /**
   * 选中十字线X轴坐标连框横向内边距
   */
  selectedDateBoxHorizontalPadding: number;
  /**
   * 设置主实图图例距离视图上边缘的距离
   */
  mainLegendMarginTop: number;
  /**
   * 设置图例距离视图左边缘的距离
   */
  legendMarginLeft: number;
  /**
   * 设置涨的颜色
   */
  increaseColor: string;
  /**
   * 设置跌的颜色
   */
  decreaseColor: string;
  /**
   * 设置是否自适应X左右边轴坐标的位置,默认true
   */
  betterXLabel: boolean;
  /**
   * 设置坐标文字大小
   */
  labelTextSize: number;
  /**
   * 设置坐标轴坐标文字颜色
   */
  labelTextColor: string;
  /**
   * 设置Y轴显示在左侧/右侧
   */
  yLabelAlign: boolean;
  /**
   * b设置是否自适应X左右边轴坐标的位置,默认true
   */
  betterSelectedXLabel: boolean;
  /**
   * 统一设置设置文字大小
   */
  commonTextSize: number;
  /**
   * 设置上方padding
   */
  mainMarginTop: number;
  /**
   * 设置下方padding
   */
  paddingBottom: number;
  /**
   * 子视图的顶部padding
   */
  childPaddingTop: number;
  /**
   * 设置通用文字颜色
   */
  commonTextColor: string;
  /**
   * 全局通用线宽
   */
  lineWidth: number;
  /**
   * 设置每根蜡烛图宽度
   */
  candleWidth: number;
  /**
   * 设置蜡烛线画笔宽(空心时的线宽)
   */
  candleLineWidth: number;
  /**
   * 设置主视图最大/最小值文字颜色
   */
  limitTextColor: string;
  /**
   * 蜡烛是否空心
   */
  candleHollow: CandleHollow;
  /**
   * 设置背景网格线宽
   */
  gridLineWidth: number;
  /**
   * 设置背景网格线颜色
   */
  gridLineColor: string;
  /**
   * 设置背景网格行数
   */
  gridLineRows: number;
  /**
   * 设置背景网格列数
   */
  gridLineColumns: number;
  /**
   * macd空心时线宽
   */
  macdStrokeWidth: number;
  /**
   * 设置macd 上涨颜色
   */
  macdIncreaseColor: string;
  /**
   * 设置macd 下跌颜色
   */
  macdDecreaseColor: string;
  /**
   * macd柱状图宽
   */
  macdWidth: number;
  /**
   * 设置DIF颜色
   */
  difColor: string;
  /**
   * 设置DEA颜色
   */
  deaColor: string;
  /**
   * 设置MACD颜色
   */
  macdColor: string;
  /**
   * 设置WR1颜色
   */
  wr1Color: string;
  /**
   * 设置WR2颜色
   */
  wr2Color: string;
  /**
   * 设置WR3颜色
   */
  wr3Color: string;
  /**
   * 设置K颜色
   */
  kColor: string;
  /**
   * 设置D颜色
   */
  dColor: string;
  /**
   * 设置J颜色
   */
  jColor: string;
  /**
   * 设置RSI1颜色
   */
  rsi1Color: string;
  /**
   * 设置RSI2颜色
   */
  rsi2Color: string;
  /**
   * 设置RSI3颜色
   */
  rsi3Color: string;
  /**
   * 设置MA1颜色
   */
  ma1Color: string;
  /**
   * 设置MA2颜色
   */
  ma2Color: string;
  /**
   * 设置MA3颜色
   */
  ma3Color: string;
  /**
   * 设置交易量MA1颜色
   */
  volMa1Color: string;
  /**
   * 设置交易量MA2颜色
   */
  volMa2Color: string;
  /**
   * 交易量图例颜色
   */
  volLegendColor: string;
  /**
   * 设置当成交量视图显示为线时的颜色
   */
  volLineChartColor: string;
  /**
   * 交易量图例距离量视图上边缘的距离
   */
  volLegendMarginTop: number;
}

declare class ByronKlineComponent extends React.Component<
  Partial<ByronKlineProps> & ViewProps
> {}
export default class ByronKlineChart extends ByronKlineComponent {}
export declare function dispatchByronKline(
  event: KLineEvent,
  list?: Array<KLineBar>
): void;
