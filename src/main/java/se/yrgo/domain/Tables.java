package se.yrgo.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQueries({

        @NamedQuery(name = "Table.findAllTables", query = "from Tables"),
        @NamedQuery(name = "Table.findEmptyTables", query = "from Tables dt where dt.reservations IS EMPTY AND dt.isAvailable = true "),

})
public class Tables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int tableNumber;
    private int capacity;
    private boolean isAvailable;
    @OneToMany(mappedBy = "table", cascade = CascadeType.PERSIST)
    private List<Reservation> reservations;

    public Tables(int tableNumber, int capacity, boolean isAvailable, List<Reservation> reservations) {
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.isAvailable = isAvailable;
        this.reservations = reservations;
    }

    public Tables(long id) {
        this.id = id;
    }

    public Tables() {

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

    @Override
    public String toString() {
        if (isAvailable){
            return "Bord " + tableNumber +
                    " | Antal platser: " + capacity +
                    " | Ledigt ";
        }
        return "Bord " + tableNumber +
                    " | Antal platser: " + capacity +
                    " | Bokat: ";
    }
    // public String toString() {
    // return "Tables{" +
    // "id=" + id +
    // ", tableNumber=" + tableNumber +
    // ", capacity=" + capacity +
    // ", isAvailable=" + isAvailable +
    // ", reservations=" +
    // '}';
    // }
}
