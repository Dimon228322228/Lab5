package Command.Reader;

import Content.Product.Product;

import java.io.IOException;

public interface Reader {
    void ReadCommand();
    void exit();
    Product readProduct() throws IOException;
}
