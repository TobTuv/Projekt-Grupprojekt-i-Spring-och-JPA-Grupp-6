package se.yrgo.dao;

import se.yrgo.domain.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.yrgo.domain.Reservation;

import java.util.List;

@Repository
public class CustomerDAOImplementation implements CustomerDAO {

    @PersistenceContext
    private EntityManager em;

    public void save(Customer customer) {
        em.persist(customer);
    }

    public Customer findById(long id) {
        return em.find(Customer.class, id);
    }

    public List<Customer> findAll() {
        return em.createQuery("SELECT c FROM Customer AS c", Customer.class)
                .getResultList();
    }


    public List<Reservation> findBookingsByCustomerId(Long customerId) {
        return em.createNamedQuery("Customer.findBookings", Reservation.class)
                .setParameter("id", customerId)
                .getResultList();
    }

    public void delete(long id) {
        Customer c = findById(id);
        if (c != null) {
            em.remove(c);
        }
    }
}
