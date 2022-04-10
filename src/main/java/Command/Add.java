package Command;

import Command.Reader.Reader;
import Content.Product.ProductImpl;
import Manager.CollectionManager;

import java.io.IOException;

/**
 * add element in the collection
 */
public class Add implements SimpleCommand{
    /**
     * add product in the collection
     */
    @Override
    public void execute(CollectionManager manager, Reader reader, String arg) {
        ProductImpl product = null;
        try{
            product = reader.readProduct();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        if (product == null) return;
        manager.add(product);
        System.out.println("Product has been added successful.");
    }
}
