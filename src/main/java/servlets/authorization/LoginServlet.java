package servlets.authorization;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public void init() throws ServletException {}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("RECEIVED INFORMATION: " + username + ", " + password);
        response.sendRedirect(request.getContextPath() + "/home");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/authorization/login.jsp").forward(request, response);
    }

    public void destroy() {
        // do nothing.
    }
}