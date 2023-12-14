package lab.leleonz.consoletester.RestApiCaller.CustomerCrudCaller;

import java.util.List;

import lab.leleonz.consoletester.RestApiCaller.CustomerCrudCaller.Models.CreateUpdateCustomerRequest;
import lab.leleonz.consoletester.RestApiCaller.CustomerCrudCaller.Models.Customer;

public interface CustomerCrudBase {
    Customer Create(CreateUpdateCustomerRequest requestObj);
    List<Customer> ReadAll();
    Customer Read(Long id);
    Customer Update(Long id, CreateUpdateCustomerRequest requestObj);
    Boolean Delete(Long id);
}
