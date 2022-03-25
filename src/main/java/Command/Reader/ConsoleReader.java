package Command.Reader;

import Command.CommandFactory.CommandFactory;
import Content.Coordinate.RealizedCoordinates;
import Content.ObjectFactory.ObjectFactory;
import Content.Person.RealizedPerson;
import Content.Product.RealizedProduct;
import Manager.CollectionManager;
import Messager.Messanger;
import Exception.InvalidProductFieldException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class ConsoleReader extends AbstractReader {
    public ConsoleReader(CommandFactory commandFactory, CollectionManager manager, ObjectFactory objectFactory, Messanger messanger){
        reader = new BufferedReader(new InputStreamReader(System.in));
        super.commandFactory = commandFactory;
        super.messanger = messanger;
        super.manager = manager;
        super.productFactory = objectFactory;
    }

    @Override
    protected boolean readyInput(){
        return true;
    }

    @Override
    public RealizedProduct readProduct() throws IOException {
        RealizedProduct product = productFactory.getProduct();
        RealizedCoordinates coordinates = productFactory.getCoordinates();
        RealizedPerson owner = productFactory.getPerson();

        System.out.println(messanger.getNameInputInvitationMessage());
        setField(reader.readLine(), product::setNameStr);

        System.out.println(messanger.getXInputInvitationMessage());
        setField(reader.readLine(), coordinates::setXStr);

        System.out.println(messanger.getYInputInvitationMessage());
        setField(reader.readLine(), coordinates::setYStr);

        product.setCoordinates(coordinates);

        System.out.println(messanger.getPriceInputInvitationMessage());
        setField(reader.readLine(), product::setPriceStr);

        System.out.println(messanger.getPartNumberInputInvitationMessage());
        setField(reader.readLine(), product::setPartNumberStr);

        System.out.println(messanger.getManufactureCostInputInvitationMessage());
        setField(reader.readLine(), product::setManufactureCostStr);

        System.out.println(messanger.getUnitOfMeasureInputInvitationMessage());
        setField(reader.readLine(), product::setUnitOfMeasureStr);

        System.out.println(messanger.getPersonNameInputInvitationMessage());
        setField(reader.readLine(), owner::setNameStr);

        System.out.println(messanger.getPersonBirthdayInputInvitationMessage());
        setField(reader.readLine(), owner::setBirthdayStr);

        System.out.println(messanger.getPersonHeightInputInvitationMessage());
        setField(reader.readLine(), owner::setHeightStr);

        System.out.println(messanger.getPersonWeightInputInvitationMessage());
        setField(reader.readLine(), owner::setWeightStr);

        System.out.println(messanger.getPersonPassportIdInputInvitationMessage());
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
