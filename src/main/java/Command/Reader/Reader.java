package Command.Reader;

import Content.Product.ProductImpl;

import java.io.IOException;


/**
 * an interface which read command from console
 */
public interface Reader {
    /**
     * reads commands in console mode
     */
    void readCommand();

    /**
     *  finishes waiting for commands to be entered
     */
    void exit();

    /**
     * @return entire product with filled out field
     * @throws IOException if IO exception occurred
     */
    ProductImpl readProduct() throws IOException;
}
