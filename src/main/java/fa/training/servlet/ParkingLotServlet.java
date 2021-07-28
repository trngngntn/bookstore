package fa.training.servlet;

import fa.training.dao.ParkingLotDAO;
import fa.training.entity.ParkingLot;
import fa.training.entity.ParkingPlace;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

public class ParkingLotServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setTitle(request, "Parking Lot Manager");
        setBaseJspPath("baseStaff.jsp");

        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        List<ParkingPlace> parkingPlaceList;

        int id = getId(request);

        if (id == -1) { //no id --> view list
            String keyword = request.getParameter("keyword");
            int index = getIndex(request);
            List<ParkingLot> parkingLotList;

            if (index != -1) {

                if (keyword == null || keyword.equals("")) {
                    parkingLotList = parkingLotDAO.getList(index);
                } else { //have keyword -> search
                    parkingLotList = parkingLotDAO.search(keyword, index);
                }
                parkingPlaceList = parkingLotDAO.getPlaces();

                request.setAttribute("placeList", parkingPlaceList);
                request.setAttribute("resultList", parkingLotList);
            } else { //invalid index

            }

            forwardToJsp(request, response, "parkingLotMgr/list-parkingLot.jsp");
        } else { //have id --> view detail
            ParkingLot parkingLot = parkingLotDAO.get(id);
            parkingPlaceList = parkingLotDAO.getPlaces();

            request.setAttribute("detail", parkingLot);
            request.setAttribute("placeList", parkingPlaceList);

            forwardToJsp(request, response, "parkingLotMgr/view-parkingLot.jsp");
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
                parkingLotDAO parkingLotDAO = new parkingLotDAO();
                if(parkingLotDAO.delete(id)){
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
