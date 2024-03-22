package Servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import Dao.StudentDao;
import Model.Student;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    private StudentDao studentDao = new StudentDao(entityManager);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Student> students = studentDao.findAll();
        request.setAttribute("students", students);
        try {
            request.getRequestDispatcher("/Student.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if (action.equals("create")) {
            createStudent(request);
        } else if (action.equals("update")) {
            updateStudent(request);
        } else if (action.equals("delete")) {
            deleteStudent(request);
        }

       
List<Student>Students = studentDao.findAll();
        
        request.setAttribute("students", Students);


        // Forwarding the request to the JSP page to display registered students
        try {
			request.getRequestDispatcher("/Student.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void createStudent(HttpServletRequest request) {
        Student student = new Student();
        // Assuming studid is auto-generated
        student.setFirstName(request.getParameter("firstName"));
        student.setLastName(request.getParameter("lastName"));
        // Parse dob from request parameter and convert to LocalDate
        LocalDate dob = LocalDate.parse(request.getParameter("dob"));
        student.setDob(dob);
        studentDao.save(student);
        
        
        
    }

    private void updateStudent(HttpServletRequest request) {
        int studid = Integer.parseInt(request.getParameter("studid"));
        Student student = studentDao.findByStudid(studid);
        if (student != null) {
            student.setFirstName(request.getParameter("firstName"));
            student.setLastName(request.getParameter("lastName"));
            // Parse dob from request parameter and convert to LocalDate
            LocalDate dob = LocalDate.parse(request.getParameter("dob"));
            student.setDob(dob);
            studentDao.update(student);
        } else {
            // Handle case where student with given studid doesn't exist
            // This could be logging an error or showing a message to the user
        }
    }

    private void deleteStudent(HttpServletRequest request) {
        int studid = Integer.parseInt(request.getParameter("studid"));
        studentDao.deleteByStudid(studid);
    }

    @Override
    public void destroy() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
