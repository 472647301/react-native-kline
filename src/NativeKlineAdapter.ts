import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';
import type { Double, Float } from 'react-native/Libraries/Types/CodegenTypes';
import type { Int32 } from 'react-native/Libraries/Types/CodegenTypes';

export interface KLineEntity {
  /** 秒时间戳 */
  id: Double;
  /** 开盘价 */
  open: Float;
  /** 最高价 */
  high: Float;
  /** 最低价 */
  low: Float;
  /** 收盘价 */
  close: Float;
  /** 成交量 */
  vol: Float;
}

export interface Spec extends TurboModule {
  /**
   * @name 重置K线数据
   * @param list K线数据
   * @param resetShowPosition 重置K线显示位置`default true`,如不需重置K线传入`false`
   * @param resetLastAnim 清楚最后一要柱子的动画,如果切换币需要使用方法传`true`
   * @summary 用于初次获取到k线数据调用
   */
  resetData(
    list: KLineEntity[],
    resetShowPosition?: boolean,
    resetLastAnim?: boolean
  ): void;
  /**
   * @name 添加新数据
   * @param data K线数据
   * @param resetShowPosition 重置K线显示位置`default false`
   * @summary 用于`WebSocket`实时推送过来的数据，需要自己判断是走`addNewData`还是`changeItem`
   */
  addNewData(data: KLineEntity, resetShowPosition?: boolean): void;
  /**
   * @name 加载更多数据
   * @param list K线数据
   * @param resetShowPosition 重置K线显示位置`default false`
   * @summary 用于滑动到最左侧加载更多数据调用
   */
  addHistoryData(list: KLineEntity[], resetShowPosition?: boolean): void;
  /**
   * @name 改变某个点的值
   * @param position 索引值
   * @param data K线数据
   * @summary 用于`WebSocket`实时推送过来的数据，需要自己判断是走`addNewData`还是`changeItem`
   */
  changeItem(position: Int32, data: KLineEntity): void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('KlineAdapter');
