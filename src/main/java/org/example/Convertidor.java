package org.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Convertidor {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String API_KEY = "5d4d276777c7a921f2ed3535";

    // Método para obtener la tasa de conversión desde la API
    public double obtenerTasaConversion(String base, String objetivo) throws Exception {
        String urlString = API_URL + API_KEY + "/latest/" + base;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Leer la respuesta de la API
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder respuesta = new StringBuilder();
        String linea;

        while ((linea = reader.readLine()) != null) {
            respuesta.append(linea);
        }
        reader.close();

        // Parsear el JSON y obtener la tasa de conversión
        JsonObject jsonResponse = JsonParser.parseString(respuesta.toString()).getAsJsonObject();
        if (!jsonResponse.get("result").getAsString().equals("success")) {
            throw new Exception("No se pudo obtener la tasa de cambio");
        }

        JsonObject tasasConversion = jsonResponse.getAsJsonObject("conversion_rates");
        return tasasConversion.get(objetivo).getAsDouble();
    }

    // Método para realizar la conversión
    public double convertir(double monto, double tasa) {
        return monto * tasa;
    }
}

