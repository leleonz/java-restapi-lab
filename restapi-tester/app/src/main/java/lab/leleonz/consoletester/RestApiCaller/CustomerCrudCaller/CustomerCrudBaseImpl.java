package lab.leleonz.consoletester.RestApiCaller.CustomerCrudCaller;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import lab.leleonz.consoletester.RestApiCaller.CustomerCrudCaller.Models.CreateUpdateCustomerRequest;
import lab.leleonz.consoletester.RestApiCaller.CustomerCrudCaller.Models.Customer;
import lab.leleonz.consoletester.RestApiCaller.Interfaces.HttpClientBase;

public abstract class CustomerCrudBaseImpl implements CustomerCrudBase {

    protected final HttpClientBase httpClientFactory;
    protected final ObjectMapper objectMapper;
    private final String requestUrl;

    public CustomerCrudBaseImpl(HttpClientBase httpClientFactory, ObjectMapper objectMapper, String baseUrl, String resourcePath) {
        this.httpClientFactory = httpClientFactory;
        this.objectMapper = objectMapper;

        //todo: remove extra slashes from baseUrl and resourcePath
        this.requestUrl = baseUrl + "/" + resourcePath;
    }

    @Override
    public Customer Create(CreateUpdateCustomerRequest requestObj) throws IOException, InterruptedException {
        HttpClient client = this.httpClientFactory.createHttpClient();
        HttpRequest request = this.httpClientFactory.buildHttpPostRequest(requestUrl, objectMapper.writeValueAsString(requestObj));

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200) return null;
        return objectMapper.readValue(response.body(), Customer.class);

    }

    @Override
    public List<Customer> ReadAll() throws IOException, InterruptedException {
        HttpClient client = this.httpClientFactory.createHttpClient();
        HttpRequest request = this.httpClientFactory.buildHttpGetRequest(requestUrl);

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        CollectionType customerListType = TypeFactory.defaultInstance().constructCollectionType(List.class, Customer.class);

        if(response.statusCode() != 200) return Collections.emptyList();
        return objectMapper.readValue(response.body(), customerListType);
    }

    @Override
    public Customer Read(Long id) throws IOException, InterruptedException {
        HttpClient client = this.httpClientFactory.createHttpClient();
        HttpRequest request = this.httpClientFactory.buildHttpGetRequest(requestUrl + "/" + id);

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200) return null;
        return objectMapper.readValue(response.body(), Customer.class);
    }

    @Override
    public Customer Update(Long id, CreateUpdateCustomerRequest requestObj) throws IOException, InterruptedException {
        HttpClient client = this.httpClientFactory.createHttpClient();
        HttpRequest request = this.httpClientFactory.buildHttpPutRequest(requestUrl + "/" + id, objectMapper.writeValueAsString(requestObj));

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200) return null;
        return objectMapper.readValue(response.body(), Customer.class);
    }

    @Override
    public Boolean Delete(Long id) throws IOException, InterruptedException {
        HttpClient client = this.httpClientFactory.createHttpClient();
        HttpRequest request = this.httpClientFactory.buildHttpDeleteRequest(requestUrl + "/" + id);

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode() == 204;
    }
    
}
