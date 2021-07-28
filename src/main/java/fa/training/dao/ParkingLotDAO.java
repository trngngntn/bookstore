package fa.training.dao;

import fa.training.entity.ParkingLot;
import fa.training.entity.ParkingPlace;
import fa.training.utils.db.DBConnection;
import fa.training.utils.db.DBConnectionPool;
import fa.training.utils.db.DBConnectionUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotDAO {
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

    public ParkingLot get(int id) {
        final String SQL = "SELECT * FROM `ParkingLot` WHERE `id` = ?";
        DBConnection dbConn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().prepareStatement(SQL);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new ParkingLot(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("place_id"),
                        resultSet.getDouble("area"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("status")
                );
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
        return null;
    }

    public List<ParkingLot> getList(int index) {
        final String SQL = "SELECT * FROM `ParkingLot`";
        DBConnection dbConn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<ParkingLot> result = new ArrayList<>();
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().createStatement();
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                result.add(new ParkingLot(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("place_id"),
                        resultSet.getDouble("area"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("status")
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

    public List<ParkingLot> search(String keyword, int index) {
        /*
        * final String sql = "SELECT * FROM\n" +
                "(SELECT `id`, `title`, `brief`, `created_date`, DENSE_RANK() OVER (ORDER BY `id`) AS `sort` FROM `Content`) tbl\n" +
                "WHERE `sort` > ? AND `sort` <= ?";
        DBConnection dbConn = null;
        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        ArrayList<ParkingLot> result = new ArrayList<>();
        try {
            dbConn = DBConnectionPool.getConn();
            prepStatement = dbConn.getConnection().prepareStatement(sql);
            prepStatement.setInt(1, (index - 1) * PAGE_ITEMS);
            prepStatement.setInt(2, index * PAGE_ITEMS);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                result.add(new Content(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("brief"),
                        resultSet.getString("created_date"),
                        resultSet.getInt("sort")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
            DBConnectionUtils.closeStatement(prepStatement);
            DBConnectionPool.release(dbConn);
        }
        return result;*/
        return null;
    }
}