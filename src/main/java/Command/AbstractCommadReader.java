package Command;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class AbstractCommadReader implements CommandReader {
    protected BufferedReader reader;
    private boolean isRunning;

    @Override
    public void ReadCommand() {
        isRunning = true;
        while (isRunning) {
            try {
                if (readyInput()) {
                    String[] command = reader.readLine().trim().split("[ ]+");

                } else isRunning = false;
            } catch (Exception e) {
                if (e.getMessage() != null) {
                    System.err.println(e.getMessage());
                } else {
                    System.err.println("Error got while getting command");
                }
            }
        }
    }

    protected abstract boolean readyInput() throws IOException;

    public void exit(){
        isRunning = false;
    }
}