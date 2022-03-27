package Command;

import Command.CommandFactory.CommandFactory;
import Command.Reader.Reader;
import Content.Product.Product;
import Manager.CollectionManager;
import Messager.Messanger;

import java.util.List;

public class PrintInAscendingOrder implements MessagingCommand{
    @Override
    public void execute(CollectionManager manager, Reader reader, String arg, Messanger messanger, CommandFactory commandFactory) {
        List<Product> products = manager.ShowElements();
        for (Product product: products){
            System.out.println(messanger.getProductMessage(product));
        }
    }
}
