##Why this software
####VR Headset stabilization in a nutshell
As described in ![Problem description](http://www.vectionvr.com/img/explanation.jpg), to simulate the acceleration of a car, a motion simulator would lean the player's seat backwards. This would be interpreted by the Oculus Rift as looking up. Our goal is to solve this problem by changing VR headset reference orientation according to the motion platform current orientation. This process is obviously only reliable if a fast and accurate motion platform orientation sensor is used.
####Reference implementation
Our reference implementation is based on 3 elements:
- Modification of the Oculus Rift library to receive and use real ground orientation in orientation detection algorithm. This is described and available [here](https://github.com/VectionVR/OculusRiftPatch)
- Java based software that will grab platform orientation and send it to our modified Oculus Rift library. 
- 800-1000Hz 9doF IMU providing high accuracy. Until now, the only affordable device with such high orientation detection rate we could find is from [YEI Technology](http://www.yeitechnology.com) 

##How to build 
- Grab the maven project from the [Stabilizer](https://github.com/VectionVR/VRHeadsetStabilizer/Stabilizer) project folder in this repository
```
git clone https://github.com/VectionVR/VRHeadsetStabilizer.git Stabilizer
```
- Import the project in you favorite Java IDE (We use [eclipse](http://www.eclipse.org) and [Netbeans](https://netbeans.org/downloads/) but any IDE supporting maven should be able to handle the project).
- Build the project with mvn clean install to generate platform dependant "executables" or directly launch the GuiMain class. 
```
mvn clean install
```

##Setup
There is no setup required, when starting the project, the main window should appear immediately.
##Improvements / Custom needs
We know that this software requires a lot of improvement and we will try to keep it up to date. If you want any changes to be done and cannot do it yourself or want any custom development, feel free to [contact-us](mailto:contact@vectionvr.com).
##Disclaimer
######*VectionVR is in no way affiliated with YEI or Oculus. All source code or binary library is provided as is. VectionVR, Bnome or any of their representatives can in no way be held liable for any damages caused by using this software.*