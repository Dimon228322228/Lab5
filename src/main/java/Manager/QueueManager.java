package Manager;

import Content.Product.Product;
import Content.Product.ProductImpl;
import Content.Product.UnitOfMeasure;
import Content.Validator.RealizedValidatorProduct;
import Exception.EmptyFileException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.time.ZonedDateTime;
import java.util.*;
import Exception.ProductNotFoundException;
/**
 * This class stores the collection and works with it
 * */
public class QueueManager implements CollectionManager{
    /**
     * creation date
     */
    private final ZonedDateTime date;
    /**
     * class that works with files
     */
    private final FileManager fileManager;
    /**
     * checks the correctness of the fields
     */
    private final RealizedValidatorProduct validatorProduct = new RealizedValidatorProduct();
    /**
     * path to the file where the collection is stored
     */
    private String filepath = "Test.xml";
    /**
     * the collection
     */
    private PriorityQueue<ProductImpl> collection;
    /**
     * inner static class that generates the id
     */
    private static final GeneratedID generatedID = new GeneratedID();

    public QueueManager(FileManager fileManager){
        this.fileManager = fileManager;
        collection = new PriorityQueue<>();
        this.date = ZonedDateTime.now();
    }

    /**
     * Get id which don't exist in set
     * @return id
     */
    public static long getID() {
        return generatedID.getID();
    }


    /**
     * @return {@link List} with information about collection
     */
    @Override
    public List<String> displayInfo() {
        List<String> infoCollection = new ArrayList<>();
        infoCollection.add(collection.getClass().toString());
        infoCollection.add(String.valueOf(collection.size()));
        infoCollection.add(date.toString());
        return infoCollection;
    }

