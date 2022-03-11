package Content.Product;

import Content.Coordinate.Coordinates;
import Content.Person.Person;
import Manager.AbstractQueueManager;

import java.util.Date;

public class ProcessingProduct extends RealizedProduct {
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

    public ProcessingProduct(long id, Product product){
        setId(id);
        setCreationDate(new Date());
        setName(product.getName());
        setCoordinates(product.getCoordinates());
        setPrice(product.getPrice());
        setPartNumber(product.getPartNumber());
        setManufactureCost(product.getManufactureCost());
        setUnitOfMeasure(product.getUnitOfMeasure());
        setOwner(product.getOwner());
    }

    public ProcessingProduct(Product product){
        setId(AbstractQueueManager.getID());
        setCreationDate(new Date());
        setName(product.getName());
        setCoordinates(product.getCoordinates());
        setPrice(product.getPrice());
        setPartNumber(product.getPartNumber());
        setManufactureCost(product.getManufactureCost());
        setUnitOfMeasure(product.getUnitOfMeasure());
        setOwner(product.getOwner());
    }

}
