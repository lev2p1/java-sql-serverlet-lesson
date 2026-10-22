import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;
import java.util.Locale;


@WebServlet("/register/save")
public class RegisterServlet extends HttpServlet {
    private Object resp;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String url = "jdbc:postgresql://localhost:5432/sql_connector_lesson";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection con = DriverManager.getConnection(url, "postgres", "qwerty");
            Statement st = con.createStatement();

            String username = req.getParameter("username_1");
            String email = req.getParameter("email_1");
            String password = req.getParameter("password_1");

            HashMap<String, String> hm = new HashMap<>();
            hm.put("name", username);
            hm.put("email", email);
            hm.put("password", String.valueOf(password.hashCode()));

            User u = User.create(hm, false, st);
            u.println(resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
