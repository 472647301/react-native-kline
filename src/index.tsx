import { forwardRef, useImperativeHandle } from 'react';
import type { NativeProps } from './KlineViewNativeComponent';
import type { Spec } from './NativeKlineAdapter';
import KlineView from './KlineViewNativeComponent';
import KlineAdapter from './NativeKlineAdapter';

export * from './KlineViewNativeComponent';
export type { KLineEntity } from './NativeKlineAdapter';

export interface KLineChartProps extends NativeProps {}
export interface KLineChartRef extends Spec {}

export const KLineChart = forwardRef<KLineChartRef, KLineChartProps>(
  (props, ref) => {
    useImperativeHandle(ref, () => ({
      resetData: KlineAdapter.resetData,
      changeItem: KlineAdapter.changeItem,
      getConstants: KlineAdapter.getConstants,
      addLast: KlineAdapter.addLast,
    }));

    return <KlineView {...props} />;
  }
);
