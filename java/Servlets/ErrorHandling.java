/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

/**
 *
 * @author hp
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorHandling extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        PrintWriter out = res.getWriter();
        Integer status = (Integer) req.getAttribute("javax.servlet.error.status_code");
        res.setContentType("text/html");
        out.println("<html><head><title>Error Handling</title></head><body>");

        if (status != null) {
            switch (status) {
                case 404:
                    handle404Error(out);
                    break;
                case 500:
                    handle500Error(out);
                    break;
                default:
                    handleOtherErrors(out, status);
                    break;
            }
        } else {
            out.println("<h2>Error</h2>");
            out.println("<p>An unknown error occurred.</p>");
        }

        out.println("</body></html>");
    }

    private void handle404Error(PrintWriter out) {
        out.println("<h2>404 Not Found</h2>");
        out.println("<p>The resource you are looking for could not be found.</p>");
    }

    private void handle500Error(PrintWriter out) {
        out.println("<h2>500 Internal Server Error</h2>");
        out.println("<p>An internal server error occurred while processing the request.</p>");
    }

    private void handleOtherErrors(PrintWriter out, int status) {
        out.println("<h2>Error " + status + "</h2>");
        out.println("<p>An error occurred while processing the request.</p>");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doGet(req, res);
    }
}
