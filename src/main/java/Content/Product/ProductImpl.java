package Content.Product;

import Content.Caster.CasterFieldProductFromString;
import Content.Coordinate.*;
import Content.Person.Person;
import Content.Person.PersonImpl;
import Manager.QueueManager;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;

/**
 * a class of product, added setters from the string format compared to the interface
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImpl implements Product, Serializable {

    private long id; // >0 , unique and automatic generated

    private String name ; // not null and not empty

    private CoordinatesImpl coordinates ; // not null

    @XmlJavaTypeAdapter(value = DateSerializer.class)
    private Date creationDate ; // not null and automatic generation

    private Double price ; // not null and >0

    private String partNumber ; // >=22 and not null

    private double manufactureCost ; //

    private UnitOfMeasure unitOfMeasure ; // not null

    private PersonImpl owner ; // not null

    /**
     * creates fields of different classes and checks their correctness
     */
    @XmlTransient
    private final CasterFieldProductFromString casterFieldProductFromString = new CasterFieldProductFromString();

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CoordinatesImpl getCoordinates() {
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

    public PersonImpl getOwner() {
        return owner;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setCoordinates(Coordinates coordinates) {
        try {
            this.coordinates = (CoordinatesImpl) coordinates;
        } catch (ClassCastException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * set creation date and set unique id
     */
    public void setAutomaticGenerateField(){
        setCreationDate(new Date());
        setId(QueueManager.getID());
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public void setPartNumber(String partnumber) {
        this.partNumber = partnumber;
    }

    @Override
    public void setManufactureCost(double cost) {
        this.manufactureCost = cost;
    }

    @Override
    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public void setOwner(Person person) {
        try {
            this.owner = (PersonImpl) person;
        } catch (ClassCastException e){
            System.err.println(e.getMessage());
        }
    }

    public void setId(long id){
        this.id = id;
    }

    /**
     * set name product from string, used in ConsoleReader
     */
    public void setNameStr(String inputStr){
        setName(casterFieldProductFromString.castName(inputStr));
    }

    /**
     * set price product from string, used in ConsoleReader
     */
    public void setPriceStr(String inputStr){
        setPrice(casterFieldProductFromString.castPrice(inputStr));
    }

    /**
     * set part number product from string, used in ConsoleReader
     */
    public void setPartNumberStr(String inputStr){
        setPartNumber(casterFieldProductFromString.castPartNumber(inputStr));
    }

    /**
     * set manufacture cost product from string, used in ConsoleReader
     */
    public void setManufactureCostStr(String inputStr){
        setManufactureCost(casterFieldProductFromString.castManufactureCost(inputStr));
    }

    /**
     * set unit product from string, used in ConsoleReader
     */
    public void setUnitOfMeasureStr(String inputStr){
        setUnitOfMeasure(casterFieldProductFromString.castUnitOfMeasure(inputStr));
    }

    public void setCreationDate(Date creationDate){
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
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

}
