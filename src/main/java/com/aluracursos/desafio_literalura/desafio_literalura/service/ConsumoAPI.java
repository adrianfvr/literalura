package com.aluracursos.desafio_literalura.desafio_literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String obtenerDatos(String urlBase, String busqueda) {
        String urlCompleta = urlBase + "?search=" + URLEncoder.encode(busqueda, StandardCharsets.UTF_8);
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS) // esto resuelve el 301
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlCompleta))
                .header("Accept", "application/json")
                .header("User-Agent", "Java-HttpClient") // a veces requerido
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Error HTTP: " + response.statusCode());
            }

            String json = response.body();
            System.out.println("JSON recibido: " + json); // Para verificarlo tú mismo

            return json;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al consumir la API: " + e.getMessage(), e);
        }
    }
}