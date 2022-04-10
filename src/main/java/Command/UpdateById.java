package Command;

import Command.Reader.Reader;
import Content.Product.Product;
import Manager.CollectionManager;
import Exception.InvalidProductFieldException;
import Exception.ProductNotFoundException;

import java.io.IOException;

/**
 * update element of the collection by id
 */
public class UpdateById implements SimpleCommand{
    /**
     * checked exist given id
     * read product from console
     * set new product by given id
     * old product has deleted
     */
    @Override
    public void execute(CollectionManager manager, Reader reader, String arg) {
        long id = -2;
        try{
            id = Long.parseLong(arg);
        } catch (NumberFormatException e){
            System.err.println("Id must be long.");
        }
        Product product;
        try{
            product = reader.readProduct();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        } catch (Exception e){
            System.err.println(new InvalidProductFieldException().getMessage());
            return;
        }
        if (product == null){
            return;
        }
        try{
            manager.updateId(id, product);
        } catch (ProductNotFoundException e){
            System.err.println(e.getMessage());
        }

    }
}
