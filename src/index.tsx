import { useRef, forwardRef, useImperativeHandle } from 'react';
import type { NativeProps } from './KlineViewNativeComponent';
import type { KLineEntity, Spec } from './NativeKlineAdapter';
import KlineView from './KlineViewNativeComponent';
import KlineAdapter from './NativeKlineAdapter';

export * from './KlineViewNativeComponent';
export type { KLineEntity } from './NativeKlineAdapter';

export interface KLineChartProps extends NativeProps {}
export interface KLineChartRef extends Spec {
  getData(): KLineEntity[];
}

export const KLineChart = forwardRef<KLineChartRef, KLineChartProps>(
  (props, ref) => {
    const datas = useRef<KLineEntity[]>([]);

    useImperativeHandle(ref, () => ({
      resetData(list, resetShowPosition, resetLastAnim) {
        datas.current = list;
        KlineAdapter.resetData(list, resetShowPosition, resetLastAnim);
      },
      changeItem(position, data) {
        datas.current[position] = data;
        KlineAdapter.changeItem(position, data);
      },
      getConstants: KlineAdapter.getConstants,
      addLast(data, resetShowPosition) {
        datas.current.push(data);
        KlineAdapter.addLast(data, resetShowPosition);
      },
      appendData(list) {
        datas.current = list.concat(datas.current);
        KlineAdapter.appendData(list);
      },
      getData() {
        return datas.current;
      },
    }));

    return <KlineView {...props} />;
  }
);
