package se.yrgo.dao;

import se.yrgo.domain.Reservation;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationDAO {
    void create(Reservation reservation);
    Reservation findById(long id);

    List<Reservation> findAll();
    List<Reservation> findByCustomerId(long customerId);
    List<Reservation> findByStatus(String status);
    List<Reservation> findByDateRange(LocalDateTime from, LocalDateTime to);

    void update(Reservation reservation);
    void cancel(long id);
    void delete(long id);
}