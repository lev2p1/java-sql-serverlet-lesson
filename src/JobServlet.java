import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/products")
public class JobServlet extends HttpServlet {
    private void println(String title, ResultSet rs, HttpServletResponse resp) throws IOException, SQLException {
        resp.getWriter().println(title + ": " + rs.getString(title));
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String url = "jdbc:postgresql://localhost:5432/sql_connector_lesson";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection con = DriverManager.getConnection(url, "postgres", "qwerty");
            ResultSet rs = con.createStatement().executeQuery("select * from products");
            Product p = Product.create(rs);

            while(rs.next()) {
                p.println(rs, resp);
            }
        }
        catch (SQLException e) {
            System.out.println("Error 25:");
            resp.getWriter().println("SQL error " + e.getMessage());
        }
    }

//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String url = "jdbc:postgresql://localhost:5432/sql_connector_lesson";
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            Connection con = DriverManager.getConnection(url, "postgres", "qwerty");
//            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM jobs");
//            while (rs.next()) {
//                resp.getWriter().println(rs.getInt("id_job") + " " + rs.getString("title"));
//            }
//        } catch (SQLException e) {
//            resp.getWriter().println(e.getMessage());
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String title = req.getParameter("title");
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            String url = "jdbc:mysql://localhost:3306/sql_connector";
//            Connection con = DriverManager.getConnection(url, "root", "12345");
//            PreparedStatement ps = con.prepareStatement("INSERT INTO jobs(title) VALUES (?)");
//            ps.setString(1, title);
//            ps.executeUpdate();
//
//        } catch (Exception e) {
//            resp.getWriter().println(e.getMessage());
//        }
//    }
}