package Dao;

	import Dao.HibernateUtil;
	import java.util.List;
	import Model.User;
	import org.hibernate.Criteria;
	import org.hibernate.Session;
	import org.hibernate.criterion.Restrictions;
	import org.hibernate.Transaction;
	import org.hibernate.query.Query;

	 public class UserDao {
	     public User createUser(User user){
	        try {
	            Session ss = HibernateUtil.getSessionFactory().openSession();
	            ss.save(user);
	            ss.beginTransaction().commit();
	            return user;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	        }
	    public User updateUser(User user) {
	        try {
	            Session ss = HibernateUtil.getSessionFactory().openSession();
	            ss.update(user);
	            ss.beginTransaction().commit();
	            return user;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    public User DeleteUser(User user) {
	        try {
	            Session ss = HibernateUtil.getSessionFactory().openSession();
	            ss.delete(user);
	            ss.beginTransaction().commit();
	            return user;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    public User findUserById(User user) {
	        try {
	            Session ss = HibernateUtil.getSessionFactory().openSession();
	            User theUser =(User)ss.get(User.class, user.getId());
	            return theUser;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    public List<User> getAllUsers() {
	        try {
	            Session session = HibernateUtil.getSessionFactory().openSession();
	            List<User> users = session.createQuery("FROM User").list();
	            session.close(); 
	            return users;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	      public User findByEmail(String email) {
	        try {
	            Session session = HibernateUtil.getSessionFactory().openSession();
	            Criteria criteria = session.createCriteria(User.class);
	            criteria.add(Restrictions.eq("email", email));
	            User foundUser = (User) criteria.uniqueResult();
	            session.close();
	            return foundUser;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	}
	     


