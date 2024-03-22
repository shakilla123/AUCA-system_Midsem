package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import Model.Teacher;
import java.util.List;

public class TeacherDao {

    private EntityManager entityManager;

    public TeacherDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Teacher teacher) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(teacher);
            transaction.commit();
        } catch (Exception e) {
            handleTransactionException(transaction, e);
        }
    }

    public Teacher findByTeacherId(int teacherId) {
        try {
            return entityManager.find(Teacher.class, teacherId);
        } catch (NoResultException e) {
            // Log or handle the case where no result is found
            return null;
        }
    }

    public List<Teacher> findAll() {
        TypedQuery<Teacher> query = entityManager.createQuery("SELECT t FROM Teacher t", Teacher.class);
        return query.getResultList();
    }

    public void update(Teacher teacher) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(teacher);
            transaction.commit();
        } catch (Exception e) {
            handleTransactionException(transaction, e);
        }
    }

    public void deleteByTeacherId(int teacherId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Teacher teacher = entityManager.find(Teacher.class, teacherId);
            if (teacher != null) {
                entityManager.remove(teacher);
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
