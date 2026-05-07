package se.yrgo.dao;

import se.yrgo.domain.Tables;

import java.util.List;

public interface ReservationDAO {

    public List<Tables> getAllTables();

    public void getTableByIsbn(String id);








}
