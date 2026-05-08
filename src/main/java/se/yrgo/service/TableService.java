package se.yrgo.service;

import se.yrgo.domain.Tables;

import java.util.List;

public interface TableService {

    public void create(Tables table);

    public void destroyTable(Tables oldTable);

    public void update(Tables table);

    List<Tables> findTablesWithoutReservation();

    Tables findById(Long id);


    List<Tables> findAll();
}
