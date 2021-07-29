package fa.training.meta;

import fa.training.dao.EmployeeDAO;
import fa.training.entity.Employee;
import fa.training.utils.validator.Validator;

import java.sql.Date;

public enum EmployeeMeta implements Meta {
    // declare fields
    ID("id", "id", int.class, null),
    NAME("name", "name", String.class, null),
    PHONE("phone", "phone", String.class, null),
    DOB("dob", "dob", Date.class, null),
    ADDRESS("address", "address", String.class, null),
    SEX("sex", "sex", boolean.class, null),
    DEPARTMENT_ID("departmentId", "department_id", int.class, null),
    EMAIL("email", "email", String.class, null),
    ACCOUNT("account", "account", String.class, null),
    PASSWORD("password", null, String.class, null);

    private final String fieldName;
    private final String dbName;
    private final Class type;
    private final Validator validator;

    private EmployeeMeta(String fieldName, String dbName, Class type, Validator validator) {
        this.fieldName = fieldName;
        this.dbName = dbName;
        this.type = type;
        this.validator = validator;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String getDBName() {
        return dbName;
    }

    @Override
    public Class getType() {
        return type;
    }

    @Override
    public Validator getValidator() {
        return validator;
    }

    public static Class getEntityClass() {
        return Employee.class;
    }

    public static Class getDAOClass() {
        return EmployeeDAO.class;
    }

    public static String getDBTableName() {
        return "Employee";
    }
}