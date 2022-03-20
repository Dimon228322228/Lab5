package Command.Reader;

import Content.Product.RealizedProduct;

import java.io.IOException;

public interface Reader {
    void ReadCommand();
    void exit();
    RealizedProduct readProduct() throws IOException;
}
