package Command;

import Manager.CollectionManager;

public interface SimpleCommand extends Command{
    void execute(CollectionManager manager, CommandReader reader, String arg);
}
