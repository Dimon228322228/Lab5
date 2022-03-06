package Content;

import Manager.AbstractQueueManager;

import java.util.Date;

public class ProcessingProduct extends RealizedProduct{
    public ProcessingProduct(){
        setCreationDate(new Date());
        setId(AbstractQueueManager.getID());
    }
    public ProcessingProduct(String name,
                             Coordinates coordinates,
                             Double price,
                             String partNumber,
                             double manufacturecost,
                             UnitOfMeasure unitOfMeasure,
                             Person person ){
        setId(AbstractQueueManager.getID());
        setCreationDate(new Date());
        setName(name);
        setCoordinates(coordinates);
        setPrice(price);
        setPartNumber(partNumber);
        setManufactureCost(manufacturecost);
        setUnitOfMeasure(unitOfMeasure);
        setOwner(person);
    }
}
