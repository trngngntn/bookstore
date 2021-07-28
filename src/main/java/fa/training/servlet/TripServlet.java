package fa.training.servlet;

import fa.training.dao.ParkingLotDAO;
import fa.training.dao.TripDAO;
import fa.training.entity.ParkingLot;
import fa.training.entity.ParkingPlace;
import fa.training.entity.Trip;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TripServlet", value = "/TripServlet")
public class TripServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setTitle(request, "Trip Manager");
        setBaseJspPath("baseStaff.jsp");

        TripDAO tripDAO = new TripDAO();
        //List<ParkingPlace> parkingPlaceList;

        int id = getId(request);

        if (id == -1) { //no id --> view list
            String keyword = request.getParameter("keyword");
            int index = getIndex(request);
            List<Trip> tripList;

            if (index != -1) {

                if (keyword == null || keyword.equals("")) {
                    tripList = tripDAO.getList(index);
                } else { //have keyword -> search
                    tripList = tripDAO.search(keyword, index);
                }
                //parkingPlaceList = tripDAO.getPlaces();

                //request.setAttribute("placeList", parkingPlaceList);
                request.setAttribute("resultList", tripList);
            } else { //invalid index

            }

            forwardToJsp(request, response, "tripMgr/list-trip.jsp");
        } else { //have id --> view detail
            Trip trip = tripDAO.get(id);
            //parkingPlaceList = tripDAO.getPlaces();

            request.setAttribute("detail", trip);
            //request.setAttribute("placeList", parkingPlaceList);

            forwardToJsp(request, response, "tripMgr/view-trip.jsp");
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
                tripDAO tripDAO = new tripDAO();
                if(tripDAO.delete(id)){
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

