import { useEffect } from 'react';
import { NativeModules, Platform, DeviceEventEmitter } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-sticky-notification' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

const StickyNotification = NativeModules.StickyNotification
  ? NativeModules.StickyNotification
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function multiply(a, b) {
  return StickyNotification.multiply(a, b);
}

export const StickyNotificationService = ({ onPress }) => {
  // useEffect(() => {
  //   return () => {
  //     DeviceEventEmitter.removeSubscription(subscription);
  //   };
  // }, []);

  // let subscription =

  DeviceEventEmitter.addListener('action', (e) => {
    onPress(e);
  });

  return null;
};

export const removeOnClickListener = () => {
  DeviceEventEmitter.removeAllListeners();
};

export const createChannel = ({ channelId, channelName, ...props }) => {
  return new Promise((resolve, reject) => {
    if (!channelId) {
      reject('Channel Id is required!');
      return;
    }

    if (!channelName) {
      reject('Channel Name is required!');
      return;
    }

    if (typeof channelId != 'string') {
      reject('Channel Id must be String!');
      return;
    }
    if (typeof channelName != 'string') {
      reject('Channel Name must be String!');
      return;
    }

    resolve({
      channelId: channelId,
      channelName: channelName,
      ...props,
    });
    StickyNotification.createChannel({
      channelId: channelId,
      channelName: channelName,
      ...props,
    });
  });
};

export const startService = (e) => {
  StickyNotification.startService()
    .then((e) => {})
    .catch((e) => {
      console.log(e);
    });
};

export const stopService = () => {
  StickyNotification.stopService();
};
