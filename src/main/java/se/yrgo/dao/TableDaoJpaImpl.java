package se.yrgo.dao;

import se.yrgo.domain.Tables;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class TableDaoJpaImpl implements TableDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Tables table) {

        em.persist(table);
    }

    @Override
    public void destroyTable(Tables oldTable) {

        Tables tableToRemove = em.find(Tables.class, oldTable.getId());

        if (tableToRemove != null) {
            em.remove(tableToRemove);
        }

    }

    @Override
    public void update(Tables table) {

        em.merge(table);
    }

    @Override
    public List<Tables> findAvailableTables() {

        String available = "Select dt from Tables dt where dt.isAvailable = true";

        return em.createQuery(available, Tables.class).getResultList();
    }


    @Override
    public Tables findById(Long id) {


        return em.find(Tables.class, id);
    }

    @Override
    public List<Tables> findAll() {
        return em.createNamedQuery("Table.findAllTables", Tables.class).getResultList();
    }
}
