package fa.training.servlet;

import fa.training.dao.CarDAO;
import fa.training.dao.EmployeeDAO;
import fa.training.utils.db.DBException;
import fa.training.utils.validator.LicensePlateValidator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CheckServlet", value = "/check")
public class CheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String email = request.getParameter("email");
        String licensePlate = request.getParameter("licensePlate");
        try {
            if (account != null) {
                EmployeeDAO employeeDAO = new EmployeeDAO();
                if(account.indexOf(" ") != -1) {
                    response.getWriter().print("bad_format");
                    return;
                }
                response.getWriter().print(employeeDAO.accountExists(account.trim()));
            }
            if (email != null) {
                EmployeeDAO employeeDAO = new EmployeeDAO();
                response.getWriter().print(employeeDAO.emailExists(email.trim()));
            }
            if (licensePlate != null) {
                CarDAO carDAO = new CarDAO();
                LicensePlateValidator v = new LicensePlateValidator();
                licensePlate = v.normalize(licensePlate);
                response.getWriter().print(carDAO.licensePlateExists(licensePlate));
            }
        } catch (DBException e) {
            e.printStackTrace();
            response.getWriter().print("database_error");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("internal_error");
        }
    }
}
