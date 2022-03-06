package Command;

import Manager.CollectionManager;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import Exception.UnknownCommandException;

public class RealizedCommandFactory implements CommandFactory{
    private static RealizedCommandFactory instance = null;

    private final Map<String, Command> commands;
    private final RealizedCommandFactory productFactory;
    private final Messenger messenger;
    private static Set<File> files = new HashSet<File>;

    private RealizedCommandFactory(Map<String, Command> commands, RealizedCommandFactory productFactory, Messenger messenger) {
        this.commands = commands;
        this.productFactory = productFactory;
        this.messenger = messenger;
    }

    public static CommandFactory getInstance(Map<String, Command> commands, RealizedCommandFactory productFactory, Messenger messenger){
        if (instance == null) instance = new RealizedCommandFactory(commands, productFactory,messenger);
        return instance;
    }

    private Command getCommand(String commandName) {
        return commands.get(commandName);
    }

    @Override
    public void executeCommand(String commandName, CommandReader commandReader, String arg, CollectionManager collectionManager) {
        Command command;
        try{
            command = getCommand(commandName);
        } catch (Exception e){
            throw new UnknownCommandException();
        }
        if (command instanceof SimpleCommand){
            executeSimpleCommand((SimpleCommand) command, commandReader, arg, collectionManager);
        } else if (command instanceof  Messagingommand){
            executeMessagingCommand((MessagingCommand) command, commandReader, arg, collectionManager);
        } else if (command instanceof ScriptCommand){
            executeScriptCommand((ScriptCommand) command, commandReader, arg, collectionManager);
        } else System.err.println("Error got while executing command!");
    }

    private void executeSimpleCommand(SimpleCommand command, CommandReader commandReader, String arg, CollectionManager collectionManager) {
        command.execute(collectionManager, commandReader, arg);
    }

    private void executeMessagingCommand(MessagingCommand command, CommandReader commandReader, String arg, CollectionManager collectionManager) {
        command.execute(collectionManager, commandReader, arg, messenger);
    }

    private void executeScriptCommand(ScriptCommand command, CommandReader commandReader, String arg, CollectionManager collectionManager) {
        File file = new File(arg);
        if(files.contains(file)) {
            System.err.println("Error. Script recursion has been detected.");
            return;
        }
        files.add(file);
        command.execute(collectionManager, commandReader, arg, this, productFactory);
        files.remove(file);
    }
}
