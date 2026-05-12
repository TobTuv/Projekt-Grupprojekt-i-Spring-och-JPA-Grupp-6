package se.yrgo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dao.CustomerDAO;
import se.yrgo.domain.Customer;
import se.yrgo.domain.Reservation;

import java.util.List;

@Service
@Transactional
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

    @Override
    public List<Reservation> findBookingsByCustomerId(Long customerId) {
        return
                customerDAO.findBookingsByCustomerId(customerId);
    }


}
