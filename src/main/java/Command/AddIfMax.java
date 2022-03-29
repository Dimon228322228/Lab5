package Command;

import Command.Reader.Reader;
import Content.Product.Product;
import Manager.CollectionManager;

import java.io.IOException;

public class AddIfMax implements SimpleCommand{
    @Override
    public void execute(CollectionManager manager, Reader reader, String arg) {
        Product product = null;
        try {
            product = reader.readProduct();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        if (product == null) return;
        manager.addIfMax(product);
    }
}
