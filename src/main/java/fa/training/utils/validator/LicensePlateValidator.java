package fa.training.utils.validator;

import fa.training.dao.CarDAO;

public class LicensePlateValidator extends NonSpaceStringValidator{
    @Override
    public boolean check(String o) throws Exception {
        CarDAO carDAO = new CarDAO();
        return !carDAO.licensePlateExists(normalize(o));
    }
}
