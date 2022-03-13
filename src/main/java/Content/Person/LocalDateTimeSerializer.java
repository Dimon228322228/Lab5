package Content.Person;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends XmlAdapter<String, LocalDateTime> {
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        return formatter.parse(v, LocalDateTime::from);
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return formatter.format(v);
    }
}
