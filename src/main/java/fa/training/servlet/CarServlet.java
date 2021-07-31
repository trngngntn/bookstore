package fa.training.servlet;

import fa.training.dao.BookingOfficeDAO;
import fa.training.dao.ParkingLotDAO;
import fa.training.entity.Car;
import fa.training.meta.CarMeta;
import fa.training.meta.Meta;

import javax.servlet.http.*;
import java.io.IOException;

public class CarServlet extends BaseServlet<Car> {

    @Override
    public Class<CarMeta> getMeta() {
        return CarMeta.class;
    }

    @Override
    protected Meta getDefaultFilter() {
        return CarMeta.LICENSE_PLATE;
    }

    @Override
    protected void doAfterForwardToList(HttpServletRequest request) throws Exception {
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        request.setAttribute("parkingLotListBlank", parkingLotDAO.getAllNameBlank(null));
    }

    @Override
    protected void doAfterForwardToView(HttpServletRequest request) throws Exception {
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        Car detail = (Car) request.getAttribute("detail");
        request.setAttribute("parkingLotListBlank", parkingLotDAO.getAllNameBlank(detail.getParkingLotId()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        addFormTitle = "Add a new car";
        editFormTitle = "Car detail";
        setTitle(request, "Car Manager");
        setBaseJspPath("baseStaff.jsp");
        try{
            BookingOfficeDAO bookingOfficeDAO = new BookingOfficeDAO();
            request.setAttribute("officeList", bookingOfficeDAO.getAllName());
            ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
            request.setAttribute("parkingLotList", parkingLotDAO.getAllName());
            super.doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
