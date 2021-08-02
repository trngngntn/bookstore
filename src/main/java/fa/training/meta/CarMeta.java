package fa.training.meta;

import fa.training.dao.CarDAO;
import fa.training.entity.Car;
import fa.training.utils.validator.LicensePlateValidator;
import fa.training.utils.validator.Validator;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public enum CarMeta  implements Meta {
    LICENSE_PLATE("licensePlate", "license_plate", String.class, LicensePlateValidator.class, false),
    TYPE("type", "type", String.class, null, false),
    COLOR("color", "color", String.class, null, false),
    OFFICE_ID("officeId","office_id", int.class, null, true),
    PARKING_LOT_ID("parkingLotId","parking_lot_id", int.class, null, true);

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

    private CarMeta(String fieldName, String dbName, Class type, Class<? extends Validator> validator, boolean exclusive) {
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
        return Car.class;
    }

    public static Class getDAOClass() {
        return CarDAO.class;
    }

    public static String getDBTableName() {
        return "Car";
    }

    public static Meta getMeta(String name){
        return NAME_MAP.get(name);
    }
}