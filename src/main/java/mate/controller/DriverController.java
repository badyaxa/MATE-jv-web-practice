package mate.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/drivers")
public class DriverController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/header.jsp").include(req, resp);
        req.getRequestDispatcher("/WEB-INF/views/drivers.jsp").include(req, resp);
        req.getRequestDispatcher("/WEB-INF/views/footer.jsp").include(req, resp);
    }
}
