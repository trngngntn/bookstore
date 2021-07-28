package fa.training.servlet;

import fa.training.dao.CarDAO;
import fa.training.dao.EmployeeDAO;
import fa.training.entity.Car;
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
            List<Car> carList = null;

            if (index == -1) {
                CarDAO carDAO = new CarDAO();
                if (keyword == null || keyword.equals("")) {
                    carList = carDAO.get(index);
                } else { //have keyword -> search
                    carList = carDAO.search(keyword, index);
                }
            } else {

            }

            request.setAttribute("resultList", carList);
            forwardToJsp(request, response, "carMgr/list-car.jsp");
        } else { //have id --> view detail
            forwardToJsp(request, response, "carMgr/view-car.jsp");
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
