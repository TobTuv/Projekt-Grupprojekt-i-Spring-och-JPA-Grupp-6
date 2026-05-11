package se.yrgo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.yrgo.domain.Reservation;
import se.yrgo.exception.ReservationNotFoundException;

import java.time.LocalDateTime;
import java.util.List;


@Repository
@Transactional
public class ReservationDaoJpaImpl implements ReservationDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Reservation reservation) {
        em.persist(reservation);
    }


    @Override
    public Reservation findById(long id) {
        Reservation r = em.find(Reservation.class, id);
        if (r == null) {
            throw new ReservationNotFoundException("Reservation not found");
        }
        return r;
    }

    @Override
    public List<Reservation> findAll() {

        return em.createQuery("SELECT r FROM Reservation r", Reservation.class)
                .getResultList();
    }


    //Usage of NamedQuery
    @Override
    public List<Reservation> findByCustomerId(long customerId) {

        return  em.createNamedQuery("Reservation.findByCustomer", Reservation.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    @Override
    public List<Reservation> findByStatus(String status) {

        return em.createNamedQuery("Reservation.findByStatus", Reservation.class)
                .setParameter("status", status)
                .getResultList();
    }

    // JPQL-query
    @Override
    public List<Reservation> findByDateRange(LocalDateTime from, LocalDateTime to) {

        return em.createQuery(
                "SELECT r FROM Reservation r WHERE r.dateTime BETWEEN :from AND :to",
                Reservation.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

    @Override
    public void update(Reservation reservation) {
        em.merge(reservation);
    }

    @Override
    public void cancel(long id) {
        //throws exception if non exists
        Reservation r = findById(id);
        //no merge, managed by transaction
        r.setStatus("Cancelled");
    }

    @Override
    public void delete(long id) {
    Reservation r = findById(id);
    em.remove(r);
    }
}
