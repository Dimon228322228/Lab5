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
import Messager.Messanger;

public class RealizedCommandFactory implements CommandFactory {
    private static RealizedCommandFactory instance = null;
    private List<String> historyCommand = new ArrayList<>();
    private final Map<String, Command> commands;
    private final Messanger messanger;
    private static Set<File> files = new HashSet<>();

    private RealizedCommandFactory(Map<String, Command> commands, Messanger messanger) {
        this.commands = commands;
        this.messanger = messanger;
    }

    public static CommandFactory getInstance(Map<String, Command> commands, Messanger messenger){
        if (instance == null) instance = new RealizedCommandFactory(commands, messenger);
        return instance;
    }

    private Command getCommand(String commandName) {
        return commands.get(commandName);
    }

    @Override
    public void executeCommand(String commandName, Reader reader, String arg, CollectionManager collectionManager) {
        Command command;
        addCommandInHistory(commandName);
        try{
            command = getCommand(commandName);
        } catch (Exception e){
            throw new UnknownCommandException();
        }
        if (command instanceof SimpleCommand){
            executeSimpleCommand((SimpleCommand) command, reader, arg, collectionManager);
        } else if (command instanceof MessagingCommand){
            executeMessagingCommand((MessagingCommand) command, reader, arg, collectionManager);
        } else if (command instanceof ScriptCommand){
            executeScriptCommand((ScriptCommand) command, reader, arg, collectionManager);
        } else System.err.println("Error got while executing command!");
    }

    private void executeSimpleCommand(SimpleCommand command, Reader reader, String arg, CollectionManager collectionManager) {
        command.execute(collectionManager, reader, arg);
    }

    private void executeMessagingCommand(MessagingCommand command, Reader reader, String arg, CollectionManager collectionManager) {
        command.execute(collectionManager, reader, arg, messanger, this);
    }

    private void executeScriptCommand(ScriptCommand command, Reader reader, String arg, CollectionManager collectionManager) {
        File file = new File(arg);
        if(files.contains(file)) {
            System.err.println("Error. Script recursion has been detected.");
            return;
        }
        files.add(file);
        command.execute(collectionManager, reader, arg, this);
        files.remove(file);
    }

    private void addCommandInHistory(String command){
        if (!command.equals("")) historyCommand.add(command);
        if (historyCommand.size() > 13){
            List<String> newListCommand = new ArrayList<>();
            for (int i = 1; i < historyCommand.size(); i++) newListCommand.add(historyCommand.get(i));
            historyCommand = new ArrayList<>(newListCommand);
        }
    }

    public String getHistory(){
        StringBuilder resBuilder = new StringBuilder();
        for (int i = 0; i < historyCommand.size() - 1; i++) resBuilder.append(historyCommand.get(i)).append("\n");
        String res = resBuilder.toString();
        res += historyCommand.get(historyCommand.size() - 1);
        return res;
    }
}
