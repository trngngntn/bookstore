package fa.training.servlet;

import fa.training.dao.TripDAO;
import fa.training.entity.BookingOffice;
import fa.training.meta.BookingOfficeMeta;
import fa.training.meta.Meta;

import javax.servlet.http.*;
import java.io.IOException;

public class BookingOfficeServlet extends BaseServlet<BookingOffice> {
    @Override
    public Class<BookingOfficeMeta> getMeta() {
        return BookingOfficeMeta.class;
    }

    @Override
    protected Meta getDefaultFilter() {
        return BookingOfficeMeta.NAME;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        addFormTitle = "Add a new office";
        editFormTitle = "Office detail";
        searchableMeta = new BookingOfficeMeta[] {BookingOfficeMeta.NAME, BookingOfficeMeta.PHONE, BookingOfficeMeta.PLACE};
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
