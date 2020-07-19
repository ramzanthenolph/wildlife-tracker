# WILDLIFE TRACKER  Application
Wildlife Tracker
#### By **Abubakar Ramadhan**
## Description
This is a Wildlife application.
* This application is intended to track both all animals and endangered animals.
Its main purpose is record all type of animals including ranger who spotted the animal.
The rangers can submit as many times as they want depending on how many they have sighted.
* Rangers are able to be sorted depending on where they sighted the animal.


#Live Link
   

## Setup/Installation Requirements

Get to the link below and test the app. Database creation will is as below;
**PSQL**
* CREATE DATABASE wildlife_tracker;
*CREATE TABLE rangers(id serial PRIMARY KEY, name varchar);
*CREATE TABLE animals(id serial PRIMARY KEY, name varchar, health varchar, age varchar, type varchar);
*CREATE TABLE sightings(id serial PRIMARY KEY, animalname varchar, location varchar, timestamp timestamp, rangerid int);
*CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;
## Known Bugs
No bugs
## Technologies Used
**used;**
* Java
* CSS
* Boostrap
* Postgresql
* Spark
## Support and contact details
Comment if any issues arise
Copyright (c) {2020} **Abubakar Ramadhan**