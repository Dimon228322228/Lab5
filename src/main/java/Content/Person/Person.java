package Content.Person;

import java.time.LocalDateTime;

public interface Person {
    String getName();
    LocalDateTime getBirthday();
    long getHeight();
    int getWeight();
    String getPassportID();
    void setName(String name);
    void setBirthday(LocalDateTime dateTime);
    void setHeight(long height);
    void setWeight(int weight);
    void setPassportID(String passportID);

}
