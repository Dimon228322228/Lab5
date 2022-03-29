package Command;

import Command.CommandFactory.CommandFactory;
import Command.Reader.Reader;
import Manager.CollectionManager;

public interface ScriptCommand extends Command{
    void execute(CollectionManager manager, Reader reader, String arg, CommandFactory commandFactory);
}
