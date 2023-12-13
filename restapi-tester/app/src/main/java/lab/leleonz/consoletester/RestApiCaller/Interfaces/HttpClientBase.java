package lab.leleonz.consoletester.RestApiCaller.Interfaces;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public interface HttpClientBase {
    HttpClient createHttpClient();
    HttpRequest buildHttpGetRequest(String uri);
    HttpRequest buildHttpPostRequest(String uri, String jsonString);
    HttpRequest buildHttpPutRequest(String uri, String jsonString);
    HttpRequest buildHttpDeleteRequest(String uri);
}
