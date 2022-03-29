package Command;

import Command.Reader.Reader;
import Content.Product.Product;
import Manager.CollectionManager;

import java.io.IOException;

public class RemoveLower implements SimpleCommand{
    @Override
    public void execute(CollectionManager manager, Reader reader, String arg) {
        Product product;
        try{
            product = reader.readProduct();
        } catch (IOException e){
            System.err.println(e.getMessage());
            return;
        }
        if (product == null) return;
        manager.removeLower(product);
    }
}
