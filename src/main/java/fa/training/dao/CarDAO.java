package fa.training.dao;

import fa.training.entity.Car;
import fa.training.meta.CarMeta;
import fa.training.utils.db.DBConnectionUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CarDAO extends BaseDAO<Car>{

    public CarDAO() {
        super(CarMeta.class);
    }

    public List<Car> getAllLicensePlate() throws Exception {
        final String SQL = "SELECT `license_plate` FROM `Car`";
        ResultSet resultSet = null;
        List<Car> result = new ArrayList<>();
        try{
            resultSet = getResultSet(SQL);
            while(resultSet.next()){
                result.add(parseResultSet(resultSet, CarMeta.LICENSE_PLATE));
            }
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return result;
    }
}
