# Drinking Problems

----
Drinking Problems is an Android app for users who are drinking adult beverages. The aim is to prevent users from sending unwanted/bad decision texts and calls. It is ideal for users with a strong math background such as Mathematicians and Engineers.

## Current State of Completion

When opened, the app will load a randomly selected question from the database. If the user answers the question correctly, the app will close. If answered incorrectly, the app will lock the screen for a set amount of time (currently this is set to ten seconds).

There are a few elements that need to be corrected for a usable prototype. Firstly, if the user swipes up from the bottom of the screen the navigation bar will appear, allowing the user to simply back out of the app without answering a question. Secondly, the app has a limited bank of questions to pull from. After some time with the app a user could simply have the answers memorized. 

## API Versions and Hardware

The minimum required Android API is 21. It has been successfully tested on emulators and phones running API 27 and 28. Currently the app only runs in English and portrait orientation.

## Third-Party Libraries

Drinking problems uses MathView,  a third-party view library which displays math formulas on Android apps. Two rendering engines are  available: MathJax and KaTeX. Drinking Problems uses KaTeX. Support Android version 4.1 (Jelly Bean) and newer.

## External Services

Drinking Problems uses Google Sign In to log in.

## Aesthetic Improvements

The current design is very basic. The buttons could be styled differently, possibly in horrible neon flashing colors that would dissuade the user from trying to unlock the phone. The menu fragment also has a very basic design that could be reworked. Also a splash page when opening the app would be nice.

## Stretch Goals


* Setting a timer, so that if the user doesn’t answer the question in a set amount of time the screen will lock, as if the question was answered incorrectly.

* Adding another attribute to the questions, labeling them as easy, medium, or difficult.

* On installation, the app would ask a preliminary set of questions to measure the ‘sober’ math abilities of the user. And then store the prefered list of questions to easy, medium or difficult.

* Add the option to use the user’s location. Then the app could “remember” locations where it has been turned on before.

* Add the option of using the app with an alarm, so that the user can’t silence an alarm without unlocking the screen through the app.

## Relevant Links

[my wireframe](WireFrame.pdf)

[User Stories](UserStories.md)

[ERD](DrinkingProbsERD.pdf)

[DDL](drinking_problems.ddl)

[Javadoc](docs/api/index.html)

[Licenses](Licenses.md)

[Instructions for building Drinking Problems](BuildInstructions.md)

[Instructions for using Drinking Problems](UserInstructions.md)

 


