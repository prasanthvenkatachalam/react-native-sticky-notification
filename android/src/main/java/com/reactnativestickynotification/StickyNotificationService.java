package com.reactnativestickynotification;

import static com.reactnativestickynotification.StickyNotificationModule.CHANNEL_ID;
import static com.reactnativestickynotification.StickyNotificationModule.props;


import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.List;

public class StickyNotificationService extends Service{
  @Nullable
  @Override
  public IBinder onBind(Intent intent) {

    return null;
  }

  @Override
  public void onCreate() {
    super.onCreate();

  }

  @Override
  public void onDestroy() {
    super.onDestroy();

  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    if(intent!=null) {

      switch (intent.getAction()) {
        case "startService": {
          startForegroundService();

          break;
        }
        case "b1": {
          if (isAppOnForeground(this)) {
            props.onPress(props.displayTexts()[0]);
          } else {
            enableActivity(props.displayTexts()[0]);
          }
          exit(1);
          break;
        }
        case "b2": {
          if (isAppOnForeground(this)) {
            props.onPress(props.displayTexts()[1]);
          } else {
            enableActivity(props.displayTexts()[1]);
          }

          exit(2);
          break;
        }
        case "b3": {
          if (isAppOnForeground(this)) {
            props.onPress(props.displayTexts()[2]);
          } else {
            enableActivity(props.displayTexts()[2]);
          }

          exit(3);
          break;
        }
        case "b4": {
          if (isAppOnForeground(this)) {
            props.onPress(props.displayTexts()[3]);
          } else {
            enableActivity(props.displayTexts()[3]);
          }

          exit(4);
          break;
        }
        case "b5": {
          if (isAppOnForeground(this)) {
            props.onPress(props.displayTexts()[4]);
          } else {
            enableActivity(props.displayTexts()[4]);
          }
          exit(5);
          if (props.buttonsCount() == 0) {

            stopForeground(true);
            stopSelf();
          }

          break;
        }
      }
      Intent it = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
      sendBroadcast(it);

    }


    return START_STICKY;
  }

  private void enableActivity(String pressedButton) {

    if(!isAppOnForeground(this)) {
      Intent intent1 = new Intent(this, getMainActivityClass(StickyNotificationModule.reactContext));
      intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      intent1.putExtra("action",pressedButton);
      startActivity(intent1);

    }
  }

  private boolean isAppOnForeground(Context context) {
    ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
    if (appProcesses == null) {
      return false;
    }
    final String packageName = context.getPackageName();
    for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
      if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
        return true;
      }
    }
    return false;
  }


  public void startForegroundService(){


    RemoteViews notificationLayout = new RemoteViews(getPackageName(),R.layout.notification_panel);
    Intent notificationIntent = new Intent(this, getMainActivityClass(StickyNotificationModule.reactContext));
    PendingIntent pendingIntent = PendingIntent.getActivity(this,
      0, notificationIntent, 0);





    if(StickyNotificationModule.props!=null){

      Integer[] integer =new Integer[]{R.id.t1,R.id.t2,R.id.t3,R.id.t4,R.id.t5};
      for(int i =0;i<props.displayTexts().length;i++){
        notificationLayout.setTextViewText(integer[i],StickyNotificationModule.props.displayTexts()[i]);
      }

      if(props.buttonsCount()==4){
        notificationLayout.setViewVisibility(R.id.b5, View.GONE);
      }
      else if(props.buttonsCount()==3){
        notificationLayout.setViewVisibility(R.id.b5,View.GONE);
        notificationLayout.setViewVisibility(R.id.b4,View.GONE);
      }
      else if(props.buttonsCount()==2){
        notificationLayout.setViewVisibility(R.id.b5,View.GONE);
        notificationLayout.setViewVisibility(R.id.b4,View.GONE);
        notificationLayout.setViewVisibility(R.id.b3,View.GONE);
      }

      else if(props.buttonsCount()==1){
        notificationLayout.setViewVisibility(R.id.b5,View.GONE);
        notificationLayout.setViewVisibility(R.id.b4,View.GONE);
        notificationLayout.setViewVisibility(R.id.b3,View.GONE);
        notificationLayout.setViewVisibility(R.id.b2,View.GONE);

      }

    }

    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
      .setContentTitle("Notification Service")
      .setContentText("Notification Service")
      .setCustomContentView(notificationLayout)
      .setContentIntent(pendingIntent)
      .setAutoCancel(true);

if(props.icon().equals("app-icon")){
  notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
}
else if(props.icon().equals("app-icon-rounded")){
  notificationBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
}
else if(props.icon().equals("other")){
  notificationBuilder.setSmallIcon(R.drawable.notification_icon);
}



    Intent recordIntent = new Intent(this, StickyNotificationService.class);
    recordIntent.setAction("b1");
    PendingIntent pendingRecordIntent = PendingIntent.getService(this, 0, recordIntent, 0);
    notificationLayout.setOnClickPendingIntent(R.id.b1,pendingRecordIntent);

    Intent screenshotIntent = new Intent(this, StickyNotificationService.class);
    screenshotIntent.setAction("b2");
    PendingIntent pendingScreenshotIntent = PendingIntent.getService(this, 0, screenshotIntent, 0);

    notificationLayout.setOnClickPendingIntent(R.id.b2,pendingScreenshotIntent);

    Intent toolsIntent = new Intent(this, StickyNotificationService.class);
    toolsIntent.setAction("b3");
    PendingIntent pendingToolsIntent = PendingIntent.getService(this, 0, toolsIntent, 0);
    notificationLayout.setOnClickPendingIntent(R.id.b3,pendingToolsIntent);

    Intent homeIntent = new Intent(this, StickyNotificationService.class);
    homeIntent.setAction("b4");
    PendingIntent pendingHomeIntent = PendingIntent.getService(this, 0, homeIntent, 0);
    notificationLayout.setOnClickPendingIntent(R.id.b4,pendingHomeIntent);

    Intent exitIntent = new Intent(this, StickyNotificationService.class);
    exitIntent.setAction("b5");
    PendingIntent pendingExitIntent = PendingIntent.getService(this, 0, exitIntent, 0);
    notificationLayout.setOnClickPendingIntent(R.id.b5,pendingExitIntent);

    Notification notification = notificationBuilder.build();

    startForeground(1, notification);

  }

  public void exit(int position){

    if( props.buttonsCount()==position && props.exitEnabled() ){
      stopForeground(true);
      stopSelf();
    }
  }
  private Class getMainActivityClass(Context context) {
    String packageName = context.getPackageName();
    Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
    if (launchIntent == null || launchIntent.getComponent() == null) {
      return null;
    }
    try {
      return Class.forName(launchIntent.getComponent().getClassName());
    } catch (ClassNotFoundException e) {
      return null;
    }
  }
}
