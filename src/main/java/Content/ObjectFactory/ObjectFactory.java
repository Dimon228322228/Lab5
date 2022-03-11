package Content.ObjectFactory;

import Command.CommandFactory.CommandFactory;
import Command.Reader.Reader;
import Content.Coordinate.Coordinates;
import Content.Coordinate.RealizedCoordinates;
import Content.Product.Product;
import Content.Person.RealizedPerson;
import Content.Product.RealizedProduct;
import Manager.CollectionManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

public interface ObjectFactory {
    Coordinates getCoordinates(Integer x, Integer y);
    RealizedCoordinates getCoordinates();
    RealizedPerson getPerson();
    RealizedPerson getPerson(String name, LocalDateTime birthday, long height, int weight, String passportId);
    RealizedProduct getProduct();
    RealizedProduct getProduct(long id, Product product);
    RealizedProduct getProduct(Product product);
    Reader getFileReader(CommandFactory commandFactory, CollectionManager collectionManager, File file) throws FileNotFoundException;
}
