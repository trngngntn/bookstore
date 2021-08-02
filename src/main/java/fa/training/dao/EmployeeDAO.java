package fa.training.dao;

import fa.training.entity.Department;
import fa.training.entity.Employee;
import fa.training.meta.EmployeeMeta;
import fa.training.utils.HashUtils;
import fa.training.utils.db.DBConnectionUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO extends BaseDAO<Employee> {

    public EmployeeDAO() {
        super(EmployeeMeta.class);
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

    public boolean accountExists(String account) throws Exception {
        final String SQL = "SELECT COUNT(*) FROM `Employee` WHERE `account` = ?";
        ResultSet resultSet = null;
        try {
            resultSet = getResultSet(SQL, account);
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return false;
    }

    public boolean emailExists(String email) throws Exception {
        final String SQL = "SELECT COUNT(*) FROM `Employee` WHERE `email` = ?";
        ResultSet resultSet = null;
        try {
            resultSet = getResultSet(SQL, email);
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return false;
    }

    @Override
    public boolean add(Employee newObj) throws Exception {
        final String SQL = "INSERT INTO `Employee`(`name`,`phone`,`dob`,`address`,`sex`,`department_id`,`email`,`account`,`salt`,`pwd_hash`)\n" +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";
        String salt = HashUtils.generateSalt();
        String pwdHash = HashUtils.generatePwdHash(newObj.getPassword(), salt);
        try {
            return getResult(SQL, newObj.getName(), newObj.getPhone(), newObj.getDob(), newObj.getAddress(),
                    newObj.getSex(), newObj.getDepartmentId(), newObj.getEmail(), newObj.getAccount(), salt, pwdHash) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
