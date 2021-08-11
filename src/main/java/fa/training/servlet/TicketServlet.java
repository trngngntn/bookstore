package fa.training.servlet;

import fa.training.dao.CarDAO;
import fa.training.dao.TripDAO;
import fa.training.entity.Ticket;
import fa.training.meta.Meta;
import fa.training.meta.TicketMeta;
import fa.training.utils.ResultFilter;

import javax.servlet.http.*;
import java.io.IOException;

public class TicketServlet extends BaseServlet<Ticket> {
    @Override
    public Class<TicketMeta> getMeta() {
        return TicketMeta.class;
    }

    @Override
    protected ResultFilter[] getResultFilter() {
        return new ResultFilter[]{ResultFilter.TRIP, ResultFilter.LICENSE_PLATE, ResultFilter.CUSTOMER};
    }

    @Override
    protected void doAfterForwardToList(HttpServletRequest request) throws Exception {
        TripDAO tripDAO = new TripDAO();
        request.setAttribute("tripListNotFull", tripDAO.getAllDestNotFull(null));
    }

    @Override
    protected void doAfterForwardToView(HttpServletRequest request) throws Exception {
        TripDAO tripDAO = new TripDAO();
        Ticket detail = (Ticket) request.getAttribute("detail");
        request.setAttribute("tripListNotFull", tripDAO.getAllDestNotFull(detail.getTripId()));
        CarDAO carDAO = new CarDAO();
        request.setAttribute("carListByTrip", carDAO.searchAll(
                new ResultFilter[]{ResultFilter.TRIP_SP},
                new String[]{detail.getTripId() + ""}
                ));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        addFormTitle = "Add a new ticket";
        editFormTitle = "Ticket detail";
        setTitle(request, "Ticket Manager");
        setBaseJspPath("baseStaff.jsp");
        try{
            TripDAO tripDAO = new TripDAO();
            request.setAttribute("tripList", tripDAO.getAllDest());
            CarDAO carDAO = new CarDAO();
            request.setAttribute("carList", carDAO.getAllLicensePlate());
            super.doGet(request, response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
