 package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import Model.StudentRegistration;
import java.util.List;

public class StudentRegistrationDao {

    private EntityManager entityManager;

    public StudentRegistrationDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(StudentRegistration studentRegistration) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(studentRegistration);
            transaction.commit();
        } catch (Exception e) {
            handleTransactionException(transaction, e);
        }
    }

    public StudentRegistration findById(int registrationId) {
        return entityManager.find(StudentRegistration.class, registrationId);
    }

    public List<StudentRegistration> findAll() {
        TypedQuery<StudentRegistration> query = entityManager.createQuery("SELECT sr FROM StudentRegistration sr", StudentRegistration.class);
        return query.getResultList();
    }

    public void update(StudentRegistration studentRegistration) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(studentRegistration);
            transaction.commit();
        } catch (Exception e) {
            handleTransactionException(transaction, e);
        }
    }

    public void delete(int registrationId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            StudentRegistration studentRegistration = entityManager.find(StudentRegistration.class, registrationId);
            if (studentRegistration != null) {
                entityManager.remove(studentRegistration);
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