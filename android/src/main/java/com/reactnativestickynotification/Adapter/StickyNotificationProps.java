package com.reactnativestickynotification.Adapter;

public interface StickyNotificationProps {

  String[] displayTexts();
  String channelId();
  String channelName();
  Boolean exitEnabled();
  int buttonsCount();
  void onPress(String clickedButton);
}
