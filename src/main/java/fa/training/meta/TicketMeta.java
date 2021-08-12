package fa.training.meta;

import fa.training.dao.TicketDAO;
import fa.training.entity.Ticket;
import fa.training.utils.validator.GeneralStringValidator;
import fa.training.utils.validator.Validator;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public enum TicketMeta implements Meta {
    ID("id", "id", int.class, null),
    CUSTOMER_NAME("customerName", "customer_name", String.class, GeneralStringValidator.class),
    BOOKED_TIME("bookedTime", "booked_time", Time.class, null),
    TRIP_ID("tripId", "trip_id", int.class, null),
    LICENSE_PLATE("licensePlate", "license_plate", String.class, null);

    private final String fieldName;
    private final String dbName;
    private final Class type;
    private final Class<? extends Validator> validator;

    private TicketMeta(String fieldName, String dbName, Class type, Class<? extends Validator> validator) {
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
        return Ticket.class;
    }

    public static Class getDAOClass() {
        return TicketDAO.class;
    }

    public static String getDBTableName() {
        return "Ticket";
    }
}
