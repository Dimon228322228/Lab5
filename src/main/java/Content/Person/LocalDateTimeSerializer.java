package Content.Person;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends XmlAdapter<String, LocalDateTime> {
//    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
    @Override
    public LocalDateTime unmarshal(String v) {
        return LocalDateTime.parse(v);//, formatter);
    }

    @Override
    public String marshal(LocalDateTime v) {
        return v.toString();//v.format(formatter);
    }
}
