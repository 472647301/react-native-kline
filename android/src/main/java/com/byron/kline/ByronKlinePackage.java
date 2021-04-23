package com.byron.kline;

import java.util.Collections;
import java.util.List;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import org.jetbrains.annotations.NotNull;

public class ByronKlinePackage implements ReactPackage {
    @Override
    public @NotNull List<NativeModule> createNativeModules(@NotNull ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    @Override
    public @NotNull List<ViewManager> createViewManagers(@NotNull ReactApplicationContext reactContext) {
        return Collections.singletonList(new ByronKlineManager());
    }
}
