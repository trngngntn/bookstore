package fa.training.servlet;

import fa.training.entity.Trip;
import fa.training.meta.Meta;
import fa.training.meta.TripMeta;

import javax.servlet.http.*;
import java.io.IOException;

public class TripServlet extends BaseServlet<Trip> {
    @Override
    public Class<TripMeta> getMeta() {
        return TripMeta.class;
    }

    @Override
    protected Meta getDefaultFilter() {
        return TripMeta.DESTINATION;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        addFormTitle = "Add a new trip";
        editFormTitle = "Trip detail";
        searchableMeta = new TripMeta[] {TripMeta.DESTINATION, TripMeta.DRIVER, TripMeta.CAR_TYPE};
        setTitle(request, "Trip Manager");
        setBaseJspPath("baseStaff.jsp");
        try{
            super.doGet(request, response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

