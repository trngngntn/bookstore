package fa.training.servlet;

import fa.training.entity.Trip;
import fa.training.meta.Meta;
import fa.training.meta.TripMeta;
import fa.training.utils.ResultFilter;

import javax.servlet.http.*;
import java.io.IOException;

public class TripServlet extends BaseServlet<Trip> {
    @Override
    public Class<TripMeta> getMeta() {
        return TripMeta.class;
    }

    @Override
    protected ResultFilter[] getResultFilter() {
        return new ResultFilter[]{ResultFilter.DESTINATION, ResultFilter.DATE, ResultFilter.TO_DATE, ResultFilter.FROM_DATE};
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        addFormTitle = "Add a new trip";
        editFormTitle = "Trip detail";
        setTitle(request, "Trip Manager");
        setBaseJspPath("baseStaff.jsp");
        try{
            super.doGet(request, response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

