package fa.training.dao;

import fa.training.entity.Department;
import fa.training.entity.Employee;
import fa.training.utils.HashUtils;
import fa.training.utils.db.DBConnection;
import fa.training.utils.db.DBConnectionPool;
import fa.training.utils.db.DBConnectionUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public List<Department> getDepartments() {
        final String SQL = "SELECT * FROM `Department`";
        DBConnection dbConn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Department> result = new ArrayList<>();
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().createStatement();
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                result.add(new Department(
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

    public Employee get(int id){
        final String sql = "SELECT `id`,`name`,`phone`,`dob`,`address`,`sex`,`department_id`,`email`,`account` FROM `Employee`";
        DBConnection dbConn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getDate("dob"),
                        resultSet.getString("address"),
                        resultSet.getBoolean("sex"),
                        resultSet.getInt("department_id"),
                        resultSet.getString("email"),
                        resultSet.getString("account")
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

    public List<Employee> getList(int index){
        final String sql = "SELECT `id`,`name`,`dob`,`address`,`phone`,`department_id` FROM `Employee`";
        DBConnection dbConn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Employee> result = new ArrayList<>();
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(new Employee(
                   resultSet.getInt("id"),
                   resultSet.getString("name"),
                   resultSet.getString("phone"),
                   resultSet.getDate("dob"),
                   resultSet.getString("address"),
                   resultSet.getInt("department_id")
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
    public List<Employee> search(String keyword, int index){
        return null;
    }

    public boolean delete(int id) {
        return false;
    }

    public String getPwdHash(int uid) {
        final String sql = "SELECT `pwd_hash` FROM `Employee` WHERE `id` = ?";
        DBConnection dbConn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().prepareStatement(sql);
            statement.setInt(1, uid);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("pwd_hash");
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

    public String getSalt(String username) {
        final String sql = "SELECT `salt` FROM `Employee` WHERE `account` = ?";
        DBConnection dbConn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("salt");
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

    public int login(String username, String password) {
        String salt = getSalt(username);
        if(salt == null){
            return -1;
        }
        String pwdHash = HashUtils.generatePwdHash(password, salt);
        //System.out.println(salt + " : " + pwdHash);
        final String sql = "SELECT `id` FROM `Employee` WHERE `account` = ? AND `pwd_hash`=?";
        DBConnection dbConn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,pwdHash);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
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
        return -1;
    }

    public boolean usernameIsExist(String username) {
        final String sql = "SELECT `id` FROM `Employee` WHERE `account` = ?";
        DBConnection dbConn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
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
        return false;
    }

    public boolean emailIsExist(String email) {
        final String sql = "SELECT `id` FROM `Employee` WHERE `email` = ?";
        DBConnection dbConn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().prepareStatement(sql);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
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
        return false;
    }

    public boolean add(Employee employee, String password) {
        final String SQL = "INSERT INTO `CPL_CPMS`.`Employee`(`name`,`phone`,`dob`,`address`,`sex`,`department_id`,`email`,`account`,`salt`,`pwd_hash`)\n" +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";
        String salt = HashUtils.generateSalt();
        String pwdHash = HashUtils.generatePwdHash(password, salt);
        DBConnection dbConn = null;
        PreparedStatement statement = null;
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().prepareStatement(SQL);
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getPhone());
            statement.setDate(3, employee.getDob());
            statement.setString(4, employee.getAddress());
            statement.setBoolean(5, employee.getSex());
            statement.setInt(6, employee.getDepartmentId());
            statement.setString(7, employee.getEmail());
            statement.setString(8, employee.getAccount());
            statement.setString(9, salt);
            statement.setString(10, pwdHash);
            if(statement.executeUpdate() > 0){
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnectionUtils.closeStatement(statement);
            DBConnectionPool.release(dbConn);
        }
        return false;
    }
}
