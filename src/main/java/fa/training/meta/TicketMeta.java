package fa.training.meta;

import fa.training.dao.TicketDAO;
import fa.training.entity.Ticket;
import fa.training.utils.validator.Validator;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public enum TicketMeta implements Meta {
    ID("id", "id", int.class, null, true),
    CUSTOMER_NAME("customerName", "customer_name", String.class, null, false),
    BOOKED_TIME("bookedTime", "booked_time", Time.class, null, true),
    TRIP_ID("tripId", "trip_id", int.class, null, true),
    LICENSE_PLATE("licensePlate", "license_plate", String.class, null, false);

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

    private TicketMeta(String fieldName, String dbName, Class type, Validator validator, boolean exclusive) {
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
        return Ticket.class;
    }

    public static Class getDAOClass() {
        return TicketDAO.class;
    }

    public static String getDBTableName() {
        return "Ticket";
    }

    public static Meta getMeta(String name){
        return NAME_MAP.get(name);
    }
}
