package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dao.UserDao;
import Model.User;
import java.io.IOException;

@WebServlet("/register")
public class SignUpServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/SignUp.html");
		dispatcher.forward(request, response);
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
        String email = request.getParameter("s-email");
        String password = request.getParameter("s-password");
        String confirmPassword = request.getParameter("cpassword");
        String role = request.getParameter("role");

        if (email == null || email.isEmpty() || password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty() || !password.equals(confirmPassword)) {
            response.getWriter().println("Invalid data. Please fill in all fields and ensure passwords match.");
            return;
        }

        UserDao userDao = new UserDao();
        User user = new User();
        user.setEmail(email);
        User existingUser = userDao.findByEmail(email);

        if (existingUser != null) {
            response.getWriter().println("user with this email already exists");
            return;
       }

        User theUser = new User(email, password, role);
        userDao.createUser(theUser);
        }catch(Exception e){
            System.out.println(e);
            response.getWriter().println("Internal server error");
        }

        response.sendRedirect("Login.html");
    }
}
