package Content.Validator;

import java.time.LocalDateTime;

public class ValidatorPerson {

    public boolean namePersonValid(String name){
        return( name != null && !name.trim().equals(""));
    }

    public boolean birthdayValid(LocalDateTime time){
        return (time != null);
    }

    public boolean birthdayValid(String time){
        return (time != null);
    }

    public boolean weightValid(int weight){
        return weight > 0;
    }

    public boolean heightValid(long height){
        return height > 0;
    }

    public boolean passportidValid(String passportid){
        return(passportid != null && passportid.length() >= 6 && passportid.length() <= 41);
    }

}
