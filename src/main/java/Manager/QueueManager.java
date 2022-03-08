package Manager;

import Content.*;
import Exception.EmptyFileException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.ZoneId;
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
    private String filepath;

    public QueueManager(FileManager fileManager, ObjectFactory productfactory){
        this.fileManager = fileManager;
        ProductFactory = productfactory;
        collection = new PriorityQueue<>();
        this.date = ZonedDateTime.now(ZoneId.of("Moscow"));
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
            Collection<Product> products = fileManager.getCollectionFromFile(filepath);
            for (Product product : products){
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
    }

    private void setFilepath(){
        System.out.println("Enter name environment variable that contains the path of the file: ");
        String nameVariable = "";
        try(Scanner scr = new Scanner(System.in)){
            nameVariable = scr.nextLine();
        } catch (NoSuchElementException | IllegalStateException e){
            System.err.println(e.getMessage());
        }
        filepath = System.getenv(nameVariable);
    }

    @Override
    public List<Product> ShowElements(){
        return getListProduct();
    }

    private List<Product> getListProduct(){
        List<Product> productList = new ArrayList<>();
        PriorityQueue<Product> instance = new PriorityQueue<>();
        int size = collection.size();
        for (int i = 0; i < size; i++ ){
            Product product = collection.poll();
            productList.add(product);
            instance.add(product);
        }
        collection = instance;
        return productList;
    }

    @Override
    public void add(Product product) {
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
        collection = new PriorityQueue<>();
    }

    @Override
    public void save() {
        if (filepath == null || filepath.equals("")) {
            setFilepath();
            try {
                fileManager.SaveCollectionInXML(collection);
            } catch (JAXBException | IOException e){
                System.err.println(e.getMessage());
            }
        }else {
            try {
                fileManager.SaveCollectionInXML(collection);
            } catch (JAXBException | IOException e){
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
        Product product1 = ProductFactory.getProduct(product);
        if (!collection.isEmpty() && product1.compareTo(maxProduct()) > 0) collection.add(product1);
    }

    @Override
    public void RemoveLower(Product product) {
        List<Product> productList = getListProduct();
        Collections.sort(productList);
        if (productList.size() != 0) collection.remove(productList.get(0));
        else System.err.println("Nothing to remove! Collection is empty!");
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
            if (product.getUnitOfMeasure().compare(product.getUnitOfMeasure(), unitOfMeasure) > 0) count += 1;
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

    public void sort(){
        SortedSet<Product> productSet = new TreeSet<>();
        while (!collection.isEmpty()){
            productSet.add(collection.poll());
        }
        collection = new PriorityQueue<>(productSet);
    }
}
