package org.example.control;

import static spark.Spark.*;

public class WebService {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hola caracola");
    }
}