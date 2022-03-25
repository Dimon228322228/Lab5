package Content.Validator;

import java.time.LocalDateTime;

public class ValidatorPerson {

    public boolean NamePersonValid(String name){
        return( name != null && !name.trim().equals(""));
    }

    public boolean BirthdayValid(LocalDateTime time){
        return (time != null);
    }

    public boolean BirthdayValid(String time){
        return (time != null);
    }

    public boolean WeightValid(int weight){
        return weight > 0;
    }

    public boolean HeightValid(long height){
        return height > 0;
    }

    public boolean PassportidValid(String passportid){
        return(passportid != null && passportid.length() >= 6 && passportid.length() <= 41);
    }

}
