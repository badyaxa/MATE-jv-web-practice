package mate.controller.manufacturer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.lib.Injector;
import mate.model.Manufacturer;
import mate.service.ManufacturerService;

@WebServlet(urlPatterns = "/manufacturers/add")
public class CreateManufacturerController extends HttpServlet {
    private static Injector injector = Injector.getInstance("mate");
    private final ManufacturerService manufacturerService =
            (ManufacturerService) injector.getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("title", "CREATE / ADD manufacturer.");
        req.getRequestDispatcher("/WEB-INF/views/manufacturer/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name").trim();
        String country = req.getParameter("country").trim();

        if (name.isBlank()
                || name.isEmpty()
                || name.length() < 3
                || country.isEmpty()
                || country.isBlank()
                || country.length() < 3) {
            req.setAttribute("title", "<p style=\"color:red\">enter the correct data</p>");
            req.setAttribute("name", name);
            req.setAttribute("country", country);
        } else {
            Manufacturer newManufacturer = new Manufacturer();
            newManufacturer.setName(name);
            newManufacturer.setCountry(country);
            Manufacturer createdManufacturer = manufacturerService.create(newManufacturer);
            Long id = createdManufacturer.getId();

            req.setAttribute("title", "Manufacturer ("
                    + "<a href='" + id + "'>" + name + "</a>"
                    + ") has been successfully created,<br> do you want to create another one?");
        }

        req.getRequestDispatcher("/WEB-INF/views/manufacturer/create.jsp").forward(req, resp);
    }
}
