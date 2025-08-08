package com.kline

import com.facebook.react.bridge.WritableMap
import com.facebook.react.uimanager.events.Event

class SlidListenerEvent(
  surfaceId: Int,
  viewTag: Int,
  val EVENT_NAME: String,
  val args: WritableMap?
) : Event<SlidListenerEvent>(surfaceId, viewTag) {
  override fun getEventName(): String = EVENT_NAME
  override fun getEventData(): WritableMap? = args
}
