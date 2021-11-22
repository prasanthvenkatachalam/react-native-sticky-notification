package com.reactnativestickynotification.Adapter;

public interface StickyNotificationProps {

  String[] displayTexts();
  String channelId();
  String channelName();
  Boolean exitEnabled();
  int buttonsCount();
  String icon();
  void onPress(String clickedButton);
}
