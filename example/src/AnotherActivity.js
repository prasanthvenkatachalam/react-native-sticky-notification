import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

const AnotherActivity = () => {
  return (
    <View
      style={{
        flex: 1,
        backgroundColor: 'red',
        justifyContent: 'center',
        alignItems: 'center',
      }}
    >
      <Text>Hello</Text>
    </View>
  );
};

export default AnotherActivity;

const styles = StyleSheet.create({});
