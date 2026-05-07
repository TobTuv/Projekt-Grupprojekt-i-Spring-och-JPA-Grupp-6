package service;

import domain.Table;

import java.util.List;

public interface TableService {

    public void create(Table table);

    public void destroyTable(Table oldTable);

    public void update(Table table);


    List<Table> findTablesWithoutReservation();

    Table findById(Long id);

    List<Table> findAll();
}
