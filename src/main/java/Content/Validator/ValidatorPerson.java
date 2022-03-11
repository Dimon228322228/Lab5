package Content.Validator;

import java.time.LocalDateTime;

public interface ValidatorPerson {

    default boolean NamePersonValid(String name){
        return( name != null && !name.trim().equals(""));
    }

    default boolean BirthdayValid(LocalDateTime time){
        return (time != null);
    }

    default boolean BirthdayValid(String time){
        return (time != null);
    }

    default boolean WeightValid(int weight){
        return weight > 0;
    }

    default boolean HeightValid(long height){
        return height > 0;
    }

    default boolean PassportidValid(String passportid){
        return(passportid != null && passportid.length() >= 6 && passportid.length() <= 41);
    }

}
