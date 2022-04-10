package Content.Product;

import Content.Coordinate.Coordinates;
import Content.Person.Person;

import java.util.Date;

/**
 * an interface which implements compare to and has basic getters and setters of product class
 */
public interface Product extends Comparable<Product>{
    long getId();

    String getName();

    Coordinates getCoordinates();

    Date getCreationDate();

    Double getPrice();

    String getPartNumber();

    double getManufactureCost();

    UnitOfMeasure getUnitOfMeasure();

    Person getOwner();

    void setId(long id);

    void setName(String name);

    void setCoordinates(Coordinates coordinates);

    void setPrice(Double price);

    void setPartNumber(String partnumber);

    void setManufactureCost(double cost);

    void setUnitOfMeasure(UnitOfMeasure unitOfMeasure);

    void setOwner(Person person);

    default int compareTo(Product o) {
        return this.getName().compareTo(o.getName());
    }
}
