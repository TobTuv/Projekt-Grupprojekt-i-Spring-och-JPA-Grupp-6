package domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Table {

    @Id
    @GeneratedValue
    private long id;
    private int tableNumber;
    private int capacity;

    @OneToMany(mappedBy = "table")
    private List<Reservation> reservations;

    public Table(int tableNumber, int capacity) {
        this.tableNumber = tableNumber;
        this.capacity = capacity;
    }
}
