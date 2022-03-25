package Content.Validator;

import Content.Coordinate.Coordinates;
import Content.Person.Person;
import Content.Product.UnitOfMeasure;

import java.util.Date;

public class ValidatorProduct {

    ValidatorCoordinates validcoordinates = new ValidatorCoordinates();
    ValidatorUnitOfMeasure validunitofmeasure = new ValidatorUnitOfMeasure();
    ValidatorPerson validperson = new ValidatorPerson();

    public boolean NameProductValid(String name){
        return(name != null && !name.trim().equals(""));
    }

    public boolean CoordinateValid(Coordinates coordinates){
        return (coordinates != null &&  validcoordinates.xCoordinateValid(coordinates.getX()) &&
                                        validcoordinates.yCoordinateValid(coordinates.getY()));
    }

    public boolean DateValid(Date date){
        return date != null;
    }

    public boolean PriceValid(Double price){
        return (price != null && price > 0);
    }

    public boolean partNumberValid(String partnumber){
        return (partnumber != null && partnumber.length() >= 22);
    }

    public boolean manufactureCostValid(double cost){
        return true;
    }

    public boolean UnitOfMeasureValid(UnitOfMeasure unitOfMeasure){
        return validunitofmeasure.ValidUnitOfMeasure(unitOfMeasure);
    }

    public boolean UnitOfMeasureValid(String unitOfMeasure){
        return validunitofmeasure.ValidUnitOfMeasure(unitOfMeasure);
    }

    public boolean PersonValid(Person person){
        return (  person != null && validperson.BirthdayValid(person.getBirthday()) &&
                                    validperson.HeightValid(person.getHeight()) &&
                                    validperson.NamePersonValid(person.getName()) &&
                                    validperson.PassportidValid(person.getPassportID()) &&
                                    validperson.WeightValid(person.getWeight()));
    }

}
