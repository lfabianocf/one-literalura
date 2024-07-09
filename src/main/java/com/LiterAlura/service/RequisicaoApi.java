package com.LiterAlura.service;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequisicaoApi {

        public String obterDados(String endereco) {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();

            HttpResponse<String> response = null;

            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());

            } catch (Exception ex) {

                System.out.println(ex.getMessage());
            }

            String json = response.body();

            return json;
        }

}
