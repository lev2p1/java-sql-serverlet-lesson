import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("username") != null) {
            resp.sendRedirect("/welcome");
            return;
        }

        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username_1");
        String password = req.getParameter("password_1");

        if (username.isBlank() || password.isBlank()) {
            req.setAttribute("error", "Bad username or password");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }

        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            resp.sendRedirect("/welcome");
            return;
        }

        if (password.equals("qwerty")) {
            session = req.getSession(true);
            session.setAttribute("username", username);

            resp.sendRedirect("/welcome");
        }
        else {
            req.setAttribute("error", "Invalid password");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

}
