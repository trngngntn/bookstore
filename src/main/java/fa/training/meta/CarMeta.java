package fa.training.meta;

import fa.training.dao.CarDAO;
import fa.training.entity.Car;
import fa.training.utils.validator.Validator;

import java.sql.Date;

public enum CarMeta  implements Meta {
    LICENSE_PLATE("licensePlate", "license_plate", String.class, null),
    TYPE("type", "type", String.class, null),
    COLOR("color", "color", String.class, null),
    OFFICE_ID("officeId","office_id", int.class, null),
    PARKING_LOT_ID("parkingLotId","parking_lot_id", int.class, null);

    private final String fieldName;
    private final String dbName;
    private final Class type;
    private final Validator validator;

    private CarMeta(String fieldName, String dbName, Class type, Validator validator) {
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
        return Car.class;
    }

    public static Class getDAOClass() {
        return CarDAO.class;
    }

    public static String getDBTableName() {
        return "Car";
    }
}