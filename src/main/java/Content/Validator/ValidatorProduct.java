package Content.Validator;

import Content.Coordinate.Coordinates;
import Content.Person.Person;
import Content.Product.UnitOfMeasure;

import java.util.Date;

public class ValidatorProduct {

    private final ValidatorCoordinates validcoordinates = new ValidatorCoordinates();
    private final ValidatorUnitOfMeasure validunitofmeasure = new ValidatorUnitOfMeasure();
    private final ValidatorPerson validperson = new ValidatorPerson();

    public boolean nameProductValid(String name){
        return(name != null && !name.trim().equals(""));
    }

    public boolean coordinateValid(Coordinates coordinates){
        return (coordinates != null &&  validcoordinates.xCoordinateValid(coordinates.getX()) &&
                                        validcoordinates.yCoordinateValid(coordinates.getY()));
    }

    public boolean dateValid(Date date){
        return date != null;
    }

    public boolean priceValid(Double price){
        return (price != null && price > 0);
    }

    public boolean partNumberValid(String partnumber){
        return (partnumber != null && partnumber.length() >= 22);
    }

    public boolean manufactureCostValid(double cost){
        return true;
    }

    public boolean unitOfMeasureValid(UnitOfMeasure unitOfMeasure){
        return validunitofmeasure.validUnitOfMeasure(unitOfMeasure);
    }

    public boolean unitOfMeasureValid(String unitOfMeasure){
        return validunitofmeasure.validUnitOfMeasure(unitOfMeasure);
    }

    public boolean personValid(Person person){
        return (  person != null && validperson.birthdayValid(person.getBirthday()) &&
                                    validperson.heightValid(person.getHeight()) &&
                                    validperson.namePersonValid(person.getName()) &&
                                    validperson.passportidValid(person.getPassportID()) &&
                                    validperson.weightValid(person.getWeight()));
    }

}
