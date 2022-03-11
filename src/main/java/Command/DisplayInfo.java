package Command;

import Command.CommandFactory.CommandFactory;
import Command.Reader.Reader;
import Manager.CollectionManager;
import Messager.Messanger;

import java.util.List;

public class DisplayInfo implements MessagingCommand {
    @Override
    public void execute(CollectionManager manager, Reader reader, String arg, Messanger messanger, CommandFactory commandFactory) {
        List<String> info = manager.displayInfo();
        System.out.println(messanger.getCollectionMessage(info.get(0), info.get(1), info.get(2)));
    }
}
