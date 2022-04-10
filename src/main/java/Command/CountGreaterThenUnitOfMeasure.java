package Command;

import Command.CommandFactory.CommandFactory;
import Command.Reader.Reader;
import Content.Product.UnitOfMeasure;
import Manager.CollectionManager;
import Messager.Messenger;

/**
 * counts the number of elements large in units
 */
public class CountGreaterThenUnitOfMeasure implements MessagingCommand{
    /**
     * read unit product
     * then counts and print number
     */
    @Override
    public void execute(CollectionManager manager, Reader reader, String arg, Messenger messanger, CommandFactory commandFactory) {
        UnitOfMeasure unitOfMeasure;
        try{
            unitOfMeasure = UnitOfMeasure.fromString(arg);
        } catch (IllegalArgumentException e){
            System.err.println("No such this field in enum.");
            return;
        } catch (NullPointerException e){
            System.err.println(e.getMessage());
            return;
        }
        long count = manager.countGreaterThenUnitOfMeashure(unitOfMeasure);
        System.out.println(messanger.getCountElementWithCondition(count));
    }
}
