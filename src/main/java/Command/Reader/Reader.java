package Command.Reader;

import Content.Product.ProductImpl;

import java.io.IOException;

public interface Reader {
    void readCommand();
    void exit();
    ProductImpl readProduct() throws IOException;
}
