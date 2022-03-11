package Command.CommandFactory;

import Command.Command;
import Command.Reader.Reader;
import Manager.CollectionManager;

import java.util.List;

public interface CommandFactory {
    void executeCommand(String command, Reader reader, String arg, CollectionManager collectionManager);
    String getHistory();
}
