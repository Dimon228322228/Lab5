package Content;


import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement
public class Person {

    private final String name; // not null and not empty

    private final LocalDateTime birthday; // not null

    private final long height; // > 0

    private final int weight; // > 0

    private final String passportID; // not null, len(line) >= 6 and len(line) <= 41

    public Person(String name, LocalDateTime birthday, long height, int weight, String passportID){
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
    }

    @Override
    public String toString(){
        return String.format("Person(owner) name = %s, birthday = %s," +
                                " height = %d, weight = %s, passport ID = %s", name, birthday, height, weight, passportID);
    }

    public String getPassportID(){
        return passportID;
    }

    public String getName(){
        return name;
    }

    public LocalDateTime getBirthday(){
        return birthday;
    }

    public long getHeight(){
        return height;
    }

    public int getWeight(){
        return weight;
    }

}
