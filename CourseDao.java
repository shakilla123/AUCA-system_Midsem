package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import Model.Course;
import java.util.List;

public class CourseDao {

    private EntityManager entityManager;

    public CourseDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Course course) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(course);
            transaction.commit();
        } catch (Exception e) {
            handleTransactionException(transaction, e);
        }
    }

    public Course findById(int courseId) {
        return entityManager.find(Course.class, courseId);
    }

    public List<Course> findAll() {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c", Course.class);
        return query.getResultList();
    }

    public void update(Course course) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(course);
            transaction.commit();
        } catch (Exception e) {
            handleTransactionException(transaction, e);
        }
    }

    public void delete(int courseId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Course course = entityManager.find(Course.class, courseId);
            if (course != null) {
                entityManager.remove(course);
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
