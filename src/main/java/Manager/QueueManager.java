package Manager;

import Content.ObjectFactory.ObjectFactory;
import Content.Product.Product;
import Content.Product.RealizedProduct;
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
public class QueueManager extends AbstractQueueManager{


    private final ZonedDateTime date;
    private final FileManager fileManager;
    private final ObjectFactory ProductFactory;
    private final RealizedValidatorProduct validatorProduct = new RealizedValidatorProduct();
    private String filepath = "Test.xml";

    public QueueManager(FileManager fileManager, ObjectFactory productfactory){
        this.fileManager = fileManager;
        ProductFactory = productfactory;
        collection = new PriorityQueue<>();
        this.date = ZonedDateTime.now();
        createSet();
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
            Collection<RealizedProduct> products = fileManager.getCollectionFromFile(filepath);
            for (RealizedProduct product : products){
                if(validatorProduct.validProduct(product)){
                    collection.add(product);
                    idSet.add(product.getId());
                } else {
                    System.err.println("Product with " + product + "hasn't been add in the collection");
                }
            }
            System.out.println("Download complete");
        } catch (JAXBException | IOException e) {
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
        boolean isNull = true;
        while(isNull) {
            System.getenv().forEach((k, v) ->
                    System.out.println(k + " : " + v)
            );
            try (Scanner scr = new Scanner(System.in)) {
                nameVariable = scr.nextLine();
            } catch (NoSuchElementException | IllegalStateException e) {
                System.err.println(e.getMessage());
            }
            try {
                filepath = System.getenv(nameVariable);
            } catch (SecurityException | NullPointerException e) {
                System.err.println(e.getMessage());
            }
            if (filepath != null) isNull = false;
            else System.out.println("This environment variable not exist. Try input one more times: ");
        }
    }

    /**
     * @return all elements of the collection as a list in ascending order
     */
    @Override
    public List<Product> ShowElements(){
        return getListProduct();
    }

    /**
     * @return all elements of the collection as a list in ascending order
     */
    private List<Product> getListProduct(){
        List<Product> productList = new ArrayList<>();
        PriorityQueue<RealizedProduct> instance = new PriorityQueue<>();
        int size = collection.size();
        for (int i = 0; i < size; i++ ){
            RealizedProduct product = collection.poll();
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
    public void add(RealizedProduct product) {
        collection.add(product);
        idSet.add(product.getId());
    }

    /**
     * Updates the value of the collection item by id
     * @param id elements id
     * @param product is hair of class {@link Product}
     */
    @Override
    public void updateId(long id, Product product) {
        boolean flag = false;
        RealizedProduct product1 = ProductFactory.getProduct(id, product);
        for(Product product2: collection){
            if (product2.getId() == id){
                collection.remove(product2);
                collection.add(product1);
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
    public void RemoveById(long id) {
        boolean flag = false;
        for(Product product: collection) {
            if(product.getId() == id) {
                collection.remove(product);
                removeID(product.getId());
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
        idSet = new HashSet<>();
    }

    /**
     * Saves the collection to a file
     */
    @Override
    public void save() {
        if (filepath == null || filepath.equals("")) {
            //setFilepath();
            try {
                fileManager.SaveCollectionInXML(collection, filepath);
            } catch (JAXBException | IOException | InvalidPathException | EmptyFileException e){
                e.printStackTrace();
            }
        }else {
            try {
                fileManager.SaveCollectionInXML(collection, filepath);
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
     * Adds a element in the collection if it is larger than all the elements in the collection
     * @param product is hair of class {@link Product}
     */
    @Override
    public void AddIfMax(Product product) {
        RealizedProduct product1 = ProductFactory.getProduct(product);
        if (!collection.isEmpty() && product1.compareTo(maxProduct()) > 0) {
            collection.add(product1);
            idSet.add(product1.getId());
        }
    }

    /**
     * Deletes the smallest element in the collection
     * @param product is hair of class {@link Product}
     */
    @Override
    public void RemoveLower(Product product) {
        List<Product> productList = getListProduct();
        Collections.sort(productList);
        if (productList.size() != 0) {
            for (Product product1: collection){
                if (product1.compareTo(product) < 0){
                    removeID(product1.getId());
                    collection.remove(product1);
                }
            }
        } else System.err.println("Nothing to remove! Collection is empty!");
    }

    /**
     * Counts the number of elements with the same manufacture cost
     * @param manufactureCost
     * @return amount of elements
     */
    @Override
    public long CountByManufactureCost(Double manufactureCost) {
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
    public long CountGreaterThenUnitOfMeashure(UnitOfMeasure unitOfMeasure) {
        long count = 0;
        for (Product product: collection){
            if (product.getUnitOfMeasure().compareTo(unitOfMeasure) > 0) count += 1;
        }
        return count;
    }

    /**
     * @return all elements of the collection as a list in ascending order
     */
    @Override
    public List<Product> PrintAscending() {
        List<Product> productList = getListProduct();
        Collections.sort(productList);
        return productList;
    }

}
