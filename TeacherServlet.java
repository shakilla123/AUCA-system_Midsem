package Servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import Dao.TeacherDao;
import Model.Teacher;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    private TeacherDao teacherDao = new TeacherDao(entityManager);

    @Override
    public void destroy() {
        entityManager.close();
        entityManagerFactory.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Teacher> teachers = teacherDao.findAll();
        request.setAttribute("teachers", teachers);
        try {
            request.getRequestDispatcher("/Teacher.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(TeacherServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if (action.equals("save")) {
            saveTeacher(request);
        } else if (action.equals("update")) {
            updateTeacher(request);
        } else if (action.equals("delete")) {
            deleteTeacher(request);
        }

        List<Teacher> teachers = teacherDao.findAll();
        request.setAttribute("teachers", teachers);

        try {
            request.getRequestDispatcher("/Teacher.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(TeacherServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveTeacher(HttpServletRequest request) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(request.getParameter("firstName"));
        teacher.setLastName(request.getParameter("lastName"));
        Teacher.Qualification qualification = Teacher.Qualification.valueOf(request.getParameter("qualification"));
        teacher.setQualification(qualification);
        teacherDao.save(teacher);
    }

    private void updateTeacher(HttpServletRequest request) {
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        Teacher teacher = teacherDao.findByTeacherId(teacherId);
        if (teacher != null) {
            teacher.setFirstName(request.getParameter("firstName"));
            teacher.setLastName(request.getParameter("lastName"));
            Teacher.Qualification qualification = Teacher.Qualification.valueOf(request.getParameter("qualification"));
            teacher.setQualification(qualification);
            teacherDao.update(teacher);
        }
    }

    private void deleteTeacher(HttpServletRequest request) {
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        teacherDao.deleteByTeacherId(teacherId);
    }
}