package fa.training.utils.db;

import java.sql.*;

public class DBConnectionUtils {
    public static void closeStatement(Statement statement){
        try {
            if(statement != null && !statement.isClosed()){
                statement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeStatement(PreparedStatement statement){
        try {
            if(statement != null && !statement.isClosed()){
                statement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeStatement(CallableStatement statement){
        try {
            if(statement != null && !statement.isClosed()){
                statement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet resultSet){
        try {
            if(resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
