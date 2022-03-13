package Content.Person;

import Content.Caster.CasterPersonFromString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class RealizedPerson implements Person, CasterPersonFromString, Serializable {

    private String name; // not null and not empty
    @XmlJavaTypeAdapter(value = LocalDateTimeSerializer.class)
    private LocalDateTime birthday; // not null

    private long height; // > 0

    private int weight; // > 0

    private String passportID; // not null, len(line) >= 6 and len(line) <= 41

    @Override
    public String toString(){
        return String.format("Person(owner) name = %s, birthday = %s," +
                                " height = %d, weight = %s, passport ID = %s", name, birthday, height, weight, passportID);
    }

    public String getPassportID(){
        return passportID;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBirthday(LocalDateTime dateTime) {
        this.birthday = dateTime;
    }

    @Override
    public void setHeight(long height) {
        this.height = height;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public void setPassportID(String passportID) {
        this.passportID = passportID;
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

    public void setNameStr(String inputStr){
        setName(castName(inputStr));
    }

    public void setBirthdayStr(String inputStr){
        setBirthday(castBirthday(inputStr));
    }

    public void setHeightStr(String inputStr){
        setHeight(castHeight(inputStr));
    }

    public void setWeightStr(String inputStr){
        setWeight(castWeight(inputStr));
    }

    public void setPassportIDStr(String inputStr){
        setPassportID(castPassportID(inputStr));
    }
}