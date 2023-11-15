package org.example.control;

import java.sql.Connection;
import java.util.Timer;
import java.util.TimerTask;


public class MyTimerTask extends TimerTask {
    private final Connection connection;

    public MyTimerTask(Connection connection) {
        this.connection = connection;
    }

    public void run() {
        executeTask();
    }

    private void executeTask() {
        System.out.println("Executing task...");

        WeatherStore weatherStore = new WeatherStore(connection);
        try {
            weatherStore.storeWeatherData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}