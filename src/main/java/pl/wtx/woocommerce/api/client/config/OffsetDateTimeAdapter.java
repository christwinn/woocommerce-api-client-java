package pl.wtx.woocommerce.api.client.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import pl.wtx.woocommerce.api.client.invoker.JSON;

public class OffsetDateTimeAdapter extends TypeAdapter<OffsetDateTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public void write(JsonWriter out, OffsetDateTime value) throws IOException {

        if (value != null) {
            out.value(value.format(FORMATTER));
        } else {
            out.nullValue();
        }

    }

    @Override
    public OffsetDateTime read(JsonReader in) throws IOException {

        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }

        String date = in.nextString();

        if (date != null && !date.isEmpty()) {
            //see note is invoker.JSON
            if (date.length() == 19){
                try {
                    return LocalDateTime.parse(date).atOffset(ZoneOffset.UTC);
                } catch (DateTimeParseException e) {
                    Logger.getLogger(JSON.class.getName()).log(Level.SEVERE, "Invalid DateTime", e);
                    return null;
                }
            }else {
                try {
                    return OffsetDateTime.parse(date);
                } catch (DateTimeParseException e) {
                    LocalDateTime ldt = LocalDateTime.parse(date);
                    return ldt.atOffset(ZoneOffset.UTC);
                }
            }
        }

        return null;

    }

}
