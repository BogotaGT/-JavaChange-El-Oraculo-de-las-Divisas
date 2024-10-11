package com.alura.conversor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ConvertidorMoneda {
    private static final String API_KEY = "e44746db680e1ec29e319e43";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";
    private Map<String, Double> tasasCambio;

    public ConvertidorMoneda() {
        actualizarTasasCambio();
    }

    // Método para actualizar las tasas de cambio desde la API
    private void actualizarTasasCambio() {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

            tasasCambio = new HashMap<>();
            tasasCambio.put("USD", 1.0);
            tasasCambio.put("EUR", rates.get("EUR").getAsDouble());
            tasasCambio.put("GBP", rates.get("GBP").getAsDouble());
            tasasCambio.put("JPY", rates.get("JPY").getAsDouble());
            tasasCambio.put("ARS", rates.get("ARS").getAsDouble());
            tasasCambio.put("BRL", rates.get("BRL").getAsDouble());
            tasasCambio.put("CLP", rates.get("CLP").getAsDouble());
            tasasCambio.put("COP", rates.get("COP").getAsDouble());
            tasasCambio.put("MXN", rates.get("MXN").getAsDouble());
            tasasCambio.put("CAD", rates.get("CAD").getAsDouble());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("La operación fue interrumpida: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al actualizar las tasas de cambio: " + e.getMessage());
        }
    }

    // Método para realizar la conversión de monedas
    public String convertir(String monedaOrigen, String monedaDestino, double cantidad) {
        if (!tasasCambio.containsKey(monedaOrigen) || !tasasCambio.containsKey(monedaDestino)) {
            throw new IllegalArgumentException("Moneda no soportada");
        }

        double tasaOrigen = tasasCambio.get(monedaOrigen);
        double tasaDestino = tasasCambio.get(monedaDestino);

        double resultado = (cantidad / tasaOrigen) * tasaDestino;

        return String.format("%.2f %s equivalen a %.2f %s",
                cantidad, obtenerNombreMoneda(monedaOrigen),
                resultado, obtenerNombreMoneda(monedaDestino));
    }

    // Método para obtener el nombre completo de la moneda
    private String obtenerNombreMoneda(String codigo) {
        Map<String, String> nombres = new HashMap<>();
        nombres.put("USD", "Dólares Estadounidenses");
        nombres.put("EUR", "Euros");
        nombres.put("GBP", "Libras Esterlinas");
        nombres.put("JPY", "Yenes Japoneses");
        nombres.put("ARS", "Pesos Argentinos");
        nombres.put("BRL", "Reales Brasileños");
        nombres.put("CLP", "Pesos Chilenos");
        nombres.put("COP", "Pesos Colombianos");
        nombres.put("MXN", "Pesos Mexicanos");
        nombres.put("CAD", "Dólares Canadienses");
        return nombres.getOrDefault(codigo, codigo);
    }
}