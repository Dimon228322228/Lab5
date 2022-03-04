package Content;

public interface ValidatorCoordinates {

    default boolean xCoordinateValid(Integer x){
        return x != null && x <= 938;
    }

    default boolean yCoordinateValid(Integer y){
        return y != null;
    }

}
