package dao;

import domain.Table;

import java.util.List;

public interface ReservationDAO {

    public List<Table> getAllTables();

    public void getTableByIsbn(String id);








}
