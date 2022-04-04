package Command;

import Command.CommandFactory.CommandFactory;
import Command.Reader.Reader;
import Manager.CollectionManager;
import Messager.Messenger;

public interface MessagingCommand extends Command{
    void execute(CollectionManager manager, Reader reader, String arg, Messenger messanger, CommandFactory commandFactory);
}
