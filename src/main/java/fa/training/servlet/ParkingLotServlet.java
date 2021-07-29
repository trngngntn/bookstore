package fa.training.servlet;

import fa.training.dao.ParkingLotDAO;
import fa.training.enumeration.ResultFilter;
import fa.training.meta.ParkingLotMeta;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ParkingLotServlet extends BaseServlet {
    @Override
    protected ResultFilter getDefaultResultFilter() {
        return ResultFilter.NAME;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //using template
        setTitle(request, "Parking Lot Manager");
        setBaseJspPath("baseStaff.jsp");
        try {
            ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
            request.setAttribute("placeList", parkingLotDAO.getPlaces());
            doGetBase(request, response, ParkingLotMeta.class);
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
