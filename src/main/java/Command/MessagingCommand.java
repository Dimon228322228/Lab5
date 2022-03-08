package Command;

import Manager.CollectionManager;
import Message.Messanger;

public interface MessagingCommand extends Command{
    void execute(CollectionManager manager, CommandReader reader, String arg, Messanger messanger);
}
