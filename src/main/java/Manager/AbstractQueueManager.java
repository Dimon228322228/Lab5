package Manager;

import Content.Product;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public abstract class AbstractQueueManager implements CollectionManager{
    protected static Set<Long> idSet;

    protected PriorityQueue<Product> collection;


    public static long getID() {
        long id = -1;
        do{
            id ++;
        } while (idExists(id));
        idSet.add(id);
        return id;
    }

    public static boolean idExists(Long id){
        return idSet.contains(id);
    }

    public static void removeID(long id) {
        idSet.remove(id);
    }

    public abstract boolean elementExist(long id);

    public static void createSet(){
        idSet = new HashSet<Long>();
    }
}
