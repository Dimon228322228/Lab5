package Command;

import Command.Reader.Reader;
import Manager.CollectionManager;

public class Exit implements SimpleCommand{
    @Override
    public void execute(CollectionManager manager, Reader reader, String arg) {
        reader.exit();
    }
}
