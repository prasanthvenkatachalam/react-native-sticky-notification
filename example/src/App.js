import React, { useEffect, useState } from 'react';

import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import {
  StickyNotificationService,
  createChannel,
  stopService,
  startService,
  removeOnClickListener,
} from 'react-native-sticky-notification';

export default function App() {
  let config = {
    channelId: 'sampleproject', //required
    channelName: 'sampleproject', //required
    displayTexts: ['abc', 'def', 'ghi'], //default value is ["b1","b2","b3","b4","b5"]
    exitEnabled: false, //default value is true            //If true Service stopped when click the last button
    icon: 'app-icon', //1. app-icon 2.app-icon-rounded 3.other  //default "app-icon"
  };

  useEffect(() => {
    return () => {
      removeOnClickListener();
    };
  }, []);

  const onPressButton = (clickedButton) => {
    console.log(clickedButton, 'onPressed');
  };

  return (
    <View style={styles.container}>
      <StickyNotificationService onPressButton={onPressButton} />

      <TouchableOpacity
        style={styles.button}
        onPress={() => {
          createChannel(config)
            .then((e) => {
              console.log(e);
            })
            .catch((e) => {
              console.log(e);
            });
        }}
      >
        <Text>Create Channel</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.button}
        onPress={() => {
          startService()
            .then((res) => {
              console.log(res, 'response');
            })
            .catch((err) => {
              console.log(err, 'error');
            });
        }}
      >
        <Text>Start Service</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.button}
        onPress={() => {
          stopService()
            .then((res) => {
              console.log(res, 'res');
            })
            .catch((err) => {
              console.log(err, 'err');
            });
        }}
      >
        <Text>Stop Service</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
  button: {
    height: 50,
    padding: 10,
    borderRadius: 10,
    backgroundColor: '#7FFFD4',
    elevation: 3,
    marginVertical: 10,
  },
});
