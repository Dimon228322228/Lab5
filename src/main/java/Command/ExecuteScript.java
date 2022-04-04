package Command;

import Command.CommandFactory.CommandFactory;
import Command.Reader.FileReader;
import Command.Reader.Reader;
import Manager.CollectionManager;

import java.io.File;
import java.io.FileNotFoundException;

public class  ExecuteScript implements ScriptCommand{
    @Override
    public void execute(CollectionManager manager, Reader reader, String fileName, CommandFactory commandFactory){
        File file;
        try{
            file = new File(fileName);
        } catch (NullPointerException e){
            System.err.println("Name file must be not null.");
            return;
        }
        try{
            Reader fileReader = new FileReader(commandFactory, manager, file);
            fileReader.readCommand();
        } catch (FileNotFoundException e){
            System.err.println("File with name " + fileName + " is not found");
        }
    }
}
