package se.yrgo.service;

import se.yrgo.domain.Customer;

import java.util.List;

public interface CustomerService {

    public Customer registerCustomer();

    public Customer findById(Long id);

    public List<Customer> findAll();

    public void delete(Long id);
}


