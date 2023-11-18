package org.example.control;

import org.example.model.Weather;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI extends JFrame implements ActionListener {
    private JButton b1;
    private JTextArea textArea;
    private JComboBox<String> tableComboBox;

    public GUI() {
        b1 = new JButton("Show Info");
        b1.addActionListener(this);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        List<String> tableNames = getTableNames();
        String[] tableArray = tableNames.toArray(new String[0]);

        tableComboBox = new JComboBox<>(tableArray);

        JPanel panel = new JPanel(new GridLayout(1, 1));
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.add(b1);
        buttonPanel.add(tableComboBox);
        panel.add(buttonPanel);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel, BorderLayout.NORTH);
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);

        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String selectedTable = (String) tableComboBox.getSelectedItem();
            showInfo(selectedTable);
        }
    }

    private void showInfo(String tableName) {
        WeatherSelect weatherSelect = new WeatherSelect();
        List<Weather> weatherList = weatherSelect.selectWeatherData(tableName);
        if (weatherList != null && !weatherList.isEmpty()) {
            StringBuilder info = new StringBuilder();
            for (Weather weather : weatherList) {
                info.append(weather.toString()).append("\n");
            }
            textArea.setText(info.toString());
        } else {
            textArea.setText("No hay datos disponibles para la tabla seleccionada");
        }
    }

    private List<String> getTableNames() {
        return List.of("Gran Canaria", "Tenerife", "Fuerteventura", "Lanzarote", "La Palma", "El Hierro", "La Gomera", "La Graciosa");
    }

}
