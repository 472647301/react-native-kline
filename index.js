import React, { useEffect } from "react";
import { UIManager, Platform } from "react-native";
import { requireNativeComponent, findNodeHandle } from "react-native";

let byronController = undefined;
const ByronKline = requireNativeComponent("ByronKline");

export const dispatchByronKline = (event = "init", list = []) => {
  if (!byronController) {
    return;
  }
  UIManager.dispatchViewManagerCommand(
    findNodeHandle(byronController),
    Platform.OS === "android"
      ? "ByronKline"
      : UIManager.getViewManagerConfig("ByronKline").Commands.byronController,
    [{ event, list }]
  );
};

export const CandleHollow = {
  NONE_HOLLOW: 0,
  ALL_HOLLOW: 1,
  DECREASE_HOLLOW: 2,
  INCREASE_HOLLOW: 3,
};

export const KLineIndicator = {
  MainMA: 0,
  MainBOLL: 1,
  MainNONE: 2,
  ChildMACD: 3,
  ChildKDJ: 4,
  ChildRSI: 5,
  ChildWR: 6,
  ChildNONE: 7,
  TimeLineShow: 8,
  TimeLineHide: 9,
  VolumeShow: 10,
  VolumeHide: 11,
};

const KlineComponent = (props, forwardedRef) => {
  const { onMoreKLineData, ...localProps } = props;
  const onMoreKLineDataEvent = onMoreKLineData
    ? (event) => onMoreKLineData(event.nativeEvent)
    : null;
  const _forwardedRef = (ref) => {
    byronController = ref;
    forwardedRef && forwardedRef(ref);
  };

  useEffect(() => {
    dispatchByronKline();
  }, []);

  return (
    <ByronKline
      {...localProps}
      ref={_forwardedRef}
      onRNMoreKLineData={onMoreKLineDataEvent}
    />
  );
};

const ByronKlineChart = React.forwardRef(KlineComponent);

ByronKlineChart.defaultProps = {
  datas: [],
  locales: [],
  indicators: [],
  pricePrecision: 2,
  volumePrecision: 2,
  mainBackgroundColor: '#18181A',
  increaseColor: "#00BD9A",
  decreaseColor: "#FF6960",
};

export default ByronKlineChart;
