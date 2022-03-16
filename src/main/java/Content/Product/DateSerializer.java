package Content.Product;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateSerializer extends XmlAdapter<String, Date> {
    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
    @Override
    public Date unmarshal(String v) throws Exception {
        LocalDate date = formatter.parse(v, LocalDate :: from);
        Date date1 = Date.from(Instant.from(date));
        return date1;
    }

    @Override
    public String marshal(Date v) throws Exception {
        LocalDate localDate = LocalDate.from(v.toInstant().adjustInto(v.toInstant()));
        return formatter.format(localDate);
    }
}
