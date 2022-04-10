package Command.CommandFactory;

import Command.Command;
import Command.Reader.Reader;
import Manager.CollectionManager;
import Command.ScriptCommand;
import Command.MessagingCommand;
import Command.SimpleCommand;

import java.io.File;
import java.util.*;

import Exception.UnknownCommandException;
import Messager.Messenger;

/**
 * A class determines what type of command was entered and stores the command history
 */
public class RealizedCommandFactory implements CommandFactory {
    private static RealizedCommandFactory instance = null;
    private List<String> historyCommand = new ArrayList<>();
    private final Map<String, Command> commands;
    private final Messenger messenger;
    private final static Set<File> files = new HashSet<>();

    private RealizedCommandFactory(Map<String, Command> commands, Messenger messenger) {
        this.commands = commands;
        this.messenger = messenger;
    }

    /**
     * @return single object this class (there must be one instance)
     */
    public static CommandFactory getInstance(Map<String, Command> commands, Messenger messenger){
        if (instance == null) instance = new RealizedCommandFactory(commands, messenger);
        return instance;
    }

    /**
     * @return command from string
     */
    private Command getCommand(String commandName) {
        return commands.get(commandName);
    }

    /**
     * receives command form string, checked that it is correct
     * add given command in history if it is correct
     * calls a method to execute a certain type of command
     */
    @Override
    public void executeCommand(String commandName, Reader reader, String arg, CollectionManager collectionManager) {
        Command command;
        if (commandName.equals("") || commands.get(commandName) == null){
            System.err.println((new UnknownCommandException()).getMessage());
            return;
        }
        addCommandInHistory(commandName);
        command = getCommand(commandName);
        if (command instanceof SimpleCommand){
            executeSimpleCommand((SimpleCommand) command, reader, arg, collectionManager);
        } else if (command instanceof MessagingCommand){
            executeMessagingCommand((MessagingCommand) command, reader, arg, collectionManager);
        } else if (command instanceof ScriptCommand){
            executeScriptCommand((ScriptCommand) command, reader, arg, collectionManager);
        } else System.err.println("Error got while executing command!");
    }

    /**
     * calls method execute at certain simple command
     */
    private void executeSimpleCommand(SimpleCommand command, Reader reader, String arg, CollectionManager collectionManager) {
        command.execute(collectionManager, reader, arg);
    }

    /**
     * calls method execute at certain messaging command
     */
    private void executeMessagingCommand(MessagingCommand command, Reader reader, String arg, CollectionManager collectionManager) {
        command.execute(collectionManager, reader, arg, messenger, this);
    }

    /**
     * try creates object file by given name and here checked recursion
     * if file starts execution and not finished, but it again starts execution -> gives recursion
     */
    private void executeScriptCommand(ScriptCommand command, Reader reader, String arg, CollectionManager collectionManager) {
        File file = null;
        try {
            file = new File(arg);
        } catch (NullPointerException ignored) {
           System.err.println("No such this file.");
        }
        if(files.contains(file)) {
            System.err.println("Error. Script recursion has been detected.");
            return;
        }
        files.add(file);
        command.execute(collectionManager, reader, arg, this);
        files.remove(file);
    }

    /**
     * add command in history
     * if history is larger than 13, the first one added will be deleted
     */
    private void addCommandInHistory(String command){
        historyCommand.add(command);
        if (historyCommand.size() > 13){
            List<String> newListCommand = new ArrayList<>();
            for (int i = 1; i < historyCommand.size(); i++) newListCommand.add(historyCommand.get(i));
            historyCommand = new ArrayList<>(newListCommand);
        }
    }

    /**
     * @return history command as column in console
     */
    public String getHistory(){
        StringBuilder resBuilder = new StringBuilder();
        for (int i = 0; i < historyCommand.size() - 1; i++) resBuilder.append(historyCommand.get(i)).append("\n");
        String res = resBuilder.toString();
        res += historyCommand.get(historyCommand.size() - 1);
        return res;
    }
}
