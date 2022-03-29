package Content.Caster;

import Content.Validator.ValidatorPerson;
import Exception.InvalidNamePersonException;
import Exception.InvalidBirthdayPersonException;
import Exception.InvalidHeightPersonException;
import Exception.InvalidWeightPersonException;
import Exception.InvalidPassportIDPersonException;

import java.time.LocalDateTime;

public class CasterPersonFromString {
    ValidatorPerson valPer = new ValidatorPerson();

    public String castName(String inputStr){
        if (valPer.namePersonValid(inputStr)){
            return inputStr.trim();
        } else {
            throw new InvalidNamePersonException();
        }
    }

    public LocalDateTime castBirthday(String inputStr){
        if (valPer.birthdayValid(inputStr)){
            String [] data = inputStr.split("-");
            LocalDateTime date;
            if (data.length == 3) {
                date = LocalDateTime.of(Integer.parseInt(data[0]),
                                        Integer.parseInt(data[1]),
                                        Integer.parseInt(data[2]),
                                        0,
                                        0);
                return date;
            } else {
                throw new InvalidBirthdayPersonException();
            }
        } else {
            throw new InvalidBirthdayPersonException();
        }
    }

    public long castHeight(String inputStr){
        long height = Long.parseLong(inputStr);
        if (valPer.heightValid(height)){
            return height;
        } else {
            throw new InvalidHeightPersonException();
        }
    }

    public int castWeight(String inputStr){
        int weight = Integer.parseInt(inputStr);
        if (valPer.weightValid(weight)){
            return weight;
        } else {
            throw new InvalidWeightPersonException();
        }
    }

    public String castPassportID(String inputStr){
        if (valPer.passportidValid(inputStr)){
            return inputStr;
        } else {
            throw new InvalidPassportIDPersonException();
        }
    }
}
