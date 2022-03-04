package Content;

import java.util.Date;

public interface ValidatorProduct {

    ValidatorCoordinates validcoordinates = new ValidatorCoordinates() {};
    ValidatorUnitOfMeasure validunitofmeasure = new ValidatorUnitOfMeasure() {};
    ValidatorPerson validperson = new ValidatorPerson() {};

    default boolean NameProductValid(String name){
        return(name != null && !name.trim().equals(""));
    }

    default boolean CoordinateValid(Coordinates coordinates){
        return (coordinates != null &&  validcoordinates.xCoordinateValid(coordinates.getX()) &&
                                        validcoordinates.yCoordinateValid(coordinates.getY()));
    }

    default boolean DateValid(Date date){
        return date != null;
    }

    default boolean PriceValid(Double price){
        return (price != null && price > 0);
    }

    default boolean partNumberValid(String partnumber){
        return (partnumber != null && partnumber.length() >= 22);
    }

    default boolean manufactureCostValid(double cost){
        return true;
    }

    default boolean UnitOfMeasureValid(UnitOfMeasure unitOfMeasure){
        return validunitofmeasure.ValidUnitOfMeasure(unitOfMeasure);
    }

    default boolean UnitOfMeasureValid(String unitOfMeasure){
        return validunitofmeasure.ValidUnitOfMeasure(unitOfMeasure);
    }

    default boolean PersonValid(Person person){
        return (  person != null && validperson.BirthdayValid(person.getBirthday()) &&
                                    validperson.HeightValid(person.getHeight()) &&
                                    validperson.NamePersonValid(person.getName()) &&
                                    validperson.PassportidValid(person.getPassportID()) &&
                                    validperson.WeightValid(person.getWeight()));
    }

}
