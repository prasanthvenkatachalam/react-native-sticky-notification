package com.example.reactnativestickynotification;

import static com.reactnativestickynotification.StickyNotificationModule.props;

import com.facebook.react.ReactActivity;
import com.reactnativestickynotification.Adapter.StickyNotificationHelper;

import android.util.Log;
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
//    String btn = intent.getStringExtra ("action");
//
//    if(btn!=null){
//      props.onPress(btn);
//      Log.d("YESSIFF","YESSS");
//    }
    isFromMinimized=true;
StickyNotificationHelper.open(intent);

  }

  @Override
  protected  void  onStart() {
    super.onStart();
    if(!isFromMinimized){
      StickyNotificationHelper.open(getIntent());
    }

//    String btn = getIntent().getStringExtra("action");
//
//      if(btn!=null){
//        props.onPress(btn);
//        props.onPress(props.displayTexts()[2]);
//        Log.d("NOOO",btn);
//        props.onPress(props.displayTexts()[2]);
//      }

//    if(!isFromminimizedState){
//      String btn = getIntent().getStringExtra("action");
//      Log.d("NOOO","NOOO");
//      if(btn!=null){
////        props.onPress(props.displayTexts()[2]);
//        Log.d("NOOO",btn);
////        props.onPress(props.displayTexts()[2]);
//      }
//    }





  }


}
