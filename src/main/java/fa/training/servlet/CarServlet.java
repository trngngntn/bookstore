package fa.training.servlet;

import fa.training.dao.EmployeeDAO;
import fa.training.entity.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

public class CarServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setTitle(request, "Car Manager");
        setBaseJspPath("baseStaff.jsp");
        int id = getId(request);

        if (id == -1) { //no id --> view list
            String keyword = request.getParameter("keyword");
            int index = getIndex(request);
            List<Employee> employeeList = null;

            if (index == -1) {
                EmployeeDAO employeeDAO = new EmployeeDAO();
                if (keyword == null || keyword.equals("")) {
                    employeeList = employeeDAO.get(index);
                } else { //have keyword -> search
                    employeeList = employeeDAO.search(keyword, index);
                }
            } else {

            }

            request.setAttribute("resultList", employeeList);
            forwardToJsp(request, response, "employeeMgr/list-employee.jsp");
        } else { //have id --> view detail
            System.out.println("TO VIEW");
            forwardToJsp(request, response, "employeeMgr/view-employee.jsp");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
