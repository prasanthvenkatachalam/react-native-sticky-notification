package com.reactnativestickynotification.Adapter;

import static com.reactnativestickynotification.StickyNotificationModule.props;

import android.content.Intent;
<<<<<<< HEAD
import android.os.Looper;

=======
>>>>>>> 0c8a7b2f16dc10a531e2deda5911979a9ccf5e07

public class StickyNotificationHelper {

  public static void open(Intent intent){
    new android.os.Handler(Looper.getMainLooper()).postDelayed(
      new Runnable() {
        public void run() {
          String btn = intent.getStringExtra ("action");
          if(btn!=null){
            props.onPress(btn);

          }
        }
      },
      100);

  }
}
