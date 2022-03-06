package Command;

import Content.Product;

import java.io.IOException;

public interface CommandReader {
    void ReadCommand();
    void exit();
    Product readProduct() throws IOException;
}
