package Command;

import Command.CommandFactory.CommandFactory;
import Command.Reader.Reader;
import Content.Product.Product;
import Manager.CollectionManager;
import Messager.Messenger;

import java.util.List;

/**
 * output all element of the collection in ascending order
 */
public class PrintInAscendingOrder implements MessagingCommand{
    /**
     * a single method for output all element of the collection by ascending order
     */
    @Override
    public void execute(CollectionManager manager, Reader reader, String arg, Messenger messanger, CommandFactory commandFactory) {
        List<Product> products = manager.showElements();
        for (Product product: products){
            System.out.println(messanger.getProductMessage(product));
        }
    }
}
