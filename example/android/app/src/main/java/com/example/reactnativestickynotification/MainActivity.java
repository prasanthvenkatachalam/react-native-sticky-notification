package com.example.reactnativestickynotification;


import com.facebook.react.ReactActivity;
import com.reactnativestickynotification.Adapter.StickyNotificationHelper;
import android.content.Intent;
public class MainActivity extends ReactActivity {

public boolean isFromMinimized = false;

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "StickyNotificationExample";
  }

  @Override
  public  void  onNewIntent(Intent  intent) {
    super.onNewIntent(intent);
    isFromMinimized=true;
    StickyNotificationHelper.open(intent);

  }

  @Override
  protected  void  onStart() {
    super.onStart();
    if(!isFromMinimized){
      StickyNotificationHelper.open(getIntent());
    }

  }


}
