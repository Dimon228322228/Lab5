package Command;

import Command.CommandFactory.CommandFactory;
import Command.Reader.Reader;
import Content.Product.UnitOfMeasure;
import Manager.CollectionManager;
import Messager.Messanger;

public class CountGreaterThenUnitOfMeasure implements MessagingCommand{
    @Override
    public void execute(CollectionManager manager, Reader reader, String arg, Messanger messanger, CommandFactory commandFactory) {
        UnitOfMeasure unitOfMeasure;
        try{
            unitOfMeasure = UnitOfMeasure.valueOf(arg);
        } catch (IllegalArgumentException e){
            System.err.println("No such this field in enum.");
            return;
        } catch (NullPointerException e){
            System.err.println(e.getMessage());
            return;
        }
        long count = manager.CountGreaterThenUnitOfMeashure(unitOfMeasure);
        System.out.println(messanger.getCountElementWithCondition(count));
    }
}
