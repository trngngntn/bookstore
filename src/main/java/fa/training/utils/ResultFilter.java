package fa.training.utils;

import java.util.HashMap;
import java.util.Map;

public enum ResultFilter {
    NAME("name", null, "`name` LIKE CONCAT('%',?,'%')"),
    LICENSE_PLATE("licensePlate", "License plate", "`license_plate` LIKE CONCAT('%',?,'%')"),
    ADDRESS("address", "Address", "`address` LIKE CONCAT('%',?,'%')"),
    PHONE("phone", "Phone", "`phone` LIKE CONCAT('%',?,'%')"),
    DESTINATION("destination", "Destination", "`destination` LIKE CONCAT('%',?,'%')"),
    DRIVER("driver", "Driver", "`driver` LIKE CONCAT('%',?,'%')"),
    TYPE("type", "Type", "`type` LIKE CONCAT('%',?,'%')"),
    COLOR("color", "Color", "`color` LIKE CONCAT('%',?,'%')"),
    PLACE("place", "Place", "`place` LIKE CONCAT('%',?,'%')"),
    CUSTOMER("customer", "Customer", "`customer` LIKE CONCAT('%',?,'%')"),

    DATE("date", "Date", "`departure_date` = ?"),
    FROM_DATE("fromDate", "From date", "`departure_date` >= ?"),
    TO_DATE("toDate", "To date", "`departure_date` <= ?"),


    PARK_PLACE("parkPlace", "Place", "`place_id` = ?"),
    DEPARTMENT("department", "Department", "`department_id` = ?"),
    OFFICE("office", "Office name", "`office_id` IN (SELECT `id` FROM `Office` WHERE `name` LIKE CONCAT('%',?,'%'))"),
    TRIP("trip", "Trip", "`trip_id` IN (SELECT `id` FROM `Trip` WHERE `destination` LIKE CONCAT('%',?,'%'))"),
    STATUS("status", "Status", "`status` = ?"),
    TRIP_SP("trip_sp", null, "`office_id` IN (SELECT `id` FROM `Office` WHERE `trip_id` = ?)");

    private static final Map<String, ResultFilter> LABEL_MAP = new HashMap<>();

    static {
        for (ResultFilter f : values()) {
            LABEL_MAP.put(f.label, f);
        }
    }

    private final String label;
    private final String display;
    private final String sql;

    private ResultFilter(String label, String display, String sql) {
        this.label = label;
        this.display = display;
        this.sql = sql;
    }

    public String getSQL() {
        return sql;
    }

    public String getLabel() {
        return label;
    }

    public String getDisplay() {
        return display;
    }

    public static ResultFilter getResultFilter(String label) {
        return LABEL_MAP.get(label);
    }
}