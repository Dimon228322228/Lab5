package Command.Reader;

import Command.CommandFactory.CommandFactory;
import Content.Coordinate.CoordinatesImpl;
import Content.Person.PersonImpl;
import Content.Product.ProductImpl;
import Manager.CollectionManager;

import java.io.*;

public class FileReader extends AbstractReader {
    public FileReader(CommandFactory commandFactory, CollectionManager manager, File file) throws FileNotFoundException {
        reader = new BufferedReader(new java.io.FileReader(file));
        super.commandFactory = commandFactory;
        super.manager = manager;
    }

    /**
     * @return true if file read stream ready
     */
    @Override
    protected boolean readyInput() {
        boolean flag = false;
        try {
            flag = reader.ready();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * don't demand repeat input field
     * @return entire product with filled out field
     * @throws IOException if IO exception occurred
     */
    @Override
    public ProductImpl readProduct() throws IOException {
        CoordinatesImpl coordinates =  new CoordinatesImpl();
        ProductImpl product = new ProductImpl();
        product.setAutomaticGenerateField();
        PersonImpl person = new PersonImpl();

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
