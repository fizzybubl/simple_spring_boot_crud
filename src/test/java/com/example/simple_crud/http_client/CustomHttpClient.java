package com.example.simple_crud.http_client;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CustomHttpClient {

    private final HttpClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CustomHttpClient(HttpClient client) {
        this.client = client;
    }

    public HttpResponse<String> get(String url) throws IOException, InterruptedException {

        return client.send(
                HttpRequest.newBuilder(URI.create(url)).GET().build(),
                HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> post(String url, String body) throws IOException, InterruptedException {

        return client.send(
                HttpRequest.newBuilder(URI.create(url))
                        .POST(HttpRequest.BodyPublishers.ofString(body))
                        .build(),
                HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> post(String url, Object object) throws IOException, InterruptedException {
        return client.send(
                HttpRequest.newBuilder(URI.create(url))
                        .POST(HttpRequest.BodyPublishers
                                .ofString(objectMapper.writeValueAsString(object)))
                        .build(),
                HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> put(String url, Object object) throws IOException, InterruptedException {
        return client.send(
                HttpRequest.newBuilder(URI.create(url))
                        .PUT(HttpRequest.BodyPublishers
                                .ofString(objectMapper.writeValueAsString(object)))
                        .build(),
                HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> delete(String url) throws IOException, InterruptedException {
        return client.send(
                HttpRequest.newBuilder(URI.create(url))
                        .DELETE()
                        .build(),
                HttpResponse.BodyHandlers.ofString());
    }
}
