package com.ga2mer.seekbar;

import android.util.Log;

import javax.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.common.MapBuilder;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.ViewProps;

import java.util.ArrayList;
import java.util.Map;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
public class RNSeekbarViewManager extends BaseViewManager<SeekbarContainerView, RNSeekbarShadowNode> {
        public static final String REACT_CLASS = "RNSeekBar";
        private static Object sSeekBarCtorLock = new Object();
        @Override
        public String getName() {
                return REACT_CLASS;
        }
        public static SeekBar createSeekBar(Context context, int style) {
                synchronized (sSeekBarCtorLock) {
                        return new SeekBar(context, null, style);
                }
        }
        @Override
        public @Nullable Map getExportedCustomDirectEventTypeConstants() {
                return MapBuilder.of("trackingTouch", (Object) MapBuilder.of("registrationName", "onTrackingTouch"));
        }
        @Override
        protected SeekbarContainerView createViewInstance(ThemedReactContext context) {
                return new SeekbarContainerView(context);
        }
        @ReactProp(name = ViewProps.COLOR, customType = "Color")
        public void setColor(SeekbarContainerView view, @Nullable Integer color) {
                view.setColor(color);
        }
        @ReactProp(name = "secondaryColor", customType = "Color")
        public void setSecondaryColor(SeekbarContainerView view, @Nullable Integer color) {
                view.setSecondaryColor(color);
        }
        @ReactProp(name = "bgColor", customType = "Color")
        public void setBGColor(SeekbarContainerView view, @Nullable Integer color) {
                view.setBGColor(color);
        }
        @ReactProp(name = "thumbColor", customType = "Color")
        public void setThumbColor(SeekbarContainerView view, @Nullable Integer color) {
                view.setThumbColor(color);
        }
        @ReactProp(name = "progress")
        public void setProgress(SeekbarContainerView view, int progress) {
                view.setProgress(progress);
        }
        @ReactProp(name = "secondaryProgress")
        public void setSecondaryProgress(SeekbarContainerView view, int progress) {
                view.setSecondaryProgress(progress);
        }
        @ReactProp(name = "max")
        public void setMax(SeekbarContainerView view, int max) {
                view.setMax(max);
        }
        @ReactProp(name = "styleAttr")
        public void setStyle(SeekbarContainerView view, @Nullable String styleName) {
                view.setStyle(styleName);
        }
        @Override
        public RNSeekbarShadowNode createShadowNodeInstance() {
                return new RNSeekbarShadowNode();
        }

        @Override
        public Class<RNSeekbarShadowNode> getShadowNodeClass() {
                return RNSeekbarShadowNode.class;
        }
        @Override
        protected void onAfterUpdateTransaction(SeekbarContainerView view) {
                view.apply();
        }
        @Override
        public void updateExtraData(SeekbarContainerView root, Object extraData) {
        }
        /* package */ static int getStyleFromString(@Nullable String styleStr) {
                return android.R.attr.seekBarStyle;
        }
}
