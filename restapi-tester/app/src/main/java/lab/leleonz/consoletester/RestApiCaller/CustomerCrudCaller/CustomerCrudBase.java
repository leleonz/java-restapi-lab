package lab.leleonz.consoletester.RestApiCaller.CustomerCrudCaller;

import java.io.IOException;
import java.util.List;

import lab.leleonz.consoletester.RestApiCaller.CustomerCrudCaller.Models.CreateUpdateCustomerRequest;
import lab.leleonz.consoletester.RestApiCaller.CustomerCrudCaller.Models.Customer;

public interface CustomerCrudBase {
    Customer Create(CreateUpdateCustomerRequest requestObj) throws IOException, InterruptedException;
    List<Customer> ReadAll() throws IOException, InterruptedException;
    Customer Read(Long id) throws IOException, InterruptedException;
    Customer Update(Long id, CreateUpdateCustomerRequest requestObj) throws IOException, InterruptedException;
    Boolean Delete(Long id) throws IOException, InterruptedException;
}
