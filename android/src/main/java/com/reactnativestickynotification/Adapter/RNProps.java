package com.reactnativestickynotification.Adapter;


public enum RNProps {


  DISPLAY_TEXTS("displayTexts"),
  CHANNEL_ID("channelId"),
  CHANNEL_NAME("channelName"),
  EXIT_ENABLED("exitEnabled");

  private String value;

  RNProps(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }
}
