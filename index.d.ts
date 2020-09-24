import * as React from "react";

export interface KLineBar {
  amount: number;
  open: number;
  close: number;
  high: number;
  id: number;
  count: number;
  low: number;
  vol: number;
}

export type KLineEvent = "update" | "add";

export interface KLineCallbackParams {
  id: number;
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
  locales?: Array<string>;

  /**
   * 指标配置
   */
  indicators?: Array<KLineIndicator>;

  /**
   * 价格精度
   */
  pricePrecision?: number;

  /**
   * 数量精度
   */
  volumePrecision?: number;

  /**
   * 图表请求加载更多历史数据
   */
  onMoreKLineData?: (params: KLineCallbackParams) => void;

  /**
   * 周期
   */
  period?: number;
}

declare class ByronKlineComponent extends React.Component<ByronKlineProps> {}
export default class ByronKlineChart extends ByronKlineComponent {}
export declare function dispatchByronKline(
  event: KLineEvent,
  list?: Array<KLineBar>
): void;
