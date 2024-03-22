package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import Model.Semester;
import java.util.List;

public class SemesterDao {

    private EntityManager entityManager;

    public SemesterDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Semester semester) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(semester);
            transaction.commit();
        } catch (Exception e) {
            handleTransactionException(transaction, e);
        }
    }

    public Semester findById(int semesterId) {
        try {
            return entityManager.find(Semester.class, semesterId);
        } catch (NoResultException e) {
            // Log or handle the case where no result is found
            return null;
        }
    }

    public List<Semester> findAll() {
        TypedQuery<Semester> query = entityManager.createQuery("SELECT s FROM Semester s", Semester.class);
        return query.getResultList();
    }

    public void update(Semester semester) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(semester);
            transaction.commit();
        } catch (Exception e) {
            handleTransactionException(transaction, e);
        }
    }

    public void delete(int semesterId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Semester semester = entityManager.find(Semester.class, semesterId);
            if (semester != null) {
                entityManager.remove(semester);
            }
            transaction.commit();
        } catch (Exception e) {
            handleTransactionException(transaction, e);
        }
    }

    private void handleTransactionException(EntityTransaction transaction, Exception e) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        // Log the exception or handle it appropriately
        e.printStackTrace();
    }
}
