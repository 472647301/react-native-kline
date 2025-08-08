package com.kline

import com.kline.model.KLineEntity

class KlineEntityBar: KLineEntity() {
  override fun getDate(): Long {
    return date
  }

  override fun getOpenPrice(): Float {
    return open
  }

  override fun getHighPrice(): Float {
    return high
  }

  override fun getLowPrice(): Float {
    return low
  }

  override fun getClosePrice(): Float {
    return close
  }

  override fun getVolumeCount(): Float {
    return volume
  }

  var date: Long = 0
  var open: Float = 0.0F
  var close: Float = 0.0F
  var high: Float = 0.0F
  var low: Float = 0.0F
  var volume: Float = 0.0F
}
