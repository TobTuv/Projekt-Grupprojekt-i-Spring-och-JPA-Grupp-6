package data;

import domain.Table;

import java.util.List;

public interface Reservation {

    public List<Table> getAllTables();

    public void getTableByIsbn(String id);








}
