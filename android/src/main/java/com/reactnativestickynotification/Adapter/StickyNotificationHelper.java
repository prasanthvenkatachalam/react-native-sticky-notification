package com.reactnativestickynotification.Adapter;

import static com.reactnativestickynotification.StickyNotificationModule.props;

import android.content.Intent;

public class StickyNotificationHelper {

  public static void open(Intent intent){
    String btn = intent.getStringExtra ("action");

    if(btn!=null){
      props.onPress(btn);

    }
  }
}
