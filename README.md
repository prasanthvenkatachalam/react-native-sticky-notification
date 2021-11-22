# react-native-sticky-notification

Customizable Sticky Notification for Android

## Installation

### Step 1
```sh
npm install react-native-sticky-notification
```
### Step 2
Go to your android/app/src/main/AndroidManifest.xml and then add Permissions for Foreground Service in your android manifest as below.
```xml
<uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>    <!--   Add this   -->
```

### Step 3
Go to your android/app/src/main/AndroidManifest.xml and then Register Service by adding this line inside "application tag"
```xml

<application>
    ....
    <service android:name=".StickyNotificationService" android:enabled="true"/>   <!--   Add this   -->
</application>
```


### Step 4
Register Service in your manifest inside application.
```sh

<application>
    ....
    <service android:name=".StickyNotificationService" android:enabled="true"/>
</application>
```


## Usage

```js
import { multiply } from "react-native-sticky-notification";

// ...

const result = await multiply(3, 7);
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
