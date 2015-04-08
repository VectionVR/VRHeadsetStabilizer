##How to use this repository
##How to build 
- Clone this project locally
```
git clone https://github.com/VectionVR/VRHeadsetStabilizer.git VectionVRStabilizer
```
- Import the project in stabilizer folder in you favorite Java IDE (We use [eclipse](http://www.eclipse.org) and [Netbeans](https://netbeans.org/downloads/) but any IDE supporting maven should be able to handle the project).
- Build the project with assembly profile to generate platform dependant "executables" or directly launch the GuiMain class. 
```
cd stabilizer
mvn clean install -p assembly
```
##Setup
There is no setup required, when starting the project, the main window should appear immediately.
##Known problems
#####*Application crashes at startup / I don't see the spinning cube at the bottom of the aplication*
We noticed a problem with JoGL (the OpenGL java library) that causes the application to crash at startup. To avoid that we disabkle the cube rendering when JoGL is not initialised correctly. This can usually be fixed by updating the graphic card driver (and management software in case of nvidia graphic card)
##Disclaimer
######*VectionVR is in no way affiliated with YEI or Oculus. All source code or binary library is provided as is. VectionVR, Bnome or any of their representatives can in no way be held liable for any damages caused by using this software.*