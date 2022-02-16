# Introduction

This app is for calling number and send SMS through ADB. \n  
Official ADB interface for calling and sending SMS is not working on all devices, and has some  
incompatibility with terminal via some languages such as Golang.  

# Usage

1. Install [apk file](https://github.com/ali-imanpour/AdbCallSmsGateway/releases/download/v1.0.0/app-debug.apk) on your device and give needed permissions.
2. Connect your phone to computer via USB and enabled ADB in developer settings.
3. Install ADB on computer. `sudo apt install adb`


**To make call use in terminal** 

`adb shell am start -n imanpour.ali.callsmsgateway/imanpour.ali.callsmsgateway.MainActivity -e cmd 'call' -e phone '123456789'` 

 

**To send SMS use in terminal \n\n**  

    adb shell am start -n imanpour.ali.callsmsgateway/imanpour.ali.callsmsgateway.MainActivity -e cmd 'sendSMS' -e phone '123456789' -e message 'text message'  
  
be sure to give all needed permissions in app settings and use correct ADB  path in terminal.