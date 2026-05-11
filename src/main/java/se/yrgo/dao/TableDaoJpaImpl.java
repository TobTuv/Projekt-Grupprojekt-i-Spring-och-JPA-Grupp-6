package se.yrgo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import se.yrgo.exception.TableNotFoundException;

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
        } else {
            throw new TableNotFoundException("you missed, you are really bad at this huh?");
        }


    }

    @Override
    public void update(Tables table) {

        em.merge(table);
    }

    @Override
    public List<Tables> findAvailableTables() {


        return em.createNamedQuery("Table.findEmptyTables", Tables.class).getResultList();
    }


    @Override
    public Tables findById(Long id) {

        if (em.find(Tables.class, id) == null) {
            throw new TableNotFoundException("Table not found");
        }
        return em.find(Tables.class, id);


    }

    @Override
    public List<Tables> findAll() {
        return em.createNamedQuery("Table.findAllTables", Tables.class).getResultList();
    }
}
