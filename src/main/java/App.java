import Command.*;
import Command.CommandFactory.CommandFactory;
import Command.CommandFactory.RealizedCommandFactory;
import Command.Reader.ConsoleReader;
import Command.Reader.Reader;
import Manager.FileManager;
import Manager.QueueManager;
import Messager.EnglishMessenger;
import Messager.Messenger;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        FileManager fileManager;
        try {
            fileManager = new FileManager();
        } catch (JAXBException e){
            e.printStackTrace();
            System.out.println(e.getMessage() + " Sorry, programme cannot be started " + e);
            System.err.println(e.getMessage());
            return;
        }
        QueueManager manager = new QueueManager(fileManager);
        Messenger messanger = new EnglishMessenger();
        CommandFactory commandFactory = RealizedCommandFactory.getInstance(getCommands(), messanger);
        Reader reader = new ConsoleReader(commandFactory, manager, messanger);
        manager.parseDateFromFile();
        reader.readCommand();
    }

    public static Map<String, Command> getCommands(){
        HashMap<String, Command> commandHashMap = new HashMap<>();
        commandHashMap.put("help", new Help());
        commandHashMap.put("info", new DisplayInfo());
        commandHashMap.put("show", new ShowElements());
        commandHashMap.put("add", new Add());
        commandHashMap.put("updateId", new UpdateById());
        commandHashMap.put("removeById", new RemoveById());
        commandHashMap.put("clear", new Clear());
        commandHashMap.put("save", new Save());
        commandHashMap.put("executeScript", new ExecuteScript());
        commandHashMap.put("exit", new Exit());
        commandHashMap.put("addIfMax", new AddIfMax());
        commandHashMap.put("removeLower", new RemoveLower());
        commandHashMap.put("history", new History());
        commandHashMap.put("countByManufactureCost", new CountByManufactureCost());
        commandHashMap.put("countGreaterThanUnitOfMeasure", new CountGreaterThenUnitOfMeasure());
        commandHashMap.put("printAscending", new PrintInAscendingOrder());
        return commandHashMap;
    }
}
