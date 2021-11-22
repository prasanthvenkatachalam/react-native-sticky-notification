package com.reactnativestickynotification;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.reactnativestickynotification.Adapter.StickyNotificationAdapter;
import com.reactnativestickynotification.Adapter.StickyNotificationProps;

import java.util.List;

@ReactModule(name = StickyNotificationModule.NAME)
public class StickyNotificationModule extends ReactContextBaseJavaModule {
  public static final String NAME = "StickyNotification";
  public static ReactApplicationContext reactContext;
  public static String CHANNEL_ID = null;
  public static StickyNotificationProps props;

  public StickyNotificationModule(ReactApplicationContext reactContext) {
    super(reactContext);
    StickyNotificationModule.reactContext = reactContext;
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }


  @ReactMethod
  public void createChannel(@Nullable ReadableMap options, Promise promise) {
    if(options!=null){
      StickyNotificationModule.props = new StickyNotificationAdapter(options,promise);


      CHANNEL_ID = props.channelId(); //Set for global use

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        NotificationChannel serviceChannel = new NotificationChannel(
          props.channelId(),
          props.channelName(),
          NotificationManager.IMPORTANCE_DEFAULT
        );

        NotificationManager manager = reactContext.getSystemService(NotificationManager.class);
        manager.createNotificationChannel(serviceChannel);

      }

    }
  }



  @ReactMethod
  public void startService(Promise promise) {

    Intent intent = new Intent(reactContext,StickyNotificationService.class);
    intent.setAction("startService");


    try{
      if(CHANNEL_ID!=null){
        reactContext.startService(intent);
        promise.resolve("SERVICE_STARTED");
      }
      else{
        promise.reject("SERVICE_NOT_STARTED","Invalid Channel Id and Channel Name");
      }

    }
    catch(Exception e){
      Log.d("StartError","StartError "+ e);
      promise.reject("SERVICE_NOT_STARTED",e.getMessage());
    }

  }

  @ReactMethod
  public void stopService() {
    Intent intent = new Intent(reactContext,StickyNotificationService.class);
    reactContext.stopService(intent);

  }





}
