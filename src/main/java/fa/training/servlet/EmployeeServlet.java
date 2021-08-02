package fa.training.servlet;

import fa.training.dao.EmployeeDAO;
import fa.training.entity.Employee;
import fa.training.meta.EmployeeMeta;
import fa.training.meta.Meta;

import javax.servlet.http.*;
import java.io.IOException;

public class EmployeeServlet extends BaseServlet<Employee> {

    @Override
    public Class<EmployeeMeta> getMeta() {
        return EmployeeMeta.class;
    }

    @Override
    protected Meta getDefaultFilter() {
        return EmployeeMeta.NAME;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //using template
        addFormTitle = "Add a new employee";
        editFormTitle = "Employee detail";
        searchableMeta = new EmployeeMeta[] {EmployeeMeta.NAME, EmployeeMeta.PHONE, EmployeeMeta.ADDRESS, EmployeeMeta.EMAIL, EmployeeMeta.ACCOUNT};
        setTitle(request, "Employee Manager");
        setBaseJspPath("baseHR.jsp");
        try {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            request.setAttribute("departmentList", employeeDAO.getDepartments());
            super.doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
