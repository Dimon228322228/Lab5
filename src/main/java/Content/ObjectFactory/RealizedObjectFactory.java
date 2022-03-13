package Content.ObjectFactory;

import Command.CommandFactory.CommandFactory;
import Command.Reader.Reader;
import Command.Reader.FileReader;
import Content.Coordinate.Coordinates;
import Content.Coordinate.ProcessingCoordinate;
import Content.Coordinate.RealizedCoordinates;
import Content.Person.ProcessingPerson;
import Content.Person.RealizedPerson;
import Content.Product.ProcessingProduct;
import Content.Product.Product;
import Content.Product.RealizedProduct;
import Manager.CollectionManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

public class RealizedObjectFactory implements ObjectFactory {
    @Override
    public Coordinates getCoordinates(Integer x, Integer y) {
        return new ProcessingCoordinate(x, y);
    }

    @Override
    public RealizedCoordinates getCoordinates() {
        return new ProcessingCoordinate();
    }

    @Override
    public RealizedPerson getPerson() {
        return new ProcessingPerson();
    }

    @Override
    public RealizedPerson getPerson(String name, LocalDateTime birthday, long height, int weight, String passportId) {
        return new ProcessingPerson(name, birthday, height, weight, passportId);
    }

    @Override
    public RealizedProduct getProduct() {
        return new ProcessingProduct();
    }

    public RealizedProduct getProduct(long id, Product product){
        return new ProcessingProduct(id, product);
    }
    public RealizedProduct getProduct(Product product) {
        return new ProcessingProduct(product);
    }

    @Override
    public Reader getFileReader(CommandFactory commandFactory, CollectionManager collectionManager, File file) throws FileNotFoundException {
        return new FileReader(commandFactory, collectionManager, file, this);
    }
}
