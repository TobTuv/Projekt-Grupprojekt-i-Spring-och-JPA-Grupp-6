package se.yrgo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.persistence.*;


@Entity
@NamedQueries({
        @NamedQuery(
                name = "Reservation.findByStatus",
                query = "SELECT r FROM Reservation r WHERE r.status = :status"
        ),
        @NamedQuery(
                name = "Reservation.findByCustomer",
                query = "SELECT r FROM Reservation r WHERE r.customer.id = :customerId"
        )
})

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    private int numberOfGuests;
    private String notes;

    //"Confirmed", "pending", "cancelled"
    private String status;

    @ManyToOne
    @JoinColumn(name ="customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name ="table_id", nullable = false)
    private Tables table;


    public Reservation(){}

    public Reservation(LocalDateTime dateTime, int numberOfGuests,
                       String status, Customer customer, Tables table, String notes) {
        this.dateTime = dateTime;
        this.numberOfGuests = numberOfGuests;
        this.customer = customer;
        this.table = table;
        this.status = "CONFIRMED";
        this.notes = notes;
    }

    // Getters & Setters
    public long getId() { return id; }
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public int getNumberOfGuests() { return numberOfGuests; }
    public void setNumberOfGuests(int numberOfGuests) { this.numberOfGuests = numberOfGuests; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public Tables getTable() { return table; }
    public void setTable(Tables table) { this.table = table; }



    @Override
    public String toString() {
        return String.format("""
                                 Bokning #%d
                                 Datum/tid: %s
                                 Gäster:   %d
                                 Status:   %s
                                 Kund:     %s %s
                                 Bord:     %d
                                 Notering: %s""",
                id, dateTime, numberOfGuests, status,
                customer.getFirstName(), customer.getLastName(),
                table.getTableNumber(), notes);
    }
}