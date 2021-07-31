package fa.training.dao;

import fa.training.entity.ParkingLot;
import fa.training.entity.ParkingPlace;
import fa.training.meta.ParkingLotMeta;
import fa.training.utils.db.DBConnectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotDAO extends BaseDAO<ParkingLot>{

    public ParkingLotDAO() {
        super(ParkingLotMeta.class);
    }

    @Override
    public boolean update(ParkingLot newObj) throws Exception {
        return update(newObj, ParkingLotMeta.ID, ParkingLotMeta.NAME, ParkingLotMeta.AREA, ParkingLotMeta.PLACE_ID, ParkingLotMeta.PRICE);
    }

    public List<ParkingPlace> getPlaces() {
        final String SQL = "SELECT * FROM `ParkingPlace`";
        ResultSet resultSet = null;
        ArrayList<ParkingPlace> result = new ArrayList<>();
        try {
            resultSet = getResultSet(SQL);
            while (resultSet.next()) {
                result.add(new ParkingPlace(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return result;
    }

    public List<ParkingLot> getAllName() throws Exception {
        final String SQL = "SELECT `id`,`name` FROM `ParkingLot`";
        ResultSet resultSet = null;
        List<ParkingLot> result = new ArrayList<>();
        try{
            resultSet = getResultSet(SQL);
            while(resultSet.next()){
                result.add(parseResultSet(resultSet, ParkingLotMeta.ID, ParkingLotMeta.NAME));
            }
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return result;
    }

    public List<ParkingLot> getAllNameBlank(Object currentId) throws Exception {
        final String SQL = "SELECT `id`,`name` FROM `ParkingLot` WHERE `status` = 0 OR `id` = ?";
        ResultSet resultSet = null;
        List<ParkingLot> result = new ArrayList<>();
        try{
            resultSet = getResultSet(SQL, currentId);
            while(resultSet.next()){
                result.add(parseResultSet(resultSet, ParkingLotMeta.ID, ParkingLotMeta.NAME));
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