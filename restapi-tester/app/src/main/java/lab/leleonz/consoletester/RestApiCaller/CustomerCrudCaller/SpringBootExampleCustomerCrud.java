package lab.leleonz.consoletester.RestApiCaller.CustomerCrudCaller;

import com.fasterxml.jackson.databind.ObjectMapper;

import lab.leleonz.consoletester.RestApiCaller.Interfaces.HttpClientBase;

public class SpringBootExampleCustomerCrud extends CustomerCrudBaseImpl {
    private static final String baseUrl = "http://localhost:8080";
    private static final String resourcePath = "customers";

    public SpringBootExampleCustomerCrud(HttpClientBase httpClientFactory, ObjectMapper objectMapper) {
        super(httpClientFactory, objectMapper, baseUrl, resourcePath);
    }
    
}
