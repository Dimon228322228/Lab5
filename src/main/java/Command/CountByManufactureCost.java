package Command;

import Command.CommandFactory.CommandFactory;
import Command.Reader.Reader;
import Manager.CollectionManager;
import Messager.Messenger;

public class CountByManufactureCost implements MessagingCommand{
    @Override
    public void execute(CollectionManager manager, Reader reader, String arg, Messenger messanger, CommandFactory commandFactory) {
        Double manufactureCost = null;
        try{
            manufactureCost = Double.parseDouble(arg);
        } catch (NumberFormatException e){
            System.err.println("Manufacture cost must be convert to double.");
        }
        long count = manager.countByManufactureCost(manufactureCost);
        System.out.println(messanger.getCountElementWithCondition(count));
    }
}
