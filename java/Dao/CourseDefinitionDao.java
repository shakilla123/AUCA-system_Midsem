package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import Model.CourseDefinition;
import java.util.List;

public class CourseDefinitionDao {

    private EntityManager entityManager;

    public CourseDefinitionDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public CourseDefinition findById(int courseDefinitionId) {
        return entityManager.find(CourseDefinition.class, courseDefinitionId);
    }

    public List<CourseDefinition> findAll() {
        String queryString = "SELECT cd FROM CourseDefinition cd";
        TypedQuery<CourseDefinition> query = entityManager.createQuery(queryString, CourseDefinition.class);
        return query.getResultList();
    }

    public void save(CourseDefinition courseDefinition) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(courseDefinition);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(CourseDefinition courseDefinition) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(courseDefinition);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(int courseDefinitionId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            CourseDefinition courseDefinition = entityManager.find(CourseDefinition.class, courseDefinitionId);
            if (courseDefinition != null) {
                entityManager.remove(courseDefinition);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

