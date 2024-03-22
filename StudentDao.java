package Dao;

	import javax.persistence.EntityManager;
	import javax.persistence.EntityTransaction;
	import javax.persistence.NoResultException;
	import javax.persistence.TypedQuery;
	import Model.Student;
	import java.util.List;

	public class StudentDao {

	    private EntityManager entityManager;

	    public StudentDao(EntityManager entityManager) {
	        this.entityManager = entityManager;
	    }

	    public void save(Student student) {
	        EntityTransaction transaction = entityManager.getTransaction();
	        try {
	            transaction.begin();
	            entityManager.persist(student);
	            transaction.commit();
	        } catch (Exception e) {
	            handleTransactionException(transaction, e);
	        }
	    }

	    public Student findByStudid(int studid) {
	        try {
	            TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s WHERE s.studid = :studid", Student.class);
	            query.setParameter("studid", studid);
	            return query.getSingleResult();
	        } catch (NoResultException e) {
	            // Log or handle the case where no result is found
	            return null;
	        }
	    }

	    public List<Student> findAll() {
	        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s", Student.class);
	        return query.getResultList();
	    }

	    public void update(Student student) {
	        EntityTransaction transaction = entityManager.getTransaction();
	        try {
	            transaction.begin();
	            entityManager.merge(student);
	            transaction.commit();
	        } catch (Exception e) {
	            handleTransactionException(transaction, e);
	        }
	    }

	    public void deleteByStudid(int studid) {
	        EntityTransaction transaction = entityManager.getTransaction();
	        try {
	            transaction.begin();
	            Student student = entityManager.find(Student.class, studid);
	            if (student != null) {
	                entityManager.remove(student);
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

