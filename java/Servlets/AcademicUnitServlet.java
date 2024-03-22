
package Servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import Dao.AcademicUnitDao;
import Model.AcademicUnit;
import Model.AcademicUnit.UnityType;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

@WebServlet("/academicunit")
public class AcademicUnitServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    private AcademicUnitDao academicUnitDao = new AcademicUnitDao(entityManager);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<AcademicUnit> academicUnits = academicUnitDao.findAll();
        request.setAttribute("academicUnits", academicUnits);
        try {
            request.getRequestDispatcher("/AcademicUnit.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(AcademicUnitServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if (action.equals("create")) {
            createAcademicUnit(request);
        } else if (action.equals("update")) {
            updateAcademicUnit(request);
        } else if (action.equals("delete")) {
            deleteAcademicUnit(request);
        }

        List<AcademicUnit> academicUnits = academicUnitDao.findAll();
        request.setAttribute("academicUnits", academicUnits);

        // Forwarding the request to the JSP page to display academic units
        try {
            request.getRequestDispatcher("/AcademicUnit.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        updateAcademicUnit(request);
        response.sendRedirect(request.getContextPath() + "/academicunit");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        deleteAcademicUnit(request);
        response.sendRedirect(request.getContextPath() + "/academicunit");
    }

    private void createAcademicUnit(HttpServletRequest request) {
        AcademicUnit academicUnit = new AcademicUnit();
        academicUnit.setCode(request.getParameter("code"));
        academicUnit.setName(request.getParameter("name"));
        academicUnit.setUnityType(UnityType.valueOf(request.getParameter("unityType")));

        academicUnitDao.save(academicUnit);
    }

    private void updateAcademicUnit(HttpServletRequest request) {
        int academicId = Integer.parseInt(request.getParameter("academicId"));
        AcademicUnit academicUnit = academicUnitDao.findById(academicId);
        if (academicUnit != null) {
            academicUnit.setCode(request.getParameter("code"));
            academicUnit.setName(request.getParameter("name"));
            academicUnit.setUnityType(UnityType.valueOf(request.getParameter("unityType")));
            academicUnitDao.update(academicUnit);
        } else {
            // Handle case where academic unit with given ID doesn't exist
            // This could be logging an error or showing a message to the user
        }
    }

    private void deleteAcademicUnit(HttpServletRequest request) {
        int academicId = Integer.parseInt(request.getParameter("academicId"));
        academicUnitDao.delete(academicId);
    }

    @Override
    public void destroy() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
