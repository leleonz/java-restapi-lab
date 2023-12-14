package lab.leleonz.consoletester.RestApiCaller.CustomerCrudCaller;

import java.util.ArrayList;
import java.util.List;

import lab.leleonz.consoletester.RestApiCaller.CustomerCrudCaller.Models.CreateUpdateCustomerRequest;
import lab.leleonz.consoletester.RestApiCaller.CustomerCrudCaller.Models.Customer;

public class MockCustomerCrud implements CustomerCrudBase {

    private final List<Customer> customers;

    public MockCustomerCrud() {
        customers = new ArrayList<>();
        customers.add(new Customer(1L, "John", "Doe", "john_doe@tester.lab"));
        customers.add(new Customer(2L, "John", "Smith", "john_smith@tester.lab"));
        customers.add(new Customer(3L, "Jane", "Doe", "jane_doe@tester.lab"));
    }

    @Override
    public Customer Create(CreateUpdateCustomerRequest requestObj) {
        Customer newCustomer = new Customer(customers.size() + 1L, requestObj.firstName(), requestObj.lastName(), requestObj.email());
        
        return customers.add(newCustomer) ? newCustomer : null;
    }

    @Override
    public List<Customer> ReadAll() {
        return customers;
    }

    @Override
    public Customer Read(Long id) {
        return customers.stream()
        .filter(customer -> customer.id() == id)
        .findAny()
        .orElse(null);
    }

    @Override
    public Customer Update(Long id, CreateUpdateCustomerRequest requestObj) {
        Customer existingCustomer = this.Read(id);

        if (existingCustomer != null) customers.remove(existingCustomer);

        Customer newOrUpdated_Customer = new Customer(id, requestObj.firstName(), requestObj.lastName(), requestObj.email());
        
        return customers.add(newOrUpdated_Customer) ? newOrUpdated_Customer : null;
    }

    @Override
    public Boolean Delete(Long id) {
        Customer existingCustomer = this.Read(id);
        
        if(existingCustomer != null) return customers.remove(existingCustomer);
        
        return true;
    }
    
}
