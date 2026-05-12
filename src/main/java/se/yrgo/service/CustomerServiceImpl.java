package se.yrgo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.yrgo.dao.CustomerDAO;
import se.yrgo.domain.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public Customer registerCustomer(Customer customer) {
        customerDAO.save(customer);
        return customer;
    }

    @Override
    public Customer findById(Long id) {
        return customerDAO.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    @Override
    public void delete(Long id) {
        customerDAO.delete(id);
    }

    @Override
    public void save(Customer customer) {
        customerDAO.save(customer);
    }
}
