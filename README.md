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
This package comprises a collection of Java classesresponsible for executing the control and logic of the application. These classes are specifically crafted to orchestrate the interaction among the data model, business logic, and user interface.

- **Controller**: is responsible for the execution of the application.
- **DatabaseManager**: contains the method ```getConnection```, which is in charge of establishing the connection with the database, and the method ```createDatabase()```, which handles the creation and management of the SQLite database.

   ⚠️**You must insert your "resources" path into the variable ```path``` so you can create your database**⚠️
  <p align="center">
  <img width="426" alt="image" src="https://github.com/raquelaq/OpenWeatherApp/assets/117348659/cefa4cf5-1a30-44a7-a3f5-06e39aeacc8d">
  </p>
  
- **Main**: houses the principal method ```main()``` that initiates the application. It creates an instance of ```Controller``` and calls the method ```execute()``` to start running the code
- **MyTimerTask**: extends de ```TimerTask``` class and executes periodic tasks. Each time it runs, it stores weather data in the database. With this class, the code is set to run every six hours to obtain new predictions.
- **ResponseBuilder**: contructs a response from the API requests. Uses an ```HttpURLConnection``` to make the request and obtain the response.
- **OpenWeatherMapProvider**: it communicates with the OpenWeatherMap API to retrieve weather data. The ```buildWeather()``` method consctructs Weather objects from the API response and to create a coordinates map associated with the city names of the islands.

   ⚠️**You must insert your "API KEY" into the variable ```API_KEY``` to do de API call**⚠️
<p align="center">
<img width="352" alt="image" src="https://github.com/raquelaq/OpenWeatherApp/assets/117348659/5f98782c-17b9-4d77-8175-f3b8a98f6f4f">
</p>
- **SqliteWeatherStore**: manages the creation of the tables in the database using SQLite sentences. It includes methods for inserting, updating, and selecting weather data in the tables.  It establishes connections with the database through the **DatabaseManager** class.
- **WeatherSelect**: it selects weather data from the SQLite database. t establishes connections with the database through the **DatabaseManager** class and tge **SqliteWeatherStore** to do the selection.
- **WeatherStore**: coordinates de retrieval of weather data from the API and its storage in the SQLite database. It uses the clases **OpenWeatherMapProvider**, **DatabaseManager**, and **SqliteWeatherStore**  for this purpose
- **WebService**: uses the ```Spark``` library to establish a web service that returns weather data in JSON format. It responds to the HTTP requests to retireve weather data from an specific island. It returns a list of the avaliable islands in case someone requires an island that is not included in the database
- **GUI**: it represents the graphical user interface of the application. You can choose which island you want to retrieve information from, and by pressing the "Show Info" button, you will have all the information displayed on the screen.

<p align="center">
<img width="440" alt="image" src="https://github.com/raquelaq/OpenWeatherApp/assets/117348659/d9444e7a-109c-483b-ba36-9461e2bc4725">
</p>

