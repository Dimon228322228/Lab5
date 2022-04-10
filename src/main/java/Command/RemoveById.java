package Command;

import Command.Reader.Reader;
import Manager.CollectionManager;
import Exception.ProductNotFoundException;

/**
 * removing element by id
 */
public class RemoveById implements SimpleCommand{
    /**
     * read id from console
     * remove element of the collection by id
     */
    @Override
    public void execute(CollectionManager manager, Reader reader, String arg) {
        long id = -2;
        try{
            id = Long.parseLong(arg);
        } catch (NumberFormatException e){
            System.err.println(e.getMessage() + "Id must be long.");
        }
        try {
            manager.removeById(id);
        } catch (ProductNotFoundException e){
            System.err.println(e.getMessage());
        }
    }
}
