package Command;

import Command.CommandFactory.CommandFactory;
import Command.Reader.Reader;
import Manager.CollectionManager;
import Messager.Messenger;

public class History implements MessagingCommand{
    @Override
    public void execute(CollectionManager manager, Reader reader, String arg, Messenger messanger, CommandFactory commandFactory) {
         System.out.println(commandFactory.getHistory());
    }
}
