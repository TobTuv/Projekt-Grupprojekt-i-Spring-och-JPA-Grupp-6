package se.yrgo.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.yrgo.dao.TableDAO;
import se.yrgo.domain.Tables;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class TableServiceImpl implements TableService {

    private TableDAO tableDAO;

    @Autowired
    public TableServiceImpl(TableDAO tableDAO) {
        this.tableDAO = tableDAO;
    }

    @Override
    public void create(Tables table) {

        tableDAO.create(table);
    }

    @Override
    public void destroyTable(Tables oldTable) {

        tableDAO.destroyTable(oldTable);
    }

    @Override
    public void update(Tables table) {


    }

    @Override
    public List<Tables> findAvailableTables() {
        return tableDAO.findAvailableTables();
    }


    @Override
    public Tables findById(Long id) {
        return tableDAO.findById(id);
    }

    @Override
    public List<Tables> findAll() {
        return tableDAO.findAll();
    }
}
