import { useRef, forwardRef, useImperativeHandle } from 'react';
import type { NativeProps } from './KlineViewNativeComponent';
import type { KLineEntity, Spec } from './NativeKlineAdapter';
import KlineView from './KlineViewNativeComponent';
import KlineAdapter from './NativeKlineAdapter';

export * from './KlineViewNativeComponent';
export type { KLineEntity } from './NativeKlineAdapter';

export type KLineChartProps = NativeProps;
export type KLineChart = Spec & {
  getData(): KLineEntity[];
};

export const KLineChart = forwardRef<KLineChart, KLineChartProps>(
  (props, ref) => {
    const listRef = useRef<KLineEntity[]>([]);

    useImperativeHandle(ref, () => ({
      resetData(list, resetShowPosition, resetLastAnim) {
        listRef.current = list;
        KlineAdapter.resetData(list, resetShowPosition, resetLastAnim);
      },
      changeItem(position, data) {
        if (position < 0 || position >= listRef.current.length) return;
        listRef.current[position] = data;
        KlineAdapter.changeItem(position, data);
      },
      getConstants: KlineAdapter.getConstants,
      addNewData(data, resetShowPosition) {
        if (!listRef.current.length) return;
        listRef.current.push(data);
        KlineAdapter.addNewData(data, resetShowPosition);
      },
      addHistoryData(list, resetShowPosition) {
        if (!listRef.current.length) return;
        listRef.current = list.concat(listRef.current);
        KlineAdapter.addHistoryData(list, resetShowPosition);
      },
      getData() {
        return listRef.current;
      },
    }));

    return <KlineView {...props} />;
  }
);
