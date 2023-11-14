package org.example.control;

import org.example.model.Weather;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        Connection connection = DatabaseManager.getConnection("database.db");

        WeatherStore weatherStore = new WeatherStore(connection);

        MyTimerTask myTimerTask = new MyTimerTask(connection);


        Timer timer = new Timer();
        timer.schedule(myTimerTask, 0, 60000);

        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Cancela el Timer cuando hayas terminado
        timer.cancel();

        // Cierra la conexión a la base de datos
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //timer.schedule(myTimerTask, 0, 3 * 60 * 60 * 1000);

        /*List<Weather> weatherList = WeatherSelect.selectWeatherData("El Hierro");

        // Imprimir los resultados o realizar otras operaciones con ellos
       if (weatherList != null) {
            for (Weather weather : weatherList) {
                System.out.println(weather.toString());
            }
        }*/
    }
}
