package lab.leleonz.springbootapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.leleonz.springbootapi.Models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}