package fa.training.meta;

import fa.training.dao.BookingOfficeDAO;
import fa.training.entity.BookingOffice;
import fa.training.utils.validator.GeneralStringValidator;
import fa.training.utils.validator.Validator;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public enum BookingOfficeMeta implements Meta {
    ID("id", "id", int.class, null, true),
    NAME("name", "name", String.class, GeneralStringValidator.class, false),
    PHONE("phone", "phone", String.class, GeneralStringValidator.class, false),
    PLACE("place", "place", String.class, GeneralStringValidator.class, false),
    PRICE("price", "price", double.class, null, true),
    START_CONTRACT("startContract","start_contract", Date.class, null, true),
    END_CONTRACT("endContract","end_contract", Date.class, null, true),
    TRIP_ID("tripId", "trip_id", int.class, null, true);

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

    private BookingOfficeMeta(String fieldName, String dbName, Class type, Class<? extends Validator> validator, boolean exclusive) {
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
        return BookingOffice.class;
    }

    public static Class getDAOClass() {
        return BookingOfficeDAO.class;
    }

    public static String getDBTableName() {
        return "Office";
    }

    public static Meta getMeta(String name){
        return NAME_MAP.get(name);
    }
}