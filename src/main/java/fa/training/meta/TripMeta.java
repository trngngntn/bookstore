package fa.training.meta;

import fa.training.dao.TripDAO;
import fa.training.entity.Trip;
import fa.training.utils.validator.Validator;

import java.sql.Time;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public enum TripMeta implements Meta {
    ID("id", "id", int.class, null),
    BOOKED_TICKET("bookedTicket", "booked_ticket", int.class, null),
    CAR_TYPE("carType", "car_type", String.class, null),
    DEPARTURE_TIME("departureTime", "departure_time", Time.class, null),
    DEPARTURE_DATE("departureDate", "departure_date", Date.class, null),
    DESTINATION("destination", "destination", String.class, null),
    DRIVER("driver", "driver", String.class, null),
    MAX_ONL_TICKET("maxOnlTicket", "max_onl_ticket", int.class, null);

    private final String fieldName;
    private final String dbName;
    private final Class type;
    private final Class<? extends Validator> validator;


    private TripMeta(String fieldName, String dbName, Class type, Class<? extends Validator> validator) {
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
        return Trip.class;
    }

    public static Class getDAOClass() {
        return TripDAO.class;
    }

    public static String getDBTableName() {
        return "Trip";
    }
}
