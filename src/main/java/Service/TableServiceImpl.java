package Service;

import dao.TableDAO;
import domain.Table;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public class TableServiceImpl implements TableService {

    private TableDAO tableDAO;

    public TableServiceImpl(TableDAO tableDAO) {
        this.tableDAO = tableDAO;
    }

    @Override
    public void create(Table table) {

        tableDAO.create(table);
    }

    @Override
    public void destroyTable(Table oldTable) {

        tableDAO.destroyTable(oldTable);
    }

    @Override
    public void update(Table table) {


    }

    @Override
    public List<Table> findTablesWithoutReservation() {
        return tableDAO.findAvailableTables();
    }


    @Override
    public Table findById(Long id) {
        return tableDAO.findById(id);
    }

    @Override
    public List<Table> findAll() {
        return tableDAO.findAll();
    }
}
