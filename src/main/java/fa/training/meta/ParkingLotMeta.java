package fa.training.meta;

import fa.training.dao.ParkingLotDAO;
import fa.training.entity.ParkingLot;
import fa.training.utils.validator.Validator;

import java.util.HashMap;
import java.util.Map;

public enum ParkingLotMeta implements Meta {
    ID("id", "id", int.class, null, true),
    NAME("name", "name", String.class, null, false),
    PLACE_ID("placeId", "place_id", int.class, null, true),
    AREA("area", "area", double.class, null, true),
    PRICE("price", "price", double.class, null, true),
    STATUS("status", "status", boolean.class, null, true);

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

    private ParkingLotMeta(String fieldName, String dbName, Class type, Class<? extends Validator> validator, boolean exclusive) {
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
        return ParkingLot.class;
    }

    public static Class getDAOClass() {
        return ParkingLotDAO.class;
    }

    public static String getDBTableName() {
        return "ParkingLot";
    }

    public static Meta getMeta(String name){
        return NAME_MAP.get(name);
    }
}
