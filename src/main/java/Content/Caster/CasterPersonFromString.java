package Content.Caster;

import Content.Validator.ValidatorPerson;
import Exception.InvalidNamePersonException;
import Exception.InvalidBirthdayPersonException;
import Exception.InvalidHeightPersonException;
import Exception.InvalidWeightPersonException;
import Exception.InvalidPassportIDPersonException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CasterPersonFromString {
    ValidatorPerson valPer = new ValidatorPerson();

    public String castName(String inputStr){
        if (valPer.NamePersonValid(inputStr)){
            return inputStr.trim();
        } else {
            throw new InvalidNamePersonException();
        }
    }

    public LocalDateTime castBirthday(String inputStr){
        if (valPer.BirthdayValid(inputStr)){
            String [] data = inputStr.split("-");
            LocalDateTime date;
            if (data.length == 3) {
                date = LocalDateTime.of(Integer.parseInt(data[0]),
                                        Integer.parseInt(data[1]),
                                        Integer.parseInt(data[2]),
                                        00,
                                        00);
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
        if (valPer.HeightValid(height)){
            return height;
        } else {
            throw new InvalidHeightPersonException();
        }
    }

    public int castWeight(String inputStr){
        int weight = Integer.parseInt(inputStr);
        if (valPer.WeightValid(weight)){
            return weight;
        } else {
            throw new InvalidWeightPersonException();
        }
    }

    public String castPassportID(String inputStr){
        if (valPer.PassportidValid(inputStr)){
            return inputStr;
        } else {
            throw new InvalidPassportIDPersonException();
        }
    }
}
