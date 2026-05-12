package se.yrgo.dao;

import java.util.List;

import se.yrgo.domain.Customer;
import se.yrgo.domain.Reservation;

public interface CustomerDAO {

     public void save(Customer customer);


     public Customer findById(long id);


     public List<Customer> findAll();


      public void delete(long id);

      public List<Reservation> findBookingsByCustomerId(Long customerid);





    
}
