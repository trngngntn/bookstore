package fa.training.meta;

import fa.training.dao.BookingOfficeDAO;
import fa.training.entity.BookingOffice;
import fa.training.utils.validator.GeneralStringValidator;
import fa.training.utils.validator.Validator;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public enum BookingOfficeMeta implements Meta {
    ID("id", "id", int.class, null),
    NAME("name", "name", String.class, GeneralStringValidator.class),
    PHONE("phone", "phone", String.class, GeneralStringValidator.class),
    PLACE("place", "place", String.class, GeneralStringValidator.class),
    PRICE("price", "price", double.class, null),
    START_CONTRACT("startContract","start_contract", Date.class, null),
    END_CONTRACT("endContract","end_contract", Date.class, null),
    TRIP_ID("tripId", "trip_id", int.class, null);

    private final String fieldName;
    private final String dbName;
    private final Class type;
    private final Class<? extends Validator> validator;

    private BookingOfficeMeta(String fieldName, String dbName, Class type, Class<? extends Validator> validator) {
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
        return BookingOffice.class;
    }

    public static Class getDAOClass() {
        return BookingOfficeDAO.class;
    }

    public static String getDBTableName() {
        return "Office";
    }
}