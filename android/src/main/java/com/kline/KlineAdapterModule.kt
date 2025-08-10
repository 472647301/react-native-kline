package com.kline

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.module.annotations.ReactModule
import com.kline.model.KLineEntity

@ReactModule(name = KlineAdapterModule.NAME)
class KlineAdapterModule(reactContext: ReactApplicationContext) :
  NativeKlineAdapterSpec(reactContext)  {

  override fun getName(): String {
    return NAME
  }

  private fun fetchEntity(data: ReadableMap): KlineEntityBar {
    val entity = KlineEntityBar()
    entity.open = data.getDouble("open").toFloat()
    entity.high = data.getDouble("high").toFloat()
    entity.low = data.getDouble("low").toFloat()
    entity.close = data.getDouble("close").toFloat()
    entity.volume = data.getDouble("vol").toFloat()
    entity.date = data.getDouble("id").toLong()
    return entity
  }

  override fun resetData(
    list: ReadableArray?,
    resetShowPosition: Boolean?,
    resetLastAnim: Boolean?
  ) {
    if (list == null) return
    val bars = ArrayList<KLineEntity>()
    for (i in 0 until list.size()) {
      val item = list.getMap(i) ?: continue
      val entity = fetchEntity(item)
      bars.add(entity)
    }
    if (resetShowPosition != null && resetLastAnim != null) {
      KlineViewManager.adapter.resetData(bars, resetShowPosition, resetLastAnim)
    } else if (resetShowPosition != null) {
      KlineViewManager.adapter.resetData(bars, resetShowPosition)
    } else {
      KlineViewManager.adapter.resetData(bars)
    }
  }

  override fun addNewData(data: ReadableMap?, resetShowPosition: Boolean?) {
    if (data == null) return
    if (resetShowPosition != null) {
      KlineViewManager.adapter.addNewData(fetchEntity(data), resetShowPosition)
    } else {
      KlineViewManager.adapter.addNewData(fetchEntity(data))
    }
  }

  override fun addHistoryData(list: ReadableArray?, resetShowPosition: Boolean?) {
    if (list == null) return
    val bars = ArrayList<KLineEntity>()
    for (i in 0 until list.size()) {
      val item = list.getMap(i) ?: continue
      val entity = fetchEntity(item)
      bars.add(entity)
    }
    KlineViewManager.adapter.addHistoryData(bars, resetShowPosition ?: false)
  }

  override fun changeItem(position: Double, data: ReadableMap?) {
    if (data == null) return
    val entity = fetchEntity(data)
    KlineViewManager.adapter.changeItem(position.toInt(), entity)
  }

  companion object {
    const val NAME = "KlineAdapter"
  }
}
