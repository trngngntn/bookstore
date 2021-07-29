package fa.training.servlet;

import fa.training.dao.TripDAO;
import fa.training.enumeration.ResultFilter;
import fa.training.meta.BookingOfficeMeta;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookingOfficeServlet", value = "/BookingOfficeServlet")
public class BookingOfficeServlet extends BaseServlet {
    @Override
    protected ResultFilter getDefaultResultFilter() {
        return ResultFilter.NAME;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setTitle(request, "Booking Office Manager");
        setBaseJspPath("baseStaff.jsp");
        try {
            TripDAO tripDAO = new TripDAO();
            request.setAttribute("tripList", tripDAO.getAllDest());
            doGetBase(request, response, BookingOfficeMeta.class);
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
                bookingOfficeDAO bookingOfficeDAO = new bookingOfficeDAO();
                if(bookingOfficeDAO.delete(id)){
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
