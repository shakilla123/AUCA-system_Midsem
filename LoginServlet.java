package Servlets;

import Dao.UserDao;
import Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.html");
		dispatcher.forward(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        User user = userDao.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
              // Create or retrieve the session
            HttpSession session = request.getSession();
            // Set session timeout to 1 minute (in seconds)
        
            // Store user object in the session for future reference
            session.setAttribute("user", user);
            response.sendRedirect("index.jsp");
        } else {
            // Display error message or redirect to login page with error message
            response.sendRedirect("login.html?error=invalid_credentials");
        }
    }
}

