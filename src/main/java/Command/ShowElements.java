package Command;

import Command.CommandFactory.CommandFactory;
import Command.Reader.Reader;
import Content.Product.Product;
import Manager.CollectionManager;
import Messager.Messanger;

import java.util.List;

public class ShowElements implements MessagingCommand{
    @Override
    public void execute(CollectionManager manager, Reader reader, String arg, Messanger messanger, CommandFactory commandFactory) {
        List<Product> productList = manager.showElements();
        for (Product product: productList){
            System.out.println(messanger.getProductMessage(product));
        }
    }
}
