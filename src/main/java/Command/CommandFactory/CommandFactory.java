package Command.CommandFactory;

import Command.Reader.Reader;
import Manager.CollectionManager;

public interface CommandFactory {
    void executeCommand(String command, Reader reader, String arg, CollectionManager collectionManager);
    String getHistory();
}
