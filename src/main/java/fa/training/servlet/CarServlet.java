package fa.training.servlet;

import fa.training.dao.BookingOfficeDAO;
import fa.training.dao.ParkingLotDAO;
import fa.training.enumeration.ResultFilter;
import fa.training.meta.CarMeta;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CarServlet extends BaseServlet {
    @Override
    protected ResultFilter getDefaultResultFilter() {
        return ResultFilter.NAME;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setTitle(request, "Car Manager");
        setBaseJspPath("baseStaff.jsp");
        try{
            BookingOfficeDAO bookingOfficeDAO = new BookingOfficeDAO();
            request.setAttribute("officeList", bookingOfficeDAO.getAllName());
            ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
            request.setAttribute("parkingLotList", parkingLotDAO.getAllName());
            doGetBase(request, response, CarMeta.class);
        } catch (Exception e) {
            e.printStackTrace();
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
