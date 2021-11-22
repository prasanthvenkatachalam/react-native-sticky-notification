import React, { useEffect, useState } from 'react';

import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import {
  StickyNotificationService,
  createChannel,
  stopService,
  startService,
  removeOnClickListener,
} from 'react-native-sticky-notification';
import AnotherActivity from './AnotherActivity';

export default function App() {
  let config = {
    channelId: 'sampleproject', //required
    channelName: 'sampleproject', //required
    displayTexts: ['abc', 'def', 'ghi'], //default value is ["b1","b2","b3","b4","b5"]
    exitEnabled: false, //default value is true            //If true Service stopped when click the last button
  };

  useEffect(() => {
    return () => {
      removeOnClickListener();
    };
  }, []);

  const onPress = (e) => {
    console.log(e, 'onPressed');
  };

  return (
    <View style={styles.container}>
      <StickyNotificationService onPress={(e) => onPress(e)} />

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
          startService();
        }}
      >
        <Text>Start Service</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.button}
        onPress={() => {
          stopService();
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
