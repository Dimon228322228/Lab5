package Command;

import Command.CommandFactory.CommandFactory;
import Command.Reader.Reader;
import Manager.CollectionManager;

/**
 * a general interface for describing executing command
 */
public interface ScriptCommand extends Command{
    void execute(CollectionManager manager, Reader reader, String arg, CommandFactory commandFactory);
}
