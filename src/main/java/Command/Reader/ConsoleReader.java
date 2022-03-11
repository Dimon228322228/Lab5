package Command.Reader;

import Command.CommandFactory.CommandFactory;
import Content.Coordinate.RealizedCoordinates;
import Content.ObjectFactory.ObjectFactory;
import Content.Person.RealizedPerson;
import Content.Product.Product;
import Content.Product.RealizedProduct;
import Manager.CollectionManager;
import Messager.Messanger;
import Exception.InvalidProductFieldException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    public Product readProduct() throws IOException {
        RealizedProduct product = productFactory.getProduct();
        RealizedCoordinates coordinates = productFactory.getCoordinates();
        RealizedPerson owner = productFactory.getPerson();
        System.out.println(messanger.getNameInputInvitationMessage());
        consoleSetName(reader.readLine(), product);
        System.out.println(messanger.getXInputInvitationMessage());
        consoleSetX(reader.readLine(), coordinates);
        System.out.println(messanger.getYInputInvitationMessage());
        consoleSetY(reader.readLine(), coordinates);
        product.setCoordinates(coordinates);
        System.out.println(messanger.getPriceInputInvitationMessage());
        consoleSetPrice(reader.readLine(), product);
        System.out.println(messanger.getPartNumberInputInvitationMessage());
        consoleSetPartNumber(reader.readLine(), product);
        System.out.println(messanger.getManufactureCostInputInvitationMessage());
        consoleSetManufactureCost(reader.readLine(), product);
        System.out.println(messanger.getUnitOfMeasureInputInvitationMessage());
        consoleSetUnitOfMeasure(reader.readLine(), product);
        System.out.println(messanger.getPersonNameInputInvitationMessage());
        consoleSetNamePerson(reader.readLine(), owner);
        System.out.println(messanger.getPersonBirthdayInputInvitationMessage());
        consoleSetBirthdayPerson(reader.readLine(), owner);
        System.out.println(messanger.getPersonHeightInputInvitationMessage());
        consoleSetHeightPerson(reader.readLine(), owner);
        System.out.println(messanger.getPersonWeightInputInvitationMessage());
        consoleSetWeightPerson(reader.readLine(), owner);
        System.out.println(messanger.getPersonPassportIdInputInvitationMessage());
        consoleSetPassportIdPerson(reader.readLine(), owner);
        product.setOwner(owner);
        return product;
    }

    private void consoleSetName(String str, RealizedProduct product) throws IOException {
        try{
            product.setNameStr(str);
        } catch (Exception e) {
            repeatInput(e);
            consoleSetName(reader.readLine(), product);
        }
    }

    private void consoleSetX(String str, RealizedCoordinates coordinates) throws IOException{
        try{
            coordinates.setXStr(str);
        } catch (Exception e) {
            repeatInput(e);
            consoleSetX(reader.readLine(), coordinates);
        }
    }

    private void consoleSetY(String str, RealizedCoordinates coordinates) throws IOException{
        try{
            coordinates.setYStr(str);
        } catch (Exception e) {
            repeatInput(e);
            consoleSetY(reader.readLine(), coordinates);
        }
    }

    private void consoleSetPrice(String str, RealizedProduct product) throws IOException{
        try{
            product.setPriceStr(str);
        } catch (Exception e){
            repeatInput(e);
            consoleSetPrice(reader.readLine(), product);
        }
    }

    private void consoleSetPartNumber(String str, RealizedProduct product) throws IOException{
        try{
            product.setPartNumberStr(str);
        } catch (Exception e){
            repeatInput(e);
            consoleSetPartNumber(reader.readLine(), product);
        }
    }

    private void consoleSetManufactureCost(String str, RealizedProduct product) throws IOException{
        try{
            product.setManufactureCostStr(str);
        } catch (Exception e){
            repeatInput(e);
            consoleSetManufactureCost(reader.readLine(), product);
        }
    }

    private void consoleSetUnitOfMeasure(String str, RealizedProduct product) throws IOException{
        try{
            product.setUnitOfMeasureStr(str);
        } catch (Exception e){
            repeatInput(e);
            consoleSetUnitOfMeasure(reader.readLine(), product);
        }
    }


    private void consoleSetNamePerson(String str, RealizedPerson person) throws IOException{
        try{
            person.setNameStr(str);
        } catch (Exception e){
            repeatInput(e);
            consoleSetNamePerson(reader.readLine(), person);
        }
    }

    private void consoleSetBirthdayPerson(String str, RealizedPerson person) throws IOException{
        try{
            person.setBirthdayStr(str);
        } catch (Exception e){
            repeatInput(e);
            consoleSetBirthdayPerson(reader.readLine(), person);
        }
    }

    private void consoleSetHeightPerson(String str, RealizedPerson person) throws IOException{
        try{
            person.setHeightStr(str);
        } catch (Exception e){
            repeatInput(e);
            consoleSetHeightPerson(reader.readLine(), person);
        }
    }

    private void consoleSetWeightPerson(String str, RealizedPerson person) throws IOException{
        try{
            person.setWeightStr(str);
        } catch (Exception e){
            repeatInput(e);
            consoleSetWeightPerson(reader.readLine(), person);
        }
    }

    private void consoleSetPassportIdPerson(String str, RealizedPerson person) throws IOException{
        try {
            person.setPassportIDStr(str);
        } catch(Exception e){
            repeatInput(e);
            consoleSetPassportIdPerson(reader.readLine(), person);
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
        System.out.println("Got error(check std err). Please, entered the field again: ");
    }

}
