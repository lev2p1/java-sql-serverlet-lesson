import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Locale;


@WebServlet("/register/save")
public class RegisterServlet extends HttpServlet {
    private Object resp;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username_1");

        req.setAttribute("username_1", username.toUpperCase());

        System.out.println("asd");
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }
}
