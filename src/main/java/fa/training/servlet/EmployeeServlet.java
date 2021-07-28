package fa.training.servlet;

import fa.training.dao.EmployeeDAO;
import fa.training.entity.Department;
import fa.training.entity.Employee;
import fa.training.entity.ParkingLot;
import fa.training.utils.DateUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public class EmployeeServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setTitle(request, "Employee Manager");
        setBaseJspPath("baseHR.jsp");

        EmployeeDAO employeeDAO = new EmployeeDAO();
        List<Department> departmentList;

        int id = getId(request);

        if (id == -1) { //no id --> view list
            String keyword = request.getParameter("keyword");
            int index = getIndex(request);
            List<Employee> employeeList = null;

            if (index != -1) {

                if (keyword == null || keyword.equals("")) {
                    employeeList = employeeDAO.getList(index);
                } else { //have keyword -> search
                    employeeList = employeeDAO.search(keyword, index);
                }
                departmentList = employeeDAO.getDepartments();

                request.setAttribute("departmentList", departmentList);
                request.setAttribute("resultList", employeeList);
            } else {

            }

            forwardToJsp(request, response, "employeeMgr/list-employee.jsp");
        } else { //have id --> view detail
            Employee employee = employeeDAO.get(id);
            departmentList = employeeDAO.getDepartments();

            request.setAttribute("detail", employee);
            request.setAttribute("departmentList", departmentList);

            forwardToJsp(request, response, "employeeMgr/view-employee.jsp");
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

        for(Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()){
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        boolean sex = sexS.equals("0")?false:true;
        int depId = Integer.parseInt(depIdS);
        Date dob = DateUtils.parseDateDB(dobS);

        EmployeeDAO employeeDAO = new EmployeeDAO();
        if(employeeDAO.add(new Employee(name, phone, dob, address, sex, depId, email, account), password)){
            response.getWriter().print("success");
        } else {
            response.getWriter().print("failed");
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
