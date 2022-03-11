package Command;

import Command.CommandFactory.CommandFactory;
import Command.Reader.Reader;
import Manager.CollectionManager;
import Messager.Messanger;

public class Help implements MessagingCommand {
    @Override
    public void execute(CollectionManager manager, Reader reader, String arg, Messanger messanger, CommandFactory commandFactory) {
        System.out.println(messanger.getCommandsMessage());
    }
}
