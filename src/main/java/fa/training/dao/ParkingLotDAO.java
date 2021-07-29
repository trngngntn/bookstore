package fa.training.dao;

import fa.training.entity.ParkingLot;
import fa.training.entity.ParkingPlace;
import fa.training.meta.ParkingLotMeta;
import fa.training.utils.db.DBConnection;
import fa.training.utils.db.DBConnectionPool;
import fa.training.utils.db.DBConnectionUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotDAO extends BaseDAO<ParkingLot>{

    public ParkingLotDAO() {
        super(ParkingLotMeta.class);
    }

    public List<ParkingPlace> getPlaces() {
        final String SQL = "SELECT * FROM `ParkingPlace`";
        DBConnection dbConn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<ParkingPlace> result = new ArrayList<>();
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().createStatement();
            resultSet = statement.executeQuery(SQL);
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
            DBConnectionUtils.closeStatement(statement);
            DBConnectionPool.release(dbConn);
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
}