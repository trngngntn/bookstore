package fa.training.servlet;

import fa.training.dao.EmployeeDAO;
import fa.training.entity.Employee;
import fa.training.enumeration.ResultFilter;
import fa.training.meta.EmployeeMeta;
import fa.training.utils.DateUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;

public class EmployeeServlet extends BaseServlet<Employee> {
    @Override
    protected ResultFilter getDefaultResultFilter() {
        return ResultFilter.NAME;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //using template
        setTitle(request, "Employee Manager");
        setBaseJspPath("baseHR.jsp");
        try {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            request.setAttribute("departmentList", employeeDAO.getDepartments());
            doGetBase(request, response, EmployeeMeta.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String dobS = request.getParameter("dob");
        String sexS = request.getParameter("sex");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String depIdS = request.getParameter("depId");

        boolean sex = sexS.equals("1");
        int depId = Integer.parseInt(depIdS);
        Date dob = DateUtils.parseDateDB(dobS);

        EmployeeDAO employeeDAO = new EmployeeDAO();
        try {
            if (employeeDAO.add(new Employee(name, phone, dob, address, sex, depId, email, account), password)) {
                response.getWriter().print("success");
            } else {
                response.getWriter().print("failed");
            }
        } catch (Exception e) {
            response.getWriter().print("error");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        /*int id = getId(request);
        try {
            if (id == -1) {
                response.getWriter().print("invalid");
            } else {
                EmployeeDAO employeeDAO = new EmployeeDAO();
                if(employeeDAO.delete(id)){
                    response.getWriter().print("success");
                } else {
                    response.getWriter().print("failed");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
