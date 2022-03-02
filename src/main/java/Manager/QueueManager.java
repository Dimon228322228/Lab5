package Manager;

import Content.ObjectFactory;
import Content.Product;
import Content.UnitOfMeasure;

import java.time.LocalDateTime;
import java.util.List;
import java.util.PriorityQueue;

public class QueueManager extends AbstractQueueManager{
    private LocalDateTime localDateTime;
    private  FileManager fileManager;
    private ObjectFactory ProductFactory;

    public QueueManager(FileManager fileManager, ObjectFactory productfactory){
        this.fileManager = fileManager;
        ProductFactory = productfactory;
        collection = new PriorityQueue<>();
    }

    @Override
    public List<String> displayInfo() {
        return null;
    }

    @Override
    public void ShowElement() {

    }

    @Override
    public void add(Product product) {

    }

    @Override
    public void updateId(Integer id, Product product) {

    }

    @Override
    public void RemoveById(Integer id) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void save() {

    }

    @Override
    public void AddIfMax(Product product) {

    }

    @Override
    public void RemoveLower(Product product) {

    }

    @Override
    public void CounByManufactureCost(Double manufactureCost) {

    }

    @Override
    public void CountGreaterThenUnitOfMeashure(UnitOfMeasure unitOfMeasure) {

    }

    @Override
    public void PrintAscending() {

    }
}
