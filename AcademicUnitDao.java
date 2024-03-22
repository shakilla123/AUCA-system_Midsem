package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import Model.AcademicUnit;
import java.util.List;

public class AcademicUnitDao {

    private EntityManager entityManager;

    public AcademicUnitDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(AcademicUnit academicUnit) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(academicUnit);
            transaction.commit();
        } catch (Exception e) {
            handleTransactionException(transaction, e);
        }
    }

    public AcademicUnit findById(int academicId) {
        return entityManager.find(AcademicUnit.class, academicId);
    }

    public List<AcademicUnit> findAll() {
        TypedQuery<AcademicUnit> query = entityManager.createQuery("SELECT a FROM AcademicUnit a", AcademicUnit.class);
        return query.getResultList();
    }

    public void update(AcademicUnit academicUnit) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(academicUnit);
            transaction.commit();
        } catch (Exception e) {
            handleTransactionException(transaction, e);
        }
    }

    public void delete(int academicId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            AcademicUnit academicUnit = entityManager.find(AcademicUnit.class, academicId);
            if (academicUnit != null) {
                entityManager.remove(academicUnit);
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
