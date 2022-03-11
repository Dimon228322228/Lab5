package Command.Reader;

import Command.CommandFactory.CommandFactory;
import Content.ObjectFactory.ObjectFactory;
import Manager.CollectionManager;
import Messager.Messanger;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class AbstractReader implements Reader {
    protected BufferedReader reader;
    private boolean isRunning;
    protected CommandFactory commandFactory;
    protected CollectionManager manager;
    protected ObjectFactory productFactory;
    protected Messanger messanger;

    @Override
    public void ReadCommand() {
        isRunning = true;
        while (isRunning) {
            try {
                if (readyInput()) {
                    String[] command = reader.readLine().trim().split("[ ]+");
                    if (command.length == 1)
                        commandFactory.executeCommand(command[0], this, null, manager);
                    else
                        commandFactory.executeCommand(command[0], this, command[1], manager);
                } else isRunning = false;
            } catch (Exception e) {
                if (e.getMessage() != null) {
                    System.err.println(e.getMessage());
                } else {
                    System.err.println("Error got while getting command");
                }
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("An IOException occurred");
        } finally {
            isRunning = false;
        }
    }

    protected abstract boolean readyInput() throws IOException;

    public void exit(){
        isRunning = false;
    }
}