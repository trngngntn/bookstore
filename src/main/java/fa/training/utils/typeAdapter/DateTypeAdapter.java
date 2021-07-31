package fa.training.utils.typeAdapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import fa.training.utils.DateTimeUtils;

import java.io.IOException;
import java.sql.Date;

public class DateTypeAdapter extends TypeAdapter<Date> {
    @Override
    public void write(JsonWriter jsonWriter, Date date) throws IOException {
        jsonWriter.value(DateTimeUtils.formatDate(date));
    }

    @Override
    public Date read(JsonReader jsonReader) throws IOException {
        if(jsonReader.peek() != JsonToken.NULL){
            return DateTimeUtils.parseDate(jsonReader.nextString());
        } else {
            return null;
        }
    }
}
