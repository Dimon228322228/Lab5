import Command.*;
import Command.CommandFactory.CommandFactory;
import Command.CommandFactory.RealizedCommandFactory;
import Command.Reader.ConsoleReader;
import Command.Reader.Reader;
import Content.ObjectFactory.RealizedObjectFactory;
import Manager.FileManager;
import Manager.QueueManager;
import Messager.EnglishMessager;
import Messager.Messanger;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        FileManager fileManager;
        try {
            fileManager = new FileManager();
        } catch (JAXBException e){
            System.out.println(e.getMessage() + " Sorry, programme cannot be started");
            System.err.println(e.getMessage());
            return;
        }
        RealizedObjectFactory objectFactory = new RealizedObjectFactory();
        QueueManager manager = new QueueManager(fileManager, objectFactory);
        Messanger messanger = new EnglishMessager();
        CommandFactory commandFactory = RealizedCommandFactory.getInstance(getCommands(), objectFactory, messanger);
        Reader reader = new ConsoleReader(commandFactory, manager, objectFactory, messanger);
        manager.parseDateFromFile();
        reader.ReadCommand();
    }

    public static Map<String, Command> getCommands(){
        HashMap<String, Command> commandHashMap = new HashMap<>();
        commandHashMap.put("help", new Help());
        commandHashMap.put("info", new DisplayInfo());
        commandHashMap.put("show", new ShowElements());
        commandHashMap.put("add", new Add());
        commandHashMap.put("update_id", new UpdateById());
        commandHashMap.put("remove_by_id", new RemoveById());
        commandHashMap.put("clear", new Clear());
        commandHashMap.put("save", new Save());
        commandHashMap.put("execute_script", new ExecuteScript());
        commandHashMap.put("exit", new Exit());
        commandHashMap.put("add_if_max", new AddIfMax());
        commandHashMap.put("remove_lower", new RemoveLower());
        commandHashMap.put("history", new History());
        commandHashMap.put("count_by_manufacture_cost", new CountByManufactureCost());
        commandHashMap.put("count_greater_than_unit_of_measure", new CountGreaterThenUnitOfMeasure());
        commandHashMap.put("print_ascending", new PrintInAscendingOrder());
        return commandHashMap;
    }
}
