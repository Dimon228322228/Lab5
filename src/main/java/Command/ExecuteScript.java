package Command;

import Command.CommandFactory.CommandFactory;
import Command.Reader.Reader;
import Content.ObjectFactory.ObjectFactory;
import Manager.CollectionManager;

import java.io.File;
import java.io.FileNotFoundException;

public class ExecuteScript implements ScriptCommand{
    @Override
    public void execute(CollectionManager manager, Reader reader, String fileName, CommandFactory commandFactory, ObjectFactory objectFactory){
        File file = null;
        try{
            file = new File(fileName);
        } catch (NullPointerException e){
            System.err.println("Name file must be not null.");
            return;
        }
        try{
            Reader fileReader = objectFactory.getFileReader(commandFactory, manager, file);
            fileReader.ReadCommand();
        } catch (FileNotFoundException e){
            System.err.println("File with name " + fileName + " is not found");
        }
        reader.ReadCommand();
    }
}
