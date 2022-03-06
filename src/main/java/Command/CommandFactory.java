package Command;

import Manager.CollectionManager;

public interface CommandFactory {
    void executeCommand(String command, CommandReader commandReader, String arg, CollectionManager collectionManager);
}
