package fa.training.meta;

import fa.training.dao.EmployeeDAO;
import fa.training.entity.Employee;
import fa.training.utils.validator.Validator;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public enum EmployeeMeta implements Meta {
    // declare fields
    ID("id", "id", int.class, null, true),
    NAME("name", "name", String.class, null, false),
    PHONE("phone", "phone", String.class, null, false),
    DOB("dob", "dob", Date.class, null, true),
    ADDRESS("address", "address", String.class, null, false),
    SEX("sex", "sex", boolean.class, null, true),
    DEPARTMENT_ID("departmentId", "department_id", int.class, null, true),
    EMAIL("email", "email", String.class, null, false),
    ACCOUNT("account", "account", String.class, null, false),
    PASSWORD("password", null, String.class, null, false);

    private static final Map<String, Meta> NAME_MAP = new HashMap<>();

    private final String fieldName;
    private final String dbName;
    private final Class type;
    private final Class<? extends Validator> validator;
    private final boolean exclusive;

    static{
        for(Meta meta : values()){
            NAME_MAP.put(meta.getFieldName(), meta);
        }
    }

    private EmployeeMeta(String fieldName, String dbName, Class type, Class<? extends Validator> validator, boolean exclusive) {
        this.fieldName = fieldName;
        this.dbName = dbName;
        this.type = type;
        this.validator = validator;
        this.exclusive = exclusive;
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
    public Class<? extends Validator> getValidator() {
        return validator;
    }

    @Override
    public boolean isExclusive(){
        return exclusive;
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

    public static Meta getMeta(String name){
        return NAME_MAP.get(name);
    }
}
