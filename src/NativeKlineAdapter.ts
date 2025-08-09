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
   * 重置K线数据
   * @param list K线数据
   * @param resetShowPosition 重置K线显示位置`default true`,如不需重置K线传入`false`
   * @param resetLastAnim 清楚最后一要柱子的动画,如果切换币需要使用方法传`true`
   */
  resetData(
    list: KLineEntity[],
    resetShowPosition?: boolean,
    resetLastAnim?: boolean
  ): void;
  /**
   * @name 向尾部添加数据
   * @param data K线数据
   * @param resetShowPosition 重置K线显示位置`default true`,如不需重置K线传入`false`
   */
  addLast(data: KLineEntity, resetShowPosition?: boolean): void;
  /**
   * @name 改变某个点的值
   * @param position 索引值
   * @param data K线数据
   */
  changeItem(position: Int32, data: KLineEntity): void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('KlineAdapter');
