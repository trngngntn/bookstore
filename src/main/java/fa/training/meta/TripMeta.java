package fa.training.meta;

import fa.training.dao.TripDAO;
import fa.training.entity.Trip;
import fa.training.utils.validator.Validator;

import java.sql.Time;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public enum TripMeta implements Meta {
    ID("id", "id", int.class, null, true),
    BOOKED_TICKET("bookedTicket", "booked_ticket", int.class, null, true),
    CAR_TYPE("carType", "car_type", String.class, null, false),
    DEPARTURE_TIME("departureTime", "departure_time", Time.class, null, true),
    DEPARTURE_DATE("departureDate", "departure_date", Date.class, null, true),
    DESTINATION("destination", "destination", String.class, null, false),
    DRIVER("driver", "driver", String.class, null, false),
    MAX_ONL_TICKET("maxOnlTicket", "max_onl_ticket", int.class, null, true);

    private static final Map<String, Meta> NAME_MAP = new HashMap<>();

    private final String fieldName;
    private final String dbName;
    private final Class type;
    private final Validator validator;
    private final boolean exclusive;

    static{
        for(Meta meta : values()){
            NAME_MAP.put(meta.getFieldName(), meta);
        }
    }

    private TripMeta(String fieldName, String dbName, Class type, Validator validator, boolean exclusive) {
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
    public Validator getValidator() {
        return validator;
    }

    @Override
    public boolean isExclusive(){
        return exclusive;
    }

    public static Class getEntityClass() {
        return Trip.class;
    }

    public static Class getDAOClass() {
        return TripDAO.class;
    }

    public static String getDBTableName() {
        return "Trip";
    }

    public static Meta getMeta(String name){
        return NAME_MAP.get(name);
    }
}
