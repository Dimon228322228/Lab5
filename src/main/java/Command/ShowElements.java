package Command;

import Command.CommandFactory.CommandFactory;
import Command.Reader.Reader;
import Content.Product.Product;
import Manager.CollectionManager;
import Messager.Messenger;

import java.util.List;

/**
 * showing all element of the collection
 */
public class ShowElements implements MessagingCommand{
    /**
     * a single method for showing all elements
     */
    @Override
    public void execute(CollectionManager manager, Reader reader, String arg, Messenger messanger, CommandFactory commandFactory) {
        List<Product> productList = manager.showElements();
        for (Product product: productList){
            System.out.println(messanger.getProductMessage(product));
        }
    }
}
