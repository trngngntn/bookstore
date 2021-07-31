package fa.training.dao;

import fa.training.entity.Trip;
import fa.training.meta.TripMeta;
import fa.training.utils.db.DBConnectionUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TripDAO extends BaseDAO<Trip> {

    public TripDAO() {
        super(TripMeta.class);
    }

    @Override
    public List<Trip> getList(int index) throws Exception {
        return super.getList(index,
                TripMeta.ID,
                TripMeta.DESTINATION,
                TripMeta.DEPARTURE_TIME,
                TripMeta.DRIVER,
                TripMeta.CAR_TYPE,
                TripMeta.BOOKED_TICKET
        );
    }

    public List<Trip> getAllDest() throws Exception {
        final String SQL = "SELECT `id`,`destination` FROM `Trip`";
        ResultSet resultSet = null;
        List<Trip> result = new ArrayList<>();
        try{
            resultSet = getResultSet(SQL);
            while(resultSet.next()){
                result.add(parseResultSet(resultSet, TripMeta.ID, TripMeta.DESTINATION));
            }
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return result;
    }

    public List<Trip> getAllDestNotFull(Object currentId) throws Exception {
        final String SQL = "SELECT `id`,`destination` FROM `Trip` WHERE `booked_ticket` < `max_onl_ticket` OR `id` = ?";
        ResultSet resultSet = null;
        List<Trip> result = new ArrayList<>();
        try{
            resultSet = getResultSet(SQL, currentId);
            while(resultSet.next()){
                result.add(parseResultSet(resultSet, TripMeta.ID, TripMeta.DESTINATION));
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
