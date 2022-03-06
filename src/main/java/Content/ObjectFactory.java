package Content;

import Command.CommandFactory;
import Command.CommandReader;
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
    CommandReader getFileReader(CommandFactory commandFactory, CollectionManager collectionManager, File file) throws FileNotFoundException;
}
