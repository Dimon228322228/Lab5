package Command;

import Command.Reader.Reader;
import Manager.CollectionManager;

public class Save implements SimpleCommand{
    @Override
    public void execute(CollectionManager manager, Reader reader, String arg) {
        manager.save();
    }
}
