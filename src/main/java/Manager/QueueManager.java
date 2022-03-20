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

public class QueueManager extends AbstractQueueManager{

    /**
     * When collection created
     * */
    private final ZonedDateTime date;
    /** Read file from xml*/
    private final FileManager fileManager;
    /** Create object*/
    private final ObjectFactory ProductFactory;
    private final RealizedValidatorProduct validatorProduct = new RealizedValidatorProduct();
    private String filepath = "C:\\Users\\Гостевой\\Desktop\\Laba5\\Test.xml";

    public QueueManager(FileManager fileManager, ObjectFactory productfactory){
        this.fileManager = fileManager;
        ProductFactory = productfactory;
        collection = new PriorityQueue<>();
        this.date = ZonedDateTime.now();
        createSet();
    }

    @Override
    public List<String> displayInfo() {
        List<String> infoCollection = new ArrayList<>();
        infoCollection.add(collection.getClass().toString());
        infoCollection.add(String.valueOf(collection.size()));
        infoCollection.add(date.toString());
        return infoCollection;
    }

    public void parseDateFromFile(){
        try{
            setFilepath();
            Collection<RealizedProduct> products = fileManager.getCollectionFromFile(filepath);
            for (RealizedProduct product : products){
                if(validatorProduct.validProduct(product)){
                    collection.add(product);
                    idSet.add(product.getId());
                } else {
                    System.err.println("Product with " + product.toString() + "hasn't been add in the collection");
                }
            }

        } catch (JAXBException | IOException e) {
            System.err.println(e.getMessage());
        } catch (EmptyFileException e) {
            System.err.println((new EmptyFileException("File is empty!")).getMessage());
        }
        System.out.println("Download complete");
    }

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

    @Override
    public List<Product> ShowElements(){
        return getListProduct();
    }

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

    @Override
    public void add(RealizedProduct product) {
        collection.add(product);
    }

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

    @Override
    public void clear() {
        collection = new PriorityQueue<RealizedProduct>();
    }

    @Override
    public void save() {
        if (filepath == null || filepath.equals("")) {
            setFilepath();
            try {
                fileManager.SaveCollectionInXML(collection, filepath);
            } catch (JAXBException | IOException | InvalidPathException | EmptyFileException e){
                System.err.println(e.getMessage());
            }
        }else {
            try {
                fileManager.SaveCollectionInXML(collection, filepath);
            } catch (JAXBException | IOException | InvalidPathException | EmptyFileException e){
                System.err.println(e.getMessage());
            }
        }
    }

    private Product maxProduct() {
        Product productMax = collection.peek();
        for(Product product: collection) {
            if(product.compareTo(productMax) > 0)
                productMax = product;
        }
        return productMax;
    }

    @Override
    public void AddIfMax(Product product) {
        RealizedProduct product1 = ProductFactory.getProduct(product);
        if (!collection.isEmpty() && product1.compareTo(maxProduct()) > 0) collection.add(product1);
    }

    @Override
    public void RemoveLower(Product product) {
        List<Product> productList = getListProduct();
        Collections.sort(productList);
        if (productList.size() != 0) {
            for (Product product1: collection){
                if (product1.compareTo(product) < 0){
                    collection.remove(product1);
                    removeID(product1.getId());
                }
            }
        } else System.err.println("Nothing to remove! Collection is empty!");
    }

    @Override
    public long CountByManufactureCost(Double manufactureCost) {
        long count = 0;
        for (Product product: collection){
            if (product.getManufactureCost() == manufactureCost) count += 1;
        }
        return count;
    }

    @Override
    public long CountGreaterThenUnitOfMeashure(UnitOfMeasure unitOfMeasure) {
        long count = 0;
        for (Product product: collection){
            if (product.getUnitOfMeasure().compareTo(unitOfMeasure) > 0) count += 1;
        }
        return count;
    }

    @Override
    public List<Product> PrintAscending() {
        List<Product> productList = getListProduct();
        Collections.sort(productList);
        return productList;
    }

    @Override
    public boolean elementExist(long id) {
        boolean flag = false;
        for(Product product: collection) {
            if(product.getId() == id) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
