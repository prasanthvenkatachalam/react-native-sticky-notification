package com.reactnativestickynotification.Adapter;

import static com.reactnativestickynotification.Adapter.RNProps.CHANNEL_ID;
import static com.reactnativestickynotification.Adapter.RNProps.CHANNEL_NAME;
import static com.reactnativestickynotification.Adapter.RNProps.DISPLAY_TEXTS;
import static com.reactnativestickynotification.Adapter.RNProps.EXIT_ENABLED;
import static com.reactnativestickynotification.Adapter.RNProps.ICON;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.reactnativestickynotification.StickyNotificationModule;

import java.util.ArrayList;
import java.util.Objects;

import javax.annotation.Nullable;

public class StickyNotificationAdapter implements StickyNotificationProps {

  public ReadableMap props;
  public Promise promise;
  public int buttonsCount;

  public StickyNotificationAdapter(@Nullable ReadableMap props, Promise promise) {
    this.props = props;
    this.promise = promise;

  }

  @Override
  public String[] displayTexts() {
    return getStringArray(DISPLAY_TEXTS);
  }

  @Override
  public String channelId() {
    return getStringValue(CHANNEL_ID,"sticky_notification_service");
  }

  @Override
  public String channelName() {
    return getStringValue(CHANNEL_NAME,"sticky_notification_service");
  }

  @Override
  public Boolean exitEnabled() {
    return !props.hasKey(EXIT_ENABLED.value()) || props.getBoolean(EXIT_ENABLED.value());

  }

  @Override
  public int buttonsCount() {
    return buttonsCount;
  }

  @Override
  public String icon() {
    return getStringValue(ICON,"app-icon");
  }

  @Override
  public void onPress(String clickedButton) {
    WritableMap map = Arguments.createMap();
    map.putString("action", clickedButton);

    try {
      StickyNotificationModule.reactContext.
        getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
        .emit("action", map);
      promise.resolve(clickedButton);

    } catch (Exception e){

      promise.reject("Error",e.getMessage());
    }

  }


  public String[] getStringArray(RNProps prop){
    String[] displayTexts = new String[]{"b1", "b2", "b3", "b4", "b5"};

    if(props.hasKey(prop.value())){

      ArrayList<Object> str = Objects.requireNonNull(props.getArray("displayTexts")).toArrayList();
      buttonsCount=str.toArray().length;
      for(int i=0;i<str.toArray().length;i++){
        displayTexts[i]=str.get(i).toString();
      }

      return displayTexts;
    }
    return displayTexts;
  }


  private String getStringValue(RNProps prop, String defaultValue) {
    return props.hasKey(prop.value()) ? props.getString(prop.value()) : defaultValue;
  }
}
