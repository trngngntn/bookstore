package fa.training.servlet;

import fa.training.dao.ParkingLotDAO;
import fa.training.entity.ParkingLot;
import fa.training.meta.Meta;
import fa.training.meta.ParkingLotMeta;

import javax.servlet.http.*;
import java.io.IOException;

public class ParkingLotServlet extends BaseServlet<ParkingLot> {
    @Override
    public Class<ParkingLotMeta> getMeta() {
        return ParkingLotMeta.class;
    }

    @Override
    protected Meta getDefaultFilter() {
        return ParkingLotMeta.NAME;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //using template
        addFormTitle = "Add a new parking lot";
        editFormTitle = "Parking lot detail";
        searchableMeta = new ParkingLotMeta[] {ParkingLotMeta.NAME};
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
