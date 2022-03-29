package Content.Product;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateSerializer extends XmlAdapter<String, Date> {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd-HH-mm");
    @Override
    public Date unmarshal(String v) {
        LocalDateTime date = LocalDateTime.parse(v, formatter);
        Calendar calendar = new GregorianCalendar(date.getYear(),
                                                  date.getMonthValue(),
                                                  date.getDayOfMonth(),
                                                  date.getHour(),
                                                  date.getMinute());
        return calendar.getTime();
    }

    @Override
    public String marshal(Date v) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(v);
        return calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" +
                        calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.HOUR) + "-" + calendar.get(Calendar.MINUTE);
    }
}
