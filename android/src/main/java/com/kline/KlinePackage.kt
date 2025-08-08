package com.kline

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager
import java.util.ArrayList

class KlineViewPackage : ReactPackage {
  override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
    val viewManagers: MutableList<ViewManager<*, *>> = ArrayList()
    viewManagers.add(KlineViewManager())
    return viewManagers
  }

  override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
    val nativeModules: MutableList<NativeModule> = ArrayList()
    nativeModules.add(KlineAdapterModule(reactContext))
    return nativeModules
  }
}
