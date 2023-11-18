<h1 align="center"> OpenWeatherMap Data Capturing </h1>
<p align="left">
   <img src="https://img.shields.io/badge/STATUS-DONE-green">
   <img src="https://img.shields.io/badge/Released-November%202023-yellow">
   </p>

## General Information
This project has been created by Raquel Almeida Quesada for "Development of Applications for Data Science" (DACD), a second-year subject offered in the Data Science and Engineering Degree at the University of Las Palmas de Gran Canaria (ULPGC), School of Computer Engineering (EII).

## The Proyect
The main objective of this program is to consume data from a REST API that provides public and free data. The API used in this case is the "Call 5 day / 3 hour forecast data" API from OpenWeatherMap.

With this API, we can search a weather forecast for 5 days with data every 3 hours, based on the geographic coordinates of our choice.

I worked specifically with coordinates of the Canary Island, using one set of coordinates (latitude, longitude) per island.

This application queries the API every 6 hours to get the weather forecast for each of the 8 islands, retrieving the forecast prediction for the next 5 days at 12 pm each day. These data are stored in an SQL database that includes a table for each island, having a new entry created for each day

## Structure
The code is structured into two main packages: Model and Control, each encompassing their respective classes and functionalities.
#### Model Package
This package serves as a repository for classes that define the data structure. These classes encapsulate the logic and data embodying crucial concepts within the application domain.

- **Weather Class**:  this class represents the variables obtained from the API, getting only those pertinent to the program's objectives, such as temperature, humidity, wind speed, etc. It includes constructors and getter methods.
  - **Método toString**:
  - **Método buildJson**:
- **Coordinates Class**: represents geographical coordinates, including the name of the place associated with the coordinates, and the place's latitude and longitude

#### Control Package

