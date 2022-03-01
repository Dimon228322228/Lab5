package Content;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Product implements Comparable<Product>{

    private final long id; // >0 , unique and automatic generated

    private final String name ; // not null and not empty


    private final Coordinates coordinates ; // not null

    private final Date creationDate ; // not null and automatic generation

    private final Double price ; // not null and >0

    private final String partNumber ; // >=22 and not null

    private final double manufactureCost ; //

    private final UnitOfMeasure unitOfMeasure ; // not null

    private final Person owner ; // not null

    public Product(long id, String name, int x, int y, Date creationDate, double price, String partNumber, double manufactureCost, UnitOfMeasure unitOfMeasure, String name1, LocalDateTime birthday, long height, int weight, String passportID){
        this.id = id;
        this.name = name;
        coordinates = new Coordinates(x, y);
        this.creationDate = creationDate;
        this.price = price;
        this.partNumber = partNumber;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        owner = new Person(name1, birthday, height, weight, passportID);
    }


    @Override
    public int compareTo(Product o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Product product)) return false;
        if (this == o) return true;
        return product.getId() == this.getId();
    }

    @Override
    public int hashCode(){
        return (int) this.getId();
    }

    @Override
    public String toString(){
        return String.format("ID = %d, Name = %s, %s, Creation Date = %s, Price = %f, PartNumber = %s, Manufacture cost = %f, Unit of Measurement = %s, %s",
                id, name, coordinates, creationDate, price, partNumber, manufactureCost, unitOfMeasure, owner);
    }

    public List<Object> getAllField(){
        return Arrays.asList(id, name, coordinates.getX(), coordinates.getY(), creationDate, price, partNumber, manufactureCost,
                unitOfMeasure, owner.getName(), owner.getBirthday(), owner.getHeight(), owner.getWeight(), owner.getPassportID());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Double getPrice() {
        return price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public double getManufactureCost() {
        return manufactureCost;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public Person getOwner() {
        return owner;
    }
}
