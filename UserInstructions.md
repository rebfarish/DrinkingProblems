# User Instructions

On first run, the app will ask for Google Sign-In authentication as well as permission to lock the phone screen.
Drinking Problems will require this permission to run properly.

Once running, the app will ask a math question. If the question is answered correctly the app will close. If answered incorrectly, the app will lock the screen for a set duration.
>*N.B. Currently the duration is set to 10 seconds. This duration can be adjusted in the QuestionsFragment class in the PhoneUnlockedReceiver class.*

There are two other buttons the user can interact with. 
* The call a cab button will open Uber on the device if it is installed. If Uber is not installed, the user will be asked if he/she would like to install it and will direct the user to the app store. 
* The emergency button will open the dialer screen with 911 already dialed.

Once the screen is locked, the user can still receive phone calls, and silence alarms, but cannot make a call or send a text or have any access to other apps on the device.

When a question is loaded, the app will run in fullscreen mode hiding the navigation drawer. Pressing the back button will NOT allow the user to exit the app. The back button is merely cosmetic.
