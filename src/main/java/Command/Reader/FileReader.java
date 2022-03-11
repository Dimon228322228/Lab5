package Command.Reader;

import Command.CommandFactory.CommandFactory;
import Content.Coordinate.RealizedCoordinates;
import Content.ObjectFactory.RealizedObjectFactory;
import Content.Person.RealizedPerson;
import Content.Product.Product;
import Content.Product.RealizedProduct;
import Manager.CollectionManager;

import java.io.*;

public class FileReader extends AbstractReader {
    public FileReader(CommandFactory commandFactory, CollectionManager manager, File file, RealizedObjectFactory productFactory) throws FileNotFoundException {
        reader = new BufferedReader(new java.io.FileReader(file));
        super.productFactory = productFactory;
        super.commandFactory = commandFactory;
        super.manager = manager;
    }

    @Override
    protected boolean readyInput() throws IOException {
        return reader.ready();
    }

    @Override
    public Product readProduct() throws IOException {
        RealizedCoordinates coordinates =  productFactory.getCoordinates();
        RealizedProduct product = productFactory.getProduct();
        RealizedPerson person = productFactory.getPerson();
        product.setNameStr(reader.readLine());
        coordinates.setXStr(reader.readLine());
        coordinates.setYStr(reader.readLine());
        product.setCoordinates(coordinates);
        product.setPriceStr(reader.readLine());
        product.setPartNumberStr(reader.readLine());
        product.setManufactureCostStr(reader.readLine());
        product.setUnitOfMeasureStr(reader.readLine());
        person.setNameStr(reader.readLine());
        person.setBirthdayStr(reader.readLine());
        person.setHeightStr(reader.readLine());
        person.setWeightStr(reader.readLine());
        person.setPassportIDStr(reader.readLine());
        product.setOwner(person);
        return product;
    }
}
