package Content.Caster;

import Content.Validator.ValidatorCoordinates;
import Exception.InvalidXcoordinateException;
import Exception.InvalidYcoordinateException;

public class CasterCoordinatesFromString {
    ValidatorCoordinates valCoord = new ValidatorCoordinates();

    public Integer castX(String str){
        Integer x = Integer.parseInt(str);
        if (valCoord.xCoordinateValid(x)){
            return x;
        } else {
            throw new InvalidXcoordinateException();
        }
    }

    public Integer castY(String str){
        Integer y = Integer.parseInt(str);
        if (valCoord.yCoordinateValid(y)){
            return y;
        } else {
            throw new InvalidYcoordinateException();
        }
    }
}
