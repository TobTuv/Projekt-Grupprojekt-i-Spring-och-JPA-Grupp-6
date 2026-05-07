package domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQueries({

        @NamedQuery(
                name = "DiningTable.findAllTables",
                query = "select dt from Table dt"
        ),
        @NamedQuery(
                name = "DiningTable.findEmptyTables",
                query = "select dt from Table dt where dt.reservations IS EMPTY"
        )
})
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int tableNumber;
    private int capacity;
    private boolean isAvailable;
    @OneToMany(mappedBy = "table", cascade = CascadeType.PERSIST)
    private List<Reservation> reservations;

    public Table(int tableNumber, int capacity, boolean isAvailable, List<Reservation> reservations) {
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.isAvailable = isAvailable;
        this.reservations = reservations;
    }

    public Table() {

    }

    public long getId() {
        return id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
