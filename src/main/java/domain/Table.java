package domain;

public class Table {

    private long id;
    private int tableNumber;
    private int capacity;
    private String location;

    public Table(long id, int tableNumber, int capacity, String location) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.location = location;
    }


}
