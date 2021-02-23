[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.me/embeddedlab)

# Home Automation
In this project, NodeMCU is used to fetch the temperature and humidity data from the DHT11 temperature sensor, which is pulished to a broker.  
Similarly from Android Phone application and PC Application (written in Python), loads and led's can be turned on and off.
NodeMCU after measuring the temperature and humidity, it publishes this data to a broker, and this broker then publish this data to all the subscribers.  
Similarly the Android phone or PC application, pushes the data with which NodeMCU will control the devices connected with it. For publishing and subscribing, I am going to use MQTT at all the ends (NodeMcu, Android Phone, Server).  

[Blog Post](https://embeddedlaboratory.blogspot.com/2018/03/control-devices-using-android-phone-and.html)

##### Connection Diagram
The following is the connection diagarm.  
<img src="https://3.bp.blogspot.com/-M2NsrnWdIkU/WpvokrUgrTI/AAAAAAAAA1c/eu8GhXR2HosoGxcvCOSYfYmm_iJIc01UACLcBGAs/s1600/Connection%2BDiagram.PNG" width="40%"/>

##### Actual Setup
The following is the actual setup.  
<img src="https://2.bp.blogspot.com/-73Kv7D_V11I/WpvoYxXUmiI/AAAAAAAAA1Y/ur-a160RZ9ISfCmxhPthxiAke45y35XnwCLcBGAs/s1600/NodeMCU%2BRelay%2BSetup%2BLowRes.png" width="40%"/>  
The Arduino software is present in **HomeAutomation/HomeAutomation.ino** file

#### Android Application
The following is screenshot of the Android application.  
<img src="https://2.bp.blogspot.com/-jxWbx3Mcp9E/WpvpjUrFH4I/AAAAAAAAA1o/0UplkVQL1tgNQrALWJot8MFyFbIqhxHnwCLcBGAs/s1600/Android%2BApplication.png" width="30%" />  
The Android application is written in **B4A** and the complete source code is present inside the folder **"Application"**

#### Python Application
The following is screenshot of the Python application.  
![alt text](https://4.bp.blogspot.com/-3E40hZ8lQuc/Wpv7Ly1ryzI/AAAAAAAAA2c/-_Mwu1eWn6A3w28ALEj2X2JTqZnKdv2dwCLcBGAs/s1600/Python%2BApplication.PNG)  
The Python application is developed using **PyQt5** framework and is present inside the folder **Py-App**
