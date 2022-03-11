package Content.Person;

import java.time.LocalDateTime;

public class ProcessingPerson extends RealizedPerson {
    public ProcessingPerson(String name, LocalDateTime birthday,long height, int weight, String passportId){
        setBirthday(birthday);
        setName(name);
        setHeight(height);
        setPassportID(passportId);
        setWeight(weight);
    }

    public ProcessingPerson(){

    }
}
