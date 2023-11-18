package org.example.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimerTask;


public class MyTimerTask extends TimerTask {

    public void run() {
        try {
            executeTask();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeTask() throws SQLException {
        System.out.println("Executing task...");

        WeatherStore weatherStore = new WeatherStore();
        try {
            weatherStore.storeWeatherData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}