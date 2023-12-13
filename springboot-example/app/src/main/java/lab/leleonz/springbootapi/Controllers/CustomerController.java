package lab.leleonz.springbootapi.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

import lab.leleonz.springbootapi.Models.Customer;
import lab.leleonz.springbootapi.Models.Requests.NewCustomerDetail;
import lab.leleonz.springbootapi.Repositories.CustomerRepository;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> findCustomer(@PathVariable Long id) {
        return customerRepository.findById(id)
            .map(customer -> {
                return new ResponseEntity<>(customer, HttpStatus.OK);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/customers")
    public Customer registerCustomer(@RequestBody NewCustomerDetail customerDetail) {
        return customerRepository.save(
            new Customer(customerDetail.firstName(), customerDetail.lastName(), customerDetail.email()));
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> modifyCustomerDetails(@PathVariable Long id, @RequestBody NewCustomerDetail customerDetail) {
        return customerRepository.findById(id)
            .map(customer -> {
                customer.setFirstName(customerDetail.firstName());
                customer.setLastName(customerDetail.lastName());
                customer.setEmail(customerDetail.email());

                return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.OK);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deregisterCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        
        return ResponseEntity.noContent().build();
    }

}
