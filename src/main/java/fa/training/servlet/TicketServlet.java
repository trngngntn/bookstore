package fa.training.servlet;

import fa.training.dao.CarDAO;
import fa.training.dao.TripDAO;
import fa.training.entity.Ticket;
import fa.training.meta.Meta;
import fa.training.meta.TicketMeta;

import javax.servlet.http.*;
import java.io.IOException;

public class TicketServlet extends BaseServlet<Ticket> {
    @Override
    public Class<TicketMeta> getMeta() {
        return TicketMeta.class;
    }

    @Override
    protected Meta getDefaultFilter() {
        return TicketMeta.CUSTOMER_NAME;
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
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        addFormTitle = "Add a new ticket";
        editFormTitle = "Ticket detail";
        searchableMeta = new TicketMeta[] {TicketMeta.LICENSE_PLATE, TicketMeta.CUSTOMER_NAME};
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
