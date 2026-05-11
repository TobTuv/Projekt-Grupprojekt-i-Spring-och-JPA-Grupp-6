package se.yrgo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.dao.CustomerDAO;
import se.yrgo.dao.ReservationDAO;
import se.yrgo.dao.TableDAO;
import se.yrgo.domain.Customer;
import se.yrgo.domain.Reservation;
import se.yrgo.domain.Tables;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired private ReservationDAO reservationDAO;
    @Autowired private CustomerDAO customerDAO;
    @Autowired private TableDAO tableDAO;

    public Reservation bookTable(long customerId, long tableId,
                                 LocalDateTime dateTime, int guests, String notes) {
        Customer customer = customerDAO.findById(customerId);
        Tables table = tableDAO.findById(tableId);

        if (table.getCapacity() < guests) {
            throw new IllegalArgumentException(
                    "Bordet har bara plats för " + table.getCapacity() + " gäster.");
        }

        Reservation r = new Reservation(dateTime, guests, "CONFIRMED", customer, table, notes);
        reservationDAO.create(r);
        return r;
    }

    public void cancelReservation(long reservationId) {
        reservationDAO.cancel(reservationId);
    }

    public void updateReservation(Reservation reservation) {
        reservationDAO.update(reservation);
    }

    public Reservation getById(long id) {
        return reservationDAO.findById(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationDAO.findAll();
    }

    public List<Reservation> getByCustomer(long customerId) {
        return reservationDAO.findByCustomerId(customerId);
    }

    public List<Reservation> getConfirmed() {
        return reservationDAO.findByStatus("CONFIRMED");
    }

    public List<Reservation> getByDateRange(LocalDateTime from, LocalDateTime to) {
        return reservationDAO.findByDateRange(from, to);
    }
}