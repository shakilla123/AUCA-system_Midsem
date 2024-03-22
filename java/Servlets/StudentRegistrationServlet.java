package Servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import Dao.StudentRegistrationDao;
import Dao.SemesterDao;
import Dao.StudentDao;
import Dao.AcademicUnitDao;
import Model.StudentRegistration;
import Model.Student;
import Model.Semester;
import Model.AcademicUnit;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

@WebServlet("/studentregistration")
public class StudentRegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    private StudentRegistrationDao studentRegistrationDao = new StudentRegistrationDao(entityManager);
    private SemesterDao semesterDao = new SemesterDao(entityManager);
    private StudentDao studentDao = new StudentDao(entityManager);
    private AcademicUnitDao academicUnitDao = new AcademicUnitDao(entityManager);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<StudentRegistration> registrations = studentRegistrationDao.findAll();
        request.setAttribute("registrations", registrations);
        try {
            request.getRequestDispatcher("/StudentRegistration.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(StudentRegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if (action.equals("create")) {
            createStudentRegistration(request);
        } else if (action.equals("update")) {
            updateStudentRegistration(request);
        } else if (action.equals("delete")) {
            deleteStudentRegistration(request);
        }

        List<StudentRegistration> registrations = studentRegistrationDao.findAll();
        request.setAttribute("registrations", registrations);

        try {
            request.getRequestDispatcher("/StudentRegistration.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        updateStudentRegistration(request);
        response.sendRedirect(request.getContextPath() + "/studentregistration");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        deleteStudentRegistration(request);
        response.sendRedirect(request.getContextPath() + "/studentregistration");
    }

    private void createStudentRegistration(HttpServletRequest request) {
        // Similar to the create method in your previous servlet
    }

    private void updateStudentRegistration(HttpServletRequest request) {
        int registrationId = Integer.parseInt(request.getParameter("registrationId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int semesterId = Integer.parseInt(request.getParameter("semesterId"));
        int academicId = Integer.parseInt(request.getParameter("academicId"));
        LocalDate registrationDate = LocalDate.parse(request.getParameter("registrationDate"));

        Student student = studentDao.findByStudid(studentId);
        Semester semester = semesterDao.findById(semesterId);
        AcademicUnit academicUnit = academicUnitDao.findById(academicId);

        StudentRegistration registration = studentRegistrationDao.findById(registrationId);
        if (registration != null) {
            registration.setStudent(student);
            registration.setSemester(semester);
            registration.setAcademicUnit(academicUnit);
            registration.setRegistrationDate(registrationDate);
            studentRegistrationDao.update(registration);
        } else {
            // Handle case where registration with given ID doesn't exist
        }
    }

    private void deleteStudentRegistration(HttpServletRequest request) {
        int registrationId = Integer.parseInt(request.getParameter("registrationId"));
        studentRegistrationDao.delete(registrationId);
    }

    @Override
    public void destroy() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
