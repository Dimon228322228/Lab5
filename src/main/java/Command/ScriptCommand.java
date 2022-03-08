package Command;

import Content.ObjectFactory;
import Manager.CollectionManager;

public interface ScriptCommand extends Command{
    void execute(CollectionManager manager, CommandReader reader, String arg, CommandFactory commandFactory, ObjectFactory objectFactory);
}
