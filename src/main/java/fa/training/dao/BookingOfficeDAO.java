package fa.training.dao;

import fa.training.entity.BookingOffice;
import fa.training.meta.BookingOfficeMeta;
import fa.training.utils.db.DBConnectionUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookingOfficeDAO extends BaseDAO<BookingOffice> {

    public BookingOfficeDAO() {
        super(BookingOfficeMeta.class);
    }

    public List<BookingOffice> getAllName() throws Exception {
        final String SQL = "SELECT `id`,`name` FROM `Office`";
        ResultSet resultSet = null;
        List<BookingOffice> result = new ArrayList<>();
        try{
            resultSet = getResultSet(SQL);
            while(resultSet.next()){
                result.add(parseResultSet(resultSet, BookingOfficeMeta.ID, BookingOfficeMeta.NAME));
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
