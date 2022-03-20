package Content.Caster;

import Content.Validator.ValidatorPerson;
import Exception.InvalidNamePersonException;
import Exception.InvalidBirthdayPersonException;
import Exception.InvalidHeightPersonException;
import Exception.InvalidWeightPersonException;
import Exception.InvalidPassportIDPersonException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface CasterPersonFromString {
    ValidatorPerson valPer = new ValidatorPerson() {};

    default String castName(String inputStr){
        if (valPer.NamePersonValid(inputStr)){
            return inputStr.trim();
        } else {
            throw new InvalidNamePersonException();
        }
    }

    default LocalDateTime castBirthday(String inputStr){
        if (valPer.BirthdayValid(inputStr)){
            String [] data = inputStr.split("-");
            LocalDateTime date;
            if (data.length == 5) {
                date = LocalDateTime.of(Integer.parseInt(data[0]),
                                        Integer.parseInt(data[1]),
                                        Integer.parseInt(data[2]),
                                        Integer.parseInt(data[3]),
                                        Integer.parseInt(data[4]));
                return date;
            } else {
                throw new InvalidBirthdayPersonException();
            }
        } else {
            throw new InvalidBirthdayPersonException();
        }
    }

    default long castHeight(String inputStr){
        long height = Long.parseLong(inputStr);
        if (valPer.HeightValid(height)){
            return height;
        } else {
            throw new InvalidHeightPersonException();
        }
    }

    default int castWeight(String inputStr){
        int weight = Integer.parseInt(inputStr);
        if (valPer.WeightValid(weight)){
            return weight;
        } else {
            throw new InvalidWeightPersonException();
        }
    }

    default String castPassportID(String inputStr){
        if (valPer.PassportidValid(inputStr)){
            return inputStr;
        } else {
            throw new InvalidPassportIDPersonException();
        }
    }
}
