package Exception;

import java.util.NoSuchElementException;

public class ProductNotFoundException extends NoSuchElementException {
    public ProductNotFoundException(){
        super("Element with those id not found in the collection!");
    }
}
