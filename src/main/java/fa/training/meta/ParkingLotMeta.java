package fa.training.meta;

import fa.training.dao.ParkingLotDAO;
import fa.training.entity.ParkingLot;
import fa.training.utils.validator.Validator;

import java.util.HashMap;
import java.util.Map;

public enum ParkingLotMeta implements Meta {
    ID("id", "id", int.class, null),
    NAME("name", "name", String.class, null),
    PLACE_ID("placeId", "place_id", int.class, null),
    AREA("area", "area", double.class, null),
    PRICE("price", "price", double.class, null),
    STATUS("status", "status", boolean.class, null);

    private final String fieldName;
    private final String dbName;
    private final Class type;
    private final Class<? extends Validator> validator;

    private ParkingLotMeta(String fieldName, String dbName, Class type, Class<? extends Validator> validator) {
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
    public Class<? extends Validator> getValidator() {
        return validator;
    }

    public static Class getEntityClass() {
        return ParkingLot.class;
    }

    public static Class getDAOClass() {
        return ParkingLotDAO.class;
    }

    public static String getDBTableName() {
        return "ParkingLot";
    }
}
