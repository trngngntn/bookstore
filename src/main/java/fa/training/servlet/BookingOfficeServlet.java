package fa.training.servlet;

import fa.training.dao.BookingOfficeDAO;
import fa.training.entity.BookingOffice;
import fa.training.entity.ParkingPlace;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookingOfficeServlet", value = "/BookingOfficeServlet")
public class BookingOfficeServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setTitle(request, "Booking Office Manager");
        setBaseJspPath("baseStaff.jsp");

        BookingOfficeDAO bookingOfficeDAO = new BookingOfficeDAO();
        //List<ParkingPlace> parkingPlaceList;

        int id = getId(request);

        if (id == -1) { //no id --> view list
            String keyword = request.getParameter("keyword");
            int index = getIndex(request);
            List<BookingOffice> bookingOfficeList;

            if (index != -1) {

                if (keyword == null || keyword.equals("")) {
                    bookingOfficeList = bookingOfficeDAO.getList(index);
                } else { //have keyword -> search
                    bookingOfficeList = bookingOfficeDAO.search(keyword, index);
                }
                //parkingPlaceList = bookingOfficeDAO.getPlaces();

                //request.setAttribute("placeList", parkingPlaceList);
                request.setAttribute("resultList", bookingOfficeList);
            } else { //invalid index

            }

            forwardToJsp(request, response, "bookingOfficeMgr/list-office.jsp");
        } else { //have id --> view detail
            BookingOffice bookingOffice = bookingOfficeDAO.get(id);
            //parkingPlaceList = bookingOfficeDAO.getPlaces();

            request.setAttribute("detail", bookingOffice);
            //request.setAttribute("placeList", parkingPlaceList);

            forwardToJsp(request, response, "bookingOfficeMgr/view-office.jsp");
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
