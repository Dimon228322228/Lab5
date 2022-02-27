import Content.Product;

import java.util.PriorityQueue;
import java.util.Queue;

public class Collection {
    private final Queue<Product> collection = new PriorityQueue<>();

    public void add(Product product){
        collection.add(product);
    }
}
