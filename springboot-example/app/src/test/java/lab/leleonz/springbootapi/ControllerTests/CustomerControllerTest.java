package lab.leleonz.springbootapi.ControllerTests;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import lab.leleonz.springbootapi.Controllers.CustomerController;
import lab.leleonz.springbootapi.Models.Customer;
import lab.leleonz.springbootapi.Models.Requests.NewCustomerDetail;
import lab.leleonz.springbootapi.Repositories.CustomerRepository;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    public void givenListOfCustomers_whenGetAllCustomers_thenReturnSavedCustomers() throws Exception {
        List<Customer> cutomerList = new ArrayList<>();
        cutomerList.add(new Customer("John", "Doe", "johndoe@domain.com"));
        cutomerList.add(new Customer("John", "Smith", "johnsmith@domain.com"));
        cutomerList.add(new Customer("Jane", "Doe", "janedoe@domain.com"));

        when(customerRepository.findAll()).thenReturn(cutomerList);

        this.mockMvc.perform(get("/customers"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()", is(cutomerList.size())));
    }

    @Test
    public void givenCustomerId_whenFindCustomer_thenReturnJsonCustomer() throws Exception{
        Customer customer = new Customer("John", "Doe", "johndoe@domain.com");
        when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(customer));

        this.mockMvc.perform(get("/customers/{id}", Mockito.anyLong()))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName", is(customer.getFirstName())))
            .andExpect(jsonPath("$.lastName", is(customer.getLastName())))
            .andExpect(jsonPath("$.email", is(customer.getEmail())));
    }

    @Test
    public void givenInvalidCustomerId_whenFindCustomer_thenReturnNotFound() throws Exception{
        when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/customers/{id}", Mockito.anyLong()))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    public void givenNewCustomerDetail_whenRegisterCustomer_thenReturnNewCustomerJson() throws Exception{
        NewCustomerDetail customerDetail = new NewCustomerDetail("John", "Doe", "johndoe@domain.com");

        when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer((invocation) -> invocation.getArguments()[0]);

        this.mockMvc.perform(
                post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDetail)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName", is(customerDetail.firstName())))
            .andExpect(jsonPath("$.lastName", is(customerDetail.lastName())))
            .andExpect(jsonPath("$.email", is(customerDetail.email())));

        verify(customerRepository, times(1)).save(Mockito.any(Customer.class));
    }

    @Test
    public void givenEmptyCustomerFirstName_whenRegisterCustomer_thenReturnBadRequest() throws Exception{
        NewCustomerDetail customerDetail = new NewCustomerDetail("", "Doe", "johndoe@domain.com");

        when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer((invocation) -> invocation.getArguments()[0]);

        this.mockMvc.perform(
                post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDetail)))
            .andDo(print())
            .andExpect(status().isBadRequest());

        verify(customerRepository, never()).save(Mockito.any(Customer.class));
    }

    @Test
    public void givenEmptyCustomerLastName_whenRegisterCustomer_thenReturnBadRequest() throws Exception{
        NewCustomerDetail customerDetail = new NewCustomerDetail("John", "", "johndoe@domain.com");

        when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer((invocation) -> invocation.getArguments()[0]);

        this.mockMvc.perform(
                post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDetail)))
            .andDo(print())
            .andExpect(status().isBadRequest());

        verify(customerRepository, never()).save(Mockito.any(Customer.class));
    }

    @Test
    public void givenInvalidEmail_whenRegisterCustomer_thenReturnBadRequest() throws Exception{
        NewCustomerDetail customerDetail = new NewCustomerDetail("John", "Doe", "johndoe#domain.com");

        when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer((invocation) -> invocation.getArguments()[0]);

        this.mockMvc.perform(
                post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDetail)))
            .andDo(print())
            .andExpect(status().isBadRequest());

        verify(customerRepository, never()).save(Mockito.any(Customer.class));
    }

    @Test
    public void givenCustomerId_whenModifyCustomerDetails_thenReturnModifiedCustomerJson() throws Exception{
        Customer customer = new Customer("John", "Doe", "johndoe@domain.com");
        NewCustomerDetail customerDetail = new NewCustomerDetail("John", "Smith", "johnsmith@domain.com");

        when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer((invocation) -> invocation.getArguments()[0]);

        this.mockMvc.perform(
                put("/customers/{id}", Mockito.anyLong())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDetail)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName", is(customerDetail.firstName())))
            .andExpect(jsonPath("$.lastName", is(customerDetail.lastName())))
            .andExpect(jsonPath("$.email", is(customerDetail.email())));

        verify(customerRepository, times(1)).findById(Mockito.anyLong());
        verify(customerRepository, times(1)).save(Mockito.any(Customer.class));
    }

    @Test
    public void givenInvalidCustomerId_whenModifyCustomerDetails_thenReturnNotFound() throws Exception{
        NewCustomerDetail customerDetail = new NewCustomerDetail("John", "Doe", "johndoe@domain.com");

        when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        this.mockMvc.perform(
                put("/customers/{id}", Mockito.anyLong())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDetail)))
            .andDo(print())
            .andExpect(status().isNotFound());

        verify(customerRepository, times(1)).findById(Mockito.anyLong());
        verify(customerRepository, never()).save(Mockito.any(Customer.class));
    }

    @Test
    public void givenEmptyCustomerFirstName_whenModifyCustomerDetails_thenReturnBadRequest() throws Exception{
        Customer customer = new Customer("John", "Doe", "johndoe@domain.com");
        NewCustomerDetail customerDetail = new NewCustomerDetail("", "Doe", "johndoe@domain.com");

        when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(customer));

        this.mockMvc.perform(
                put("/customers/{id}", Mockito.anyLong())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDetail)))
            .andDo(print())
            .andExpect(status().isBadRequest());

        verify(customerRepository, times(1)).findById(Mockito.anyLong());
        verify(customerRepository, never()).save(Mockito.any(Customer.class));
    }

    @Test
    public void givenEmptyCustomerLastName_whenModifyCustomerDetails_thenReturnBadRequest() throws Exception{
        Customer customer = new Customer("John", "Doe", "johndoe@domain.com");
        NewCustomerDetail customerDetail = new NewCustomerDetail("John", "", "johndoe@domain.com");

        when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(customer));

        this.mockMvc.perform(
                put("/customers/{id}", Mockito.anyLong())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDetail)))
            .andDo(print())
            .andExpect(status().isBadRequest());

        verify(customerRepository, times(1)).findById(Mockito.anyLong());
        verify(customerRepository, never()).save(Mockito.any(Customer.class));
    }

    @Test
    public void givenInvalidEmail_whenModifyCustomerDetails_thenReturnBadRequest() throws Exception{
        Customer customer = new Customer("John", "Doe", "johndoe@domain.com");
        NewCustomerDetail customerDetail = new NewCustomerDetail("John", "Doe", "johndoe#domain.com");

        when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(customer));

        this.mockMvc.perform(
                put("/customers/{id}", Mockito.anyLong())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDetail)))
            .andDo(print())
            .andExpect(status().isBadRequest());

        verify(customerRepository, times(1)).findById(Mockito.anyLong());
        verify(customerRepository, never()).save(Mockito.any(Customer.class));
    }

    @Test
    public void givenCustomerId_whenDeregisterCustomer_thenReturnNoContent() throws Exception {
        doNothing().when(customerRepository).deleteById(Mockito.anyLong());

        this.mockMvc.perform(delete("/customers/{id}", Mockito.anyLong()))
            .andDo(print())
            .andExpect(status().isNoContent());

        verify(customerRepository, times(1)).deleteById(Mockito.anyLong());
    }
}
