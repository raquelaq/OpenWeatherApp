package org.example.control;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Timer;

public class Controller {
    public void execute() throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.createDatabase("database.db");
        Connection connection = databaseManager.getConnection("database.db");
        MyTimerTask myTimerTask = new MyTimerTask();
        Timer timer = new Timer();
        timer.schedule(myTimerTask, 0, 6 * 60 * 60 * 1000);
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.setVisible(true);
        });
        new WebService().start();
    }
}
