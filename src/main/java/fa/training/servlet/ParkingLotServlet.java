package fa.training.servlet;

import fa.training.dao.ParkingLotDAO;
import fa.training.entity.ParkingLot;
import fa.training.meta.Meta;
import fa.training.meta.ParkingLotMeta;
import fa.training.utils.ResultFilter;

import javax.servlet.http.*;
import java.io.IOException;

public class ParkingLotServlet extends BaseServlet<ParkingLot> {
    @Override
    public Class<ParkingLotMeta> getMeta() {
        return ParkingLotMeta.class;
    }

    @Override
    protected ResultFilter[] getResultFilter() {
        return new ResultFilter[]{ResultFilter.NAME, ResultFilter.PARK_PLACE, ResultFilter.STATUS};
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //using template
        addFormTitle = "Add a new parking lot";
        editFormTitle = "Parking lot detail";
        setTitle(request, "Parking Lot Manager");
        setBaseJspPath("baseStaff.jsp");
        try {
            ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
            request.setAttribute("placeList", parkingLotDAO.getPlaces());
            super.doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
