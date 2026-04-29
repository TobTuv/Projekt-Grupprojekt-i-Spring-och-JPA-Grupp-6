package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Table {

    @Id
    @GeneratedValue
    private long id;
    private int tableNumber;
    private int capacity;

    @ManyToOne
    private Reservation booking;

    public Table(long id, int tableNumber, int capacity, Reservation booking) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.booking = booking;
    }
}
