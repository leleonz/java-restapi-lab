package lab.leleonz.consoletester.RestApiCaller.CustomerCrudCaller.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Customer (Long id, String firstName, String lastName, String email) {}
