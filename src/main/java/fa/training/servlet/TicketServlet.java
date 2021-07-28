package fa.training.servlet;

import fa.training.dao.ParkingLotDAO;
import fa.training.dao.TicketDAO;
import fa.training.dao.TicketDAO;
import fa.training.entity.ParkingLot;
import fa.training.entity.ParkingPlace;
import fa.training.entity.Ticket;
import fa.training.entity.Ticket;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

public class TicketServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setTitle(request, "Ticket Manager");
        setBaseJspPath("baseStaff.jsp");

        TicketDAO ticketDAO = new TicketDAO();
        //List<ParkingPlace> parkingPlaceList;

        int id = getId(request);

        if (id == -1) { //no id --> view list
            String keyword = request.getParameter("keyword");
            int index = getIndex(request);
            List<Ticket> ticketList;

            if (index != -1) {

                if (keyword == null || keyword.equals("")) {
                    ticketList = ticketDAO.getList(index);
                } else { //have keyword -> search
                    ticketList = ticketDAO.search(keyword, index);
                }
                //parkingPlaceList = ticketDAO.getPlaces();

                //request.setAttribute("placeList", parkingPlaceList);
                request.setAttribute("resultList", ticketList);
            } else { //invalid index

            }

            forwardToJsp(request, response, "ticketMgr/list-ticket.jsp");
        } else { //have id --> view detail
            Ticket ticket = ticketDAO.get(id);
            //parkingPlaceList = ticketDAO.getPlaces();

            request.setAttribute("detail", ticket);
            //request.setAttribute("placeList", parkingPlaceList);

            forwardToJsp(request, response, "ticketMgr/view-ticket.jsp");
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
                ticketDAO ticketDAO = new ticketDAO();
                if(ticketDAO.delete(id)){
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
