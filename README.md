# Todoc

This repository contains an application for project 5 of the path **Grande École du Numérique**. 
This application is the property of the startup Lamzone. It helps collaborators book meetingrooms quickly. 

[Visual of the application are available here](https://drive.google.com/drive/folders/19gkOm85KPoIhn1XBeZA01RXF0T2S12GA?usp=sharing)

## Identification of the project and the mission

Name and nature: 
As part of the company's growth, the management of meetings becomes a key issue for employees. The company therefore wishes to create a meeting management application, Maréu, which can be used internally by all, collaborators.

Origin: 
The time taken to find a room is getting longer and longer.

Challenge: 
Save time for all employees.

Tech stack used:
* Java
* ROOM
* DAGGER2/HILT
* GIT

## Project setup

This is an Android application, it is coded in Java and runs on SDK version 31. To run he project, clone this repository and open it on Android Studio. 

## Project architecture

MVVM is the architecture pattern used for this project. Repositories are implemented and dependency injection is done with Dagger2 / Hilt.

## Version Control

We loosely use the "Git flow" approach: master is the release branch - it should always be releasable, and only merged into when we have tested and verified that everything works and is good to go. 

Daily development is done in the development branch. Features, bugfixes and other tasks are done as branches off of develop, then merged back into develop directly or via pull requests.

Keep commit clear and self-explanatory. Clean messy branches before merge. 

## Testing

This application is using Hilt, most of the test are in the Android Test folder where we test the behaviour of the complete app. 

## How to improve this project

* Update in Kotlin
* Add a backend for collaboration

You can either clone the repository and freely reuse it or you can make a pull request. It will only be accepted once I validate my retraining. 
