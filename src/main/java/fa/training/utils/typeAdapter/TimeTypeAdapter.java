package fa.training.utils.typeAdapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import fa.training.utils.DateTimeUtils;

import java.io.IOException;
import java.sql.Time;

public class TimeTypeAdapter extends TypeAdapter<Time> {
    @Override
    public void write(JsonWriter jsonWriter, Time time) throws IOException {
        jsonWriter.value(DateTimeUtils.formatTime(time));
    }

    @Override
    public Time read(JsonReader jsonReader) throws IOException {
        if(jsonReader.peek() != JsonToken.NULL){
            return DateTimeUtils.parseTime(jsonReader.nextString());
        } else {
            return null;
        }
    }
}
