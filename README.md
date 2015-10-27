# mobile-status-change

Recently I have faced one issue, My phone got locked it's "too many pattern attempts" on lock screen and my mobile data + wi-fi were disabled. An internet connection was needed to pass that lock screen.

So I was on a mission to start my phone's internet connection using any method I can.

Firstly, I looked into adb commands, So there are shell commands available to control your mobile data and wi-fi status.

To enable/disable wifi:

$ adb shell svc wifi enable 
$ adb shell svc wifi disable

To enable/disable mobile data:
$ adb shell svc data enable 
$ adb shell svc data disable 

WI-Fi things work for me, but unfortunately I was not able to control mobile data (I am using HTC device, May be all HTC has same issue). And internet connection was needed to unlock my phone. So I wrote one application which receives the broadcast. 

Here is a play store link.

You can also download APK

Now, How to control mobile data. 


If above application was not installed into device install,  Download and Install it using adb command. adb install <-- apk location -->
You need to send broadcast from command prompt using adb commands listed below:

$ adb shell am broadcast -a yp.data.handlebroadcast -n yp.data.handle/.DataChangeReceiver --ez "wifiEnable" "true" --ez "mobileDataEnable" "true"
So there you go. You can change wifiEnable and mobileDataEnable value accordingly. This command will send a broadcast to installed application and application will do the rest of the work.

Here is some reference links:
How to install and Use ADB
http://adbshell.com/
Adb debug bridge
