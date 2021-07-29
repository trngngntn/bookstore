package fa.training.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum ResultFilter {
    ID("id", false),
    NAME("name", false),
    ADDRESS("address", false),
    TRIP("trip", false),
    DESTINATION("destination", false),
    DEPARTURE_DATE("departureDate", true),
    PLACE("place", false);

    private static final Map<String, ResultFilter> LABEL_MAP = new HashMap<>();

    static{
        for(ResultFilter f : values()){
            LABEL_MAP.put(f.label, f);
        }
    }

    private final String label;
    private final boolean exclusive;

    private ResultFilter(String label, boolean exclusive){
        this.label = label;
        this.exclusive = exclusive;
    }

    public String getLabel() {
        return label;
    }

    public boolean isExclusive(){
        return exclusive;
    }

    public static ResultFilter getResultFilter(String label){
        return LABEL_MAP.get(label);
    }
}
