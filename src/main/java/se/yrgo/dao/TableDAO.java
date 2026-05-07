package se.yrgo.dao;

import se.yrgo.domain.Tables;

import java.util.List;

public interface TableDAO {

    public void create(Tables table);

    public void destroyTable(Tables oldTable);

    public void update(Tables table);

    List<Tables> findAvailableTables();

    Tables findById(Long id);

    List<Tables> findAll();


}
