# react-native-sticky-notification

Customizable Sticky Notification for Android, that contains maximum of five clickable buttons. This act as a Service in android. You have to use the buttons respectively for any operataions in your react native project.

## Installation

### Step 1
```sh
npm install react-native-sticky-notification
```
### Step 2
Go to your ```android/app/src/main/AndroidManifest.xml``` and then add Permissions for Foreground Service in your ```AndroidManifest.xml``` as below.
```xml
<uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>    <!--   Add this   -->
```

### Step 3
Go to your ```android/app/src/main/AndroidManifest.xml``` and then Register Service by adding this line inside ```application tag```
```xml

<application>
    ....
    <service android:name=".StickyNotificationService" android:enabled="true"/>   <!--   Add this   -->
</application>
```

### Step 4
Go to your ```android/app/src/main/res/drawable```. If the ```res``` and ```drawable``` directories does not exist, then you have to create it and continue the below steps.

Add the Image resources (uses for buttons background images) inside the ```drawable``` directory.

### Step 5
Go to your ```android/app/src/main/res/layout```. If the ```layout``` directory does not exist inside the ```res``` directory, then you have to create it and continue the below steps.

Create an xml file inside the ```layout``` directory, and the file name should be ```notification_panel.xml```, then copy and paste the below code to the ```notification_panel.xml``` file and make the changes mentioned as comments in the below code.

This is the Notification Appearance Design in the Notification Panel.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
  android:orientation="horizontal"
  android:gravity="center"
  android:layout_width="match_parent"
  android:layout_height="wrap_content">
  
  <!-- One Button Code Start -->  
  <LinearLayout
    android:id="@+id/b1"                        
                <!-- ID should be same -->   
    android:layout_margin="10dp"
    android:gravity="center"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:layout_gravity="center">
    <ImageView
      android:layout_gravity="center"
      android:layout_width="30dp"
      android:layout_height="30dp"
      android:layout_marginHorizontal="10dp"
      android:src="@drawable/record"/>            <!-- android:src="@drawable/YOUR_IMAGE_NAME" -->
    <TextView
     android:id="@+id/t1"                         
              <!-- ID should be same --> 
     android:layout_width="wrap_content"
     android:layout_height="wrap_content"
     android:layout_gravity="center"
     android:gravity="center"/>
  </LinearLayout>
<!-- One Button Code End -->

  <LinearLayout
    android:id="@+id/b2"                           
                <!-- ID should be same -->
    android:layout_margin="10dp"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:gravity="center"
    android:layout_gravity="center">
    <ImageView
      android:layout_gravity="center"
      android:layout_width="30dp"
      android:layout_height="30dp"
      android:layout_marginHorizontal="10dp"
      android:src="@drawable/screenshot"/>         <!-- android:src="@drawable/YOUR_IMAGE_NAME" -->
    <TextView
      android:id="@+id/t2"                         
              <!-- ID should be same -->
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:layout_gravity="center"
      android:gravity="center"/>
  </LinearLayout>

  <LinearLayout
    android:id="@+id/b3"                            
                <!-- ID should be same -->
    android:layout_margin="10dp"
    android:gravity="center"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:layout_gravity="center">
    <ImageView
      android:layout_gravity="center"
      android:layout_width="30dp"
      android:layout_height="30dp"
      android:layout_marginHorizontal="10dp"
      android:src="@drawable/tools"/>                <!-- android:src="@drawable/YOUR_IMAGE_NAME" -->
    <TextView
      android:id="@+id/t3"                           
              <!-- ID should be same -->
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:layout_gravity="center"
      android:gravity="center"/>
  </LinearLayout>

  <LinearLayout
    android:id="@+id/b4"                              
                <!-- ID should be same -->
    android:layout_margin="10dp"
    android:gravity="center"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:layout_gravity="center">
    <ImageView
      android:layout_width="30dp"
      android:layout_height="30dp"
      android:layout_gravity="center"
      android:layout_marginHorizontal="10dp"
      android:src="@drawable/home"/>                   <!-- android:src="@drawable/YOUR_IMAGE_NAME" -->
    <TextView
      android:id="@+id/t4"                             
              <!-- ID should be same -->
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:layout_gravity="center"
      android:gravity="center"/>
  </LinearLayout>

  <LinearLayout
    android:id="@+id/b5"                               
                <!-- ID should be same -->
    android:layout_margin="10dp"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:gravity="center"
    android:layout_gravity="center">
    <ImageView
      android:layout_gravity="center"
      android:layout_width="30dp"
      android:layout_height="30dp"
      android:layout_marginHorizontal="10dp"
      android:src="@drawable/exit"/>                    <!-- android:src="@drawable/YOUR_IMAGE_NAME" -->
    <TextView
      android:id="@+id/t5"                              
              <!-- ID should be same -->                              
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:layout_gravity="center"
      android:gravity="center"/>
  </LinearLayout>
</LinearLayout>
```

### Step 6
Update your ```MainActivity.java``` to use ```react-native-sticky-notification``` via the following changes.

```java
import com.facebook.react.ReactActivity;

// ...other imports

import android.content.Intent;    //Add this Line
import com.reactnativestickynotification.Adapter.StickyNotificationHelper;  //Add this Line

public class MainActivity extends ReactActivity {

public boolean isFromMinimized = false;     //Add this Line
   
    // ...other code 
    
  //Add this....Begin... 
  @Override
  public  void  onNewIntent(Intent  intent) {
    super.onNewIntent(intent);
    isFromMinimized=true;
    StickyNotificationHelper.open(intent);
  }
  
  @Override
  protected  void  onStart() {
    super.onStart();
    if(!isFromMinimized){
      StickyNotificationHelper.open(getIntent());
    }

  }
  //...End
  
  //...other code  
}
```



## Usage
In your ```App.js``` (Root Folder)

```Note: You can use this for Navigations```

```js
//...
import {
  StickyNotificationService,
  createChannel,
  stopService,
  startService,
  removeOnClickListener,
} from 'react-native-sticky-notification';

export const App = () => {
// ...

let config = {
    channelId: "YOUR_CHANNEL_ID",           //required
    channelName: 'YOUR_CHANNEL_NAME',       //required
    displayTexts: ['abc', 'def', 'ghi'],    //default value is ["b1","b2","b3","b4","b5"]
    exitEnabled: false,                     //default value is true    //If true Service stopped when click the last button
    icon: 'app-icon-rounded',               //1. app-icon 2.app-icon-rounded 3.other  //default "app-icon"
  };
  
  useEffect(() => {
  
    // ...
 
    return () => {
      // ...
      removeOnClickListener();
    };
  }, []);
  
  createChannel(config)
            .then((action) => {
              console.log(action);   // returns Successfull Message
            })
            .catch((error) => {
              console.log(error);    // returns Error if any problem
            });
   
   startService()
            .then((res) => {
              console.log(res);       // returns Successfull Message
            })
            .catch((err) => {
              console.log(err);       // returns Error Message
            });
   
   stopService()
            .then((res) => {
              console.log(res);      // returns Successfull Message
            })
            .catch((err) => {
              console.log(err);      // returns Error Message
            });
            
    const onPressButton = (clickedButton) => {
          console.log(clickedButton);           //returns {"action": CLICKED_BUTTON_NAME ( from config.displayTexts which you created above )}. 
          
          // Do your operations according to this result...
    };
  
  // ...
  
  return(
  // ...
  <StickyNotificationService onPressButton={onPressButton} />
  // ...
  )
}
  
```

Method | #Type | #Description | 
--- | --- | --- | --- |
Seconds | 301 | 283 | 


## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
