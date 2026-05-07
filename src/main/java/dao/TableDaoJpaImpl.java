package dao;

import domain.Table;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class TableDaoJpaImpl implements TableDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Table table) {

        em.persist(table);
    }

    @Override
    public void destroyTable(Table oldTable) {

        Table tableToRemove = em.find(Table.class, oldTable.getId());

        if (tableToRemove != null) {
            em.remove(tableToRemove);
        }

    }

    @Override
    public void update(Table table) {

        em.merge(table);
    }

    @Override
    public List<Table> findAvailableTables() {

        String available = "Select dt from Table dt where dt.isAvailable = true";

        return em.createQuery(available, Table.class).getResultList();
    }


    @Override
    public Table findById(Long id) {


        return em.find(Table.class, id);
    }

    @Override
    public List<Table> findAll() {
        return em.createNamedQuery("DiningTable.findAllTables", Table.class).getResultList();
    }
}
