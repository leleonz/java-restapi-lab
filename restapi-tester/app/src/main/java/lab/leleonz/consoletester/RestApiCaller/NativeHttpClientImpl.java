package lab.leleonz.consoletester.RestApiCaller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

import lab.leleonz.consoletester.RestApiCaller.Interfaces.HttpClientBase;

//this implementation uses HttpClient by Java
public class NativeHttpClientImpl implements HttpClientBase {

    public NativeHttpClientImpl() {}

    @Override
    public HttpClient createHttpClient() {
        return HttpClient
                .newBuilder()
                .connectTimeout(Duration.ofMillis(500))
                .build();
    }

    @Override
    public HttpRequest buildHttpGetRequest(String uri) {
        return HttpRequest
                .newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .GET()
                .build();
    }

    @Override
    public HttpRequest buildHttpPostRequest(String uri, String jsonString) {
        return HttpRequest
                .newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();
    }

    @Override
    public HttpRequest buildHttpPutRequest(String uri, String jsonString) {
        return HttpRequest
                .newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();
    }

    @Override
    public HttpRequest buildHttpDeleteRequest(String uri) {
        return HttpRequest
                .newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();
    }
    
}
