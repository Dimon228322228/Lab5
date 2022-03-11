package Command;

import Command.Reader.Reader;
import Manager.CollectionManager;

public interface SimpleCommand extends Command{
    void execute(CollectionManager manager, Reader reader, String arg);
}
