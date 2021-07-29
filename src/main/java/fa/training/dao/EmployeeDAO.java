package fa.training.dao;

import fa.training.entity.Department;
import fa.training.entity.Employee;
import fa.training.enumeration.ResultFilter;
import fa.training.meta.EmployeeMeta;
import fa.training.utils.HashUtils;
import fa.training.utils.db.DBConnection;
import fa.training.utils.db.DBConnectionPool;
import fa.training.utils.db.DBConnectionUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO extends BaseDAO<Employee>{

    public EmployeeDAO() {
        super(EmployeeMeta.class);
    }

    public boolean add(Employee newObj, String password) {
        return false;
    }

    @Override
    public boolean update(Employee newObj) {
        return false;
    }

    public List<Department> getDepartments() throws Exception {
        final String SQL = "SELECT * FROM `Department`";
        ResultSet resultSet = null;
        ArrayList<Department> result = new ArrayList<>();
        try {
            resultSet = getResultSet(SQL);
            while (resultSet.next()) {
                result.add(new Department(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return result;
    }

    /*

    public List<Employee> getList(int index) throws Exception {
        final String SQL = "SELECT * FROM (SELECT `id`,`name`,`phone`,`dob`,`address`,`sex`,`department_id`,`email`,`account`" +
                ", DENSE_RANK() OVER (ORDER BY `id`) AS `sort` FROM `Employee`) tbl " +
                "WHERE `sort` > ? AND `sort` <= ?";
        DBConnection dbConn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Employee> result = new ArrayList<>();
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().prepareStatement(SQL);
            statement.setInt(1, (index - 1) * Parameters.PAGINATION_ENTRY_COUNT);
            statement.setInt(2, index * Parameters.PAGINATION_ENTRY_COUNT);
            resultSet = statement.executeQuery();
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
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
            DBConnectionUtils.closeStatement(statement);
            DBConnectionPool.release(dbConn);
        }
        return result;
    }

    public List<Employee> search(String keyword, ResultFilter filter, int index) throws Exception {
        final String SQL = "SELECT * FROM (SELECT `id`,`name`,`phone`,`dob`,`address`,`sex`,`department_id`,`email`,`account`" +
                ", DENSE_RANK() OVER (ORDER BY `id`) AS `sort` FROM `Employee` WHERE `" + filter.getLabel() + "` LIKE ? ) tbl " +
                "WHERE `sort` > ? AND `sort` <= ?";
        DBConnection dbConn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Employee> result = new ArrayList<>();
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().prepareStatement(SQL);
            statement.setString(1, "%"+keyword+"%");
            statement.setInt(2, (index - 1) * Parameters.PAGINATION_ENTRY_COUNT);
            statement.setInt(3, index * Parameters.PAGINATION_ENTRY_COUNT);
            resultSet = statement.executeQuery();
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
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
            DBConnectionUtils.closeStatement(statement);
            DBConnectionPool.release(dbConn);
        }
        return result;
    }

    public boolean delete(int id) {
        return false;
    }*/

    public String getPwdHash(int id) throws Exception {
        final String SQL = "SELECT `pwd_hash` FROM `Employee` WHERE `id` = ?";
        ResultSet resultSet = null;
        try {
            resultSet = getResultSet(SQL, id);
            if (resultSet.next()) {
                return resultSet.getString("pwd_hash");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return null;
    }

    public String getSalt(String username) throws Exception {
        final String SQL = "SELECT `salt` FROM `Employee` WHERE `account` = ?";
        ResultSet resultSet = null;
        try {
            resultSet = getResultSet(SQL, username);
            if (resultSet.next()) {
                return resultSet.getString("salt");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return null;
    }

    public int login(String username, String password) throws Exception {
        String salt = getSalt(username);
        if (salt == null) {
            return -1;
        }
        String pwdHash = HashUtils.generatePwdHash(password, salt);
        //System.out.println(salt + " : " + pwdHash);
        final String SQL = "SELECT `id` FROM `Employee` WHERE `account` = ? AND `pwd_hash`=?";
        ResultSet resultSet = null;
        try {
            resultSet = getResultSet(SQL, username, pwdHash);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return -1;
    }

    public boolean usernameIsExist(String username) throws Exception {
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
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
            DBConnectionUtils.closeStatement(statement);
            DBConnectionPool.release(dbConn);
        }
        return false;
    }

    public boolean emailIsExist(String email) throws Exception {
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
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
            DBConnectionUtils.closeStatement(statement);
            DBConnectionPool.release(dbConn);
        }
        return false;
    }

    /*public boolean add(Employee employee, String password) throws Exception {
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
            if (statement.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnectionUtils.closeStatement(statement);
            DBConnectionPool.release(dbConn);
        }
        return false;
    }*/
}
