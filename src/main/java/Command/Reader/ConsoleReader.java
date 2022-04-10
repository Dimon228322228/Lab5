package Command.Reader;

import Command.CommandFactory.CommandFactory;
import Content.Coordinate.CoordinatesImpl;
import Content.Person.PersonImpl;
import Content.Product.ProductImpl;
import Manager.CollectionManager;
import Messager.Messenger;
import Exception.InvalidProductFieldException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class ConsoleReader extends AbstractReader {
    public ConsoleReader(CommandFactory commandFactory, CollectionManager manager, Messenger messenger){
        reader = new BufferedReader(new InputStreamReader(System.in));
        super.commandFactory = commandFactory;
        super.messenger = messenger;
        super.manager = manager;
    }

    /**
     * @return true always because program read command from console
     */
    @Override
    protected boolean readyInput() {
        return true;
    }

    /**
     * demand repeat input field if it isn't correctness
     * @return entire product with filled out field
     * @throws IOException if IO exception occurred
     */
    @Override
    public ProductImpl readProduct() throws IOException {
        ProductImpl product = new ProductImpl();
        product.setAutomaticGenerateField();
        CoordinatesImpl coordinates = new CoordinatesImpl();
        PersonImpl owner = new PersonImpl();

        System.out.println(messenger.getFieldInvitationMessage("name"));
        setField(reader.readLine(), product::setNameStr);

        System.out.println(messenger.getFieldInvitationMessage("x"));
        setField(reader.readLine(), coordinates::setXStr);

        System.out.println(messenger.getFieldInvitationMessage("y"));
        setField(reader.readLine(), coordinates::setYStr);

        product.setCoordinates(coordinates);

        System.out.println(messenger.getFieldInvitationMessage("price"));
        setField(reader.readLine(), product::setPriceStr);

        System.out.println(messenger.getFieldInvitationMessage("partNumber"));
        setField(reader.readLine(), product::setPartNumberStr);

        System.out.println(messenger.getFieldInvitationMessage("manufactureCost"));
        setField(reader.readLine(), product::setManufactureCostStr);

        System.out.println(messenger.getUnitOfMeasureInputInvitationMessage());
        setField(reader.readLine(), product::setUnitOfMeasureStr);

        System.out.println(messenger.getFieldInvitationMessage("namePerson"));
        setField(reader.readLine(), owner::setNameStr);

        System.out.println(messenger.getPersonBirthdayInputInvitationMessage());
        setField(reader.readLine(), owner::setBirthdayStr);

        System.out.println(messenger.getFieldInvitationMessage("height"));
        setField(reader.readLine(), owner::setHeightStr);

        System.out.println(messenger.getFieldInvitationMessage("weight"));
        setField(reader.readLine(), owner::setWeightStr);

        System.out.println(messenger.getFieldInvitationMessage("passportId"));
        setField(reader.readLine(), owner::setPassportIDStr);

        product.setOwner(owner);
        return product;
    }

    /**
     * try to fill out product field
     * @throws IOException if IO exception occurred
     */
    private void setField(String input, Consumer<String> setter) throws IOException{
        try{
            setter.accept(input);
        } catch (Exception e){
            repeatInput(e);
            setField(reader.readLine(), setter);
        }
    }

    /**
     * handles exception and forgive input one more time
     */
    private void repeatInput(Exception e){
        if (e instanceof InvalidProductFieldException){
            System.err.println(e.getMessage());
        } else if (e instanceof NumberFormatException){
            System.err.println("Number format error got.");
        } else {
            System.err.println("Error.");
        }
        System.out.println("Has got error. Please, entered the field again: ");
    }
}
