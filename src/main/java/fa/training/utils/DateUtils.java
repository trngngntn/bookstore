package fa.training.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class DateUtils {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat DATE_FORMAT_DB = new SimpleDateFormat("yyyy-MM-dd");
    public static Date parseDateDB(String date){
        try {
            return new java.sql.Date(DATE_FORMAT_DB.parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String formatDateDB(Date date){
        return DATE_FORMAT_DB.format(date);
    }
    public static String formatDate(Date date){
        return DATE_FORMAT.format(date);
    }
}
