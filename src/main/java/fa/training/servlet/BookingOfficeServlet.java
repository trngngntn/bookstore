package fa.training.servlet;

import fa.training.dao.TripDAO;
import fa.training.entity.BookingOffice;
import fa.training.meta.BookingOfficeMeta;
import fa.training.meta.Meta;
import fa.training.utils.ResultFilter;

import javax.servlet.http.*;
import java.io.IOException;

public class BookingOfficeServlet extends BaseServlet<BookingOffice> {
    @Override
    public Class<BookingOfficeMeta> getMeta() {
        return BookingOfficeMeta.class;
    }

    @Override
    protected ResultFilter[] getResultFilter() {
        return new ResultFilter[]{ResultFilter.NAME, ResultFilter.TRIP, ResultFilter.PLACE};
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        addFormTitle = "Add a new office";
        editFormTitle = "Office detail";
        setTitle(request, "Booking Office Manager");
        setBaseJspPath("baseStaff.jsp");
        try {
            TripDAO tripDAO = new TripDAO();
            request.setAttribute("tripList", tripDAO.getAllDest());
            super.doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
