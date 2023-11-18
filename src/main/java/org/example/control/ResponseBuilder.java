package org.example.control;

import org.example.model.Coordinates;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ResponseBuilder {
    public String response(Coordinates coordinates, String query, String apiKey) throws IOException {
        String url = String.format(query, coordinates.getLatitude(), coordinates.getLongitude(), apiKey);
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        InputStream inputStream = connection.getInputStream();
        StringBuilder builder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                builder.append((char) c);
            }
        }
        return String.valueOf(builder);
    }
}
