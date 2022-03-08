package Command;

import Content.RealizedObjectFactory;
import Manager.CollectionManager;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import Exception.UnknownCommandException;
import Message.Messanger;

public class RealizedCommandFactory implements CommandFactory{
    private static RealizedCommandFactory instance = null;

    private final Map<String, Command> commands;
    private final RealizedObjectFactory productFactory;
    private final Messanger messanger;
    private static Set<File> files = new HashSet<>();

    private RealizedCommandFactory(Map<String, Command> commands, RealizedObjectFactory productFactory, Messanger messanger) {
        this.commands = commands;
        this.productFactory = productFactory;
        this.messanger = messanger;
    }

    public static CommandFactory getInstance(Map<String, Command> commands, RealizedObjectFactory productFactory, Messanger messenger){
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
        } else if (command instanceof  MessagingCommand){
            executeMessagingCommand((MessagingCommand) command, commandReader, arg, collectionManager);
        } else if (command instanceof ScriptCommand){
            executeScriptCommand((ScriptCommand) command, commandReader, arg, collectionManager);
        } else System.err.println("Error got while executing command!");
    }

    private void executeSimpleCommand(SimpleCommand command, CommandReader commandReader, String arg, CollectionManager collectionManager) {
        command.execute(collectionManager, commandReader, arg);
    }

    private void executeMessagingCommand(MessagingCommand command, CommandReader commandReader, String arg, CollectionManager collectionManager) {
        command.execute(collectionManager, commandReader, arg, messanger);
    }

    private void executeScriptCommand(ScriptCommand command, CommandReader commandReader, String arg, CollectionManager collectionManager) {
        File file = new File(arg);
        if(files.contains(file)) {
            System.err.println("Error. Script recursion has been detected.");
            return;
        }
        files.add(file);
        files.remove(file);
        command.execute(collectionManager, commandReader, arg, this, productFactory);
    }
}
