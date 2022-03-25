package Content.Validator;

public class ValidatorCoordinates {

    public boolean xCoordinateValid(Integer x){
        return x != null && x <= 938;
    }

    public boolean yCoordinateValid(Integer y){
        return y != null;
    }

}
