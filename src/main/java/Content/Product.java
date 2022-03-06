package Content;

import java.util.Date;

public interface Product {
    long getId();

    String getName();

    Coordinates getCoordinates();

    Date getCreationDate();

    Double getPrice();

    String getPartNumber();

    double getManufactureCost();

    UnitOfMeasure getUnitOfMeasure();

    Person getOwner();

    void setName(String name);

    void setCoordinates(Coordinates coordinates);

    void setPrice(Double price);

    void setPartNumber(String partnumber);

    void setManufactureCost(double cost);

    void setUnitOfMeasure(UnitOfMeasure unitOfMeasure);

    void setOwner(Person person);
}
