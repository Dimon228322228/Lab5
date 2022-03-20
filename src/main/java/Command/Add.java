package Command;

import Command.Reader.Reader;
import Content.Product.RealizedProduct;
import Manager.CollectionManager;

import java.io.IOException;

public class Add implements SimpleCommand{
    @Override
    public void execute(CollectionManager manager, Reader reader, String arg) {
        RealizedProduct product = null;
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
