package dao;

import domain.DiningTable;

import java.util.List;

public interface ReservationDAO {

    public List<DiningTable> getAllTables();

    public void getTableByIsbn(String id);








}
