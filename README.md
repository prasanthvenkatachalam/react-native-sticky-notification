# react-native-sticky-notification

Customizable Sticky Notification for Android, that contains maximum of five clickable buttons. This act as a Service in android. You have to use the buttons respectively for any operataions in your project.

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
Go to your android/app/src/main/res/drawable. If the "res" and "drawable" directories does not exist, then you have to create it and continue the below steps  

Add the Image resources (uses for buttons background images) inside the "drawable" directory



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
