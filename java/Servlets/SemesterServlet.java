package Servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dao.SemesterDao;
import Model.Semester;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@WebServlet("/semesters")
public class SemesterServlet extends HttpServlet {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    private SemesterDao semesterDao = new SemesterDao(entityManager);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Semester> semesters = semesterDao.findAll();
        request.setAttribute("semesters", semesters);
        request.getRequestDispatcher("/Semester.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "create":
                    createSemester(request);
                    break;
                case "update":
                    updateSemester(request);
                    break;
                case "delete":
                    deleteSemester(request);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/Semester.jsp");
                    break;
            }
        }

        List<Semester> semesters = semesterDao.findAll();
        request.setAttribute("semesters", semesters);

        // Forwarding the request to the JSP page to display semesters
        request.getRequestDispatcher("/Semester.jsp").forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        updateSemester(request);
        response.sendRedirect(request.getContextPath() + "/semesters");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        deleteSemester(request);
        response.sendRedirect(request.getContextPath() + "/semesters");
    }

    private void createSemester(HttpServletRequest request) {
        String name = request.getParameter("name");
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));

        Semester semester = new Semester(name, startDate, endDate);
        semesterDao.save(semester);
    }

    private void updateSemester(HttpServletRequest request) {
        int semesterId = Integer.parseInt(request.getParameter("semesterId"));
        Semester semester = semesterDao.findById(semesterId);
        if (semester != null) {
            String name = request.getParameter("name");
            LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
            LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
            semester.setName(name);
            semester.setStartDate(startDate);
            semester.setEndDate(endDate);
            semesterDao.update(semester);
        }
    }

    private void deleteSemester(HttpServletRequest request) {
        int semesterId = Integer.parseInt(request.getParameter("semesterId"));
        semesterDao.delete(semesterId);
    }

    @Override
    public void destroy() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
