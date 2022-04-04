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
    public ConsoleReader(CommandFactory commandFactory, CollectionManager manager, Messenger messanger){
        reader = new BufferedReader(new InputStreamReader(System.in));
        super.commandFactory = commandFactory;
        super.messanger = messanger;
        super.manager = manager;
    }

    @Override
    protected boolean readyInput(){
        return true;
    }

    @Override
    public ProductImpl readProduct() throws IOException {
        ProductImpl product = new ProductImpl();
        product.setAutomaticGenerateField();
        CoordinatesImpl coordinates = new CoordinatesImpl();
        PersonImpl owner = new PersonImpl();

        System.out.println(messanger.getFieldInvitationMessage("name"));
        setField(reader.readLine(), product::setNameStr);

        System.out.println(messanger.getFieldInvitationMessage("x"));
        setField(reader.readLine(), coordinates::setXStr);

        System.out.println(messanger.getFieldInvitationMessage("y"));
        setField(reader.readLine(), coordinates::setYStr);

        product.setCoordinates(coordinates);

        System.out.println(messanger.getFieldInvitationMessage("price"));
        setField(reader.readLine(), product::setPriceStr);

        System.out.println(messanger.getFieldInvitationMessage("partNumber"));
        setField(reader.readLine(), product::setPartNumberStr);

        System.out.println(messanger.getFieldInvitationMessage("manufactureCost"));
        setField(reader.readLine(), product::setManufactureCostStr);

        System.out.println(messanger.getUnitOfMeasureInputInvitationMessage());
        setField(reader.readLine(), product::setUnitOfMeasureStr);

        System.out.println(messanger.getFieldInvitationMessage("namePerson"));
        setField(reader.readLine(), owner::setNameStr);

        System.out.println(messanger.getPersonBirthdayInputInvitationMessage());
        setField(reader.readLine(), owner::setBirthdayStr);

        System.out.println(messanger.getFieldInvitationMessage("height"));
        setField(reader.readLine(), owner::setHeightStr);

        System.out.println(messanger.getFieldInvitationMessage("weight"));
        setField(reader.readLine(), owner::setWeightStr);

        System.out.println(messanger.getFieldInvitationMessage("passportId"));
        setField(reader.readLine(), owner::setPassportIDStr);

        product.setOwner(owner);
        return product;
    }

    private void setField(String input, Consumer<String> setter) throws IOException{
        try{
            setter.accept(input);
        } catch (Exception e){
            repeatInput(e);
            setField(reader.readLine(), setter);
        }
    }

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
