package Manager;

import Content.Product.RealizedProduct;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public abstract class AbstractQueueManager implements CollectionManager{
    protected static Set<Long> idSet;

    protected PriorityQueue<RealizedProduct> collection;


    /**
     * Get id which don't exist in set
     * @return id
     */
    public static long getID() {
        long id = 0;
        do{
            id ++;
        } while (idExists(id));
        idSet.add(id);
        return id;
    }

    /**
     * does an id exist in a set
     * @param id
     * @return boolean
     */
    public static boolean idExists(Long id){
        return idSet.contains(id);
    }

    /**
     * Remove an id from the set
     * @param id
     */
    public static void removeID(long id) {
        idSet.remove(id);
    }

    /**
     * Created set of id
     */
    public static void createSet(){
        idSet = new HashSet<>();
    }
}
