package se.yrgo.service;

import se.yrgo.domain.Customer;

import java.util.List;

public interface CustomerService {
    Customer registerCustomer(Customer customer);

    Customer findById(Long id);

    List<Customer> findAll();

    void delete(Long id);

    public void save(Customer customer);
}
