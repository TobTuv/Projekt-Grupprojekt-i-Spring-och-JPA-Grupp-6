package dao;

import domain.Table;

import java.util.List;

public interface TableDAO {

    public void create(Table table);

    public void destroyTable(Table oldTable);

    public void update(Table table);

    List<Table> findAvailableTables();

    Table findById(Long id);

    List<Table> findAll();


}
