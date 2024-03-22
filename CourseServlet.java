package Servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import Dao.CourseDao;
import Model.Course;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    private CourseDao courseDao = new CourseDao(entityManager);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Course> courses = courseDao.findAll();
        request.setAttribute("courses", courses);
        try {
            request.getRequestDispatcher("/Course.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(CourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if (action.equals("create")) {
            createCourse(request);
        } else if (action.equals("update")) {
            updateCourse(request);
        } else if (action.equals("delete")) {
            deleteCourse(request);
        }

        List<Course> courses = courseDao.findAll();
        request.setAttribute("courses", courses);

        // Forwarding the request to the JSP page to display courses
        try {
            request.getRequestDispatcher("/Course.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        updateCourse(request);
        response.sendRedirect(request.getContextPath() + "/course");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        deleteCourse(request);
        response.sendRedirect(request.getContextPath() + "/course");
    }

    private void createCourse(HttpServletRequest request) {
        Course course = new Course();
        course.setCourseCode(request.getParameter("courseCode"));
        course.setCourseName(request.getParameter("courseName"));

        courseDao.save(course);
    }

    private void updateCourse(HttpServletRequest request) {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = courseDao.findById(courseId);
        if (course != null) {
            course.setCourseCode(request.getParameter("courseCode"));
            course.setCourseName(request.getParameter("courseName"));
            courseDao.update(course);
        } else {
            // Handle case where course with given ID doesn't exist
            // This could be logging an error or showing a message to the user
        }
    }

    private void deleteCourse(HttpServletRequest request) {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        courseDao.delete(courseId);
    }

    @Override
    public void destroy() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
