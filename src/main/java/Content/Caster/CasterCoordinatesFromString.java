package Content.Caster;

import Content.Validator.ValidatorCoordinates;
import Exception.InvalidXcoordinateException;
import Exception.InvalidYcoordinateException;

public interface CasterCoordinatesFromString {
    ValidatorCoordinates valCoord = new ValidatorCoordinates() {};

    default Integer castX(String str){
        Integer x = Integer.parseInt(str);
        if (valCoord.xCoordinateValid(x)){
            return x;
        } else {
            throw new InvalidXcoordinateException();
        }
    }

    default Integer castY(String str){
        Integer y = Integer.parseInt(str);
        if (valCoord.yCoordinateValid(y)){
            return y;
        } else {
            throw new InvalidYcoordinateException();
        }
    }
}