    /**
     * Checks objects received from a file and saves them to the collection
     */
    public void parseDateFromFile(){
        try{
            //setFilepath();
            Collection<ProductImpl> products = fileManager.getCollectionFromFile(filepath);
            for (ProductImpl product : products){
                if(validatorProduct.validProduct(product)){
                    collection.add(product);
                    generatedID.setID(product.getId());
                } else {
                    System.err.println("Product with " + product + "hasn't been add in the collection");
                }
            }
            System.out.println("Download complete");
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (EmptyFileException e) {
            System.err.println((new EmptyFileException("File is empty!")).getMessage());
        }
    }

    /**
     * get value environment variable and set it to filepath
     */
    private void setFilepath(){
        System.out.println("Enter name environment variable that contains the path of the file: ");
        String nameVariable = "";
        System.getenv().forEach((k, v) ->
                System.out.println(k + " : " + v)
        );
        try {
            Scanner scr = new Scanner(System.in);
            nameVariable = scr.nextLine();
            filepath = System.getenv(nameVariable);
            if (filepath == null || filepath.equals("")) {
                System.out.println("This environment variable not exist.");
                setFilepath();
            }
        } catch (NoSuchElementException | IllegalStateException | SecurityException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return all elements of the collection as a list in ascending order
     */
    @Override
    public List<Product> showElements(){
        return getListProduct();
    }

    /**
     * @return all elements of the collection as a list in ascending order
     */
    private List<Product> getListProduct(){
        List<Product> productList = new ArrayList<>();
        PriorityQueue<ProductImpl> instance = new PriorityQueue<>();
        int size = collection.size();
        for (int i = 0; i < size; i++ ){
            ProductImpl product = collection.poll();
            productList.add(product);
            instance.add(product);
        }
        collection = instance;
        return productList;
    }

    /**
     * add product in the collection
     * @param product is haired of class {@link Product}
     */
    @Override
    public void add(ProductImpl product) {
        collection.add(product);
        generatedID.setID(product.getId());
    }

    /**
     * Updates the value of the collection item by id
     * @param id elements id
     * @param product is hair of class {@link Product}
     */
    @Override
    public void updateId(long id, Product product) {
        boolean flag = false;
        for(Product product1: collection){
            if (product1.getId() == id){
                collection.remove(product1);
                collection.add((ProductImpl) product);
                flag = true;
                break;
            }
        }
        if (!flag) throw new ProductNotFoundException();
    }

    /**
     * Deletes element of the collection by id
     * @param id elements id
     */
    @Override
    public void removeById(long id) {
        boolean flag = false;
        for(Product product: collection) {
            if(product.getId() == id) {
                collection.remove(product);
                generatedID.removeID(product.getId());
                flag = true;
                break;
            }
        }
        if(!flag)
            throw new ProductNotFoundException();

    }

    /**
     * Clear collection (Delete all element of the collection)
     */
    @Override
    public void clear() {
        collection = new PriorityQueue<>();
        generatedID.clearIdSet();
    }

    /**
     * Saves the collection to a file
     */
    @Override
    public void save() {
        if (filepath == null || filepath.equals("")) {
            //setFilepath();
            try {
                fileManager.saveCollectionInXML(collection, filepath);
            } catch (JAXBException | IOException | InvalidPathException | EmptyFileException e){
                e.printStackTrace();
            }
        }else {
            try {
                fileManager.saveCollectionInXML(collection, filepath);
            } catch (JAXBException | IOException | InvalidPathException | EmptyFileException e){
                e.printStackTrace();            }
        }
    }

    /**
     * @return max product
     */
    private Product maxProduct() {
        Product productMax = collection.peek();
        for(Product product: collection) {
            if(product.compareTo(productMax) > 0)
                productMax = product;
        }
        return productMax;
    }

    /**
     * Adds an element in the collection if it is larger than all the elements in the collection
     * @param product is hair of class {@link Product}
     */
    @Override
    public void addIfMax(Product product) {
        if (!collection.isEmpty() && product.compareTo(maxProduct()) > 0) {
            collection.add((ProductImpl) product);
            generatedID.setID(product.getId());
        }
    }

    /**
     * Deletes the smallest then given element from the collection
     * @param product is hair of class {@link Product}
     */
    @Override
    public void removeLower(Product product) {
        List<Product> productList = getListProduct();
        Collections.sort(productList);
        if (productList.size() != 0) {
            for (Product product1: collection){
                if (product1.compareTo(product) < 0){
                    generatedID.removeID(product1.getId());
                    collection.remove(product1);
                }
            }
        } else System.err.println("Nothing to remove! Collection is empty!");
    }

    /**
     * Counts the number of elements with the same manufacture cost
     * @return amount of elements
     */
    @Override
    public long countByManufactureCost(Double manufactureCost) {
        long count = 0;
        for (Product product: collection){
            if (product.getManufactureCost() == manufactureCost) count += 1;
        }
        return count;
    }

    /**
     * Counts the number of elements with value unitOfMeasure grated than given
     * @param unitOfMeasure enum
     * @return amount of elements
     */
    @Override
    public long countGreaterThenUnitOfMeashure(UnitOfMeasure unitOfMeasure) {
        long count = 0;
        for (Product product: collection){
            if (product.getUnitOfMeasure().compareTo(unitOfMeasure) > 0) count += 1;
        }
        return count;
    }

    /**
     * The class that generates an id
     */
    static class GeneratedID {
        /**
         * set existing id
         */
        private Set<Long> idSet = new HashSet<>();

        /**
         * Get id which don't exist in set
         * @return id
         */
        public long getID() {
            long id = 0;
            do{
                id ++;
            } while (idExists(id));
            idSet.add(id);
            return id;
        }

        /**
         * does an id exist in a set
         * @return true if exist
         */
        public boolean idExists(Long id){
            return idSet.contains(id);
        }

        /**
         * Remove an id from the set
         */
        public void removeID(long id) {
            idSet.remove(id);
        }

        public void setID(long id){idSet.add(id);}

        public void clearIdSet(){idSet = new HashSet<>();}
    }

}
