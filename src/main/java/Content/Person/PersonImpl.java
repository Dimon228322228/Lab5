package Content.Person;

import Content.Caster.CasterPersonFromString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class PersonImpl implements Person, Serializable {

    private String name; // not null and not empty

    @XmlJavaTypeAdapter(value = LocalDateTimeSerializer.class)
    private LocalDateTime birthday; // not null

    private long height; // > 0

    private int weight; // > 0

    private String passportID; // not null, len(line) >= 6 and len(line) <= 41

    @XmlTransient
    private final CasterPersonFromString casterPersonFromString = new CasterPersonFromString();

    public PersonImpl(){}

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

    public String getPassportID(){
        return passportID;
    }

    public void setNameStr(String inputStr){
        setName(casterPersonFromString.castName(inputStr));
    }

    public void setBirthdayStr(String inputStr){
        setBirthday(casterPersonFromString.castBirthday(inputStr));
    }

    public void setHeightStr(String inputStr){
        setHeight(casterPersonFromString.castHeight(inputStr));
    }

    public void setWeightStr(String inputStr){
        setWeight(casterPersonFromString.castWeight(inputStr));
    }

    public void setPassportIDStr(String inputStr){
        setPassportID(casterPersonFromString.castPassportID(inputStr));
    }

    @Override
    public String toString(){
        return String.format("Person(owner) name = %s, birthday = %s," +
                " height = %d, weight = %s, passport ID = %s", name, birthday, height, weight, passportID);
    }
}
