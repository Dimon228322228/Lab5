package Content.Product;

import Content.Caster.CasterFieldProductFromString;
import Content.Coordinate.Coordinates;
import Content.Coordinate.RealizedCoordinates;
import Content.Person.Person;
import Content.Person.RealizedPerson;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
public abstract class RealizedProduct implements Product, Serializable {

    private long id; // >0 , unique and automatic generated

    private String name ; // not null and not empty

    private RealizedCoordinates coordinates ; // not null

    private Date creationDate ; // not null and automatic generation

    private Double price ; // not null and >0

    private String partNumber ; // >=22 and not null

    private double manufactureCost ; //

    private UnitOfMeasure unitOfMeasure ; // not null

    private RealizedPerson owner ; // not null

    CasterFieldProductFromString casterFieldProductFromString = new CasterFieldProductFromString();

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

    @XmlElement(name = "Id")
    public long getId() {
        return id;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "Coordinates")
    public RealizedCoordinates getCoordinates() {
        return coordinates;
    }

    @XmlElement(name = "Creation_date")
    @XmlJavaTypeAdapter(value = DateSerializer.class)
    public Date getCreationDate() {
        return creationDate;
    }

    @XmlElement(name = "Price")
    public Double getPrice() {
        return price;
    }

    @XmlElement(name = "Part_number")
    public String getPartNumber() {
        return partNumber;
    }

    @XmlElement(name = "Manufacture_cost")
    public double getManufactureCost() {
        return manufactureCost;
    }

    @XmlElement(name = "Unit_of_measurement")
    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    @XmlElement(name = "Owner")
    public RealizedPerson getOwner() {
        return owner;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setCoordinates(Coordinates coordinates) {
        try {
            this.coordinates = (RealizedCoordinates) coordinates;
        } catch (ClassCastException e) {
            System.err.println(e.getMessage());
        }
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
            this.owner = (RealizedPerson) person;
        } catch (ClassCastException e){
            System.err.println(e.getMessage());
        }
    }

    public void setId(long id){
        this.id = id;
    }

    public void setNameStr(String inputStr){
        setName(casterFieldProductFromString.castName(inputStr));
    }

    public void setPriceStr(String inputStr){
        setPrice(casterFieldProductFromString.castPrice(inputStr));
    }

    public void setPartNumberStr(String inputStr){
        setPartNumber(casterFieldProductFromString.castPartNumber(inputStr));
    }

    public void setManufactureCostStr(String inputStr){
        setManufactureCost(casterFieldProductFromString.castManufactureCost(inputStr));
    }

    public void setUnitOfMeasureStr(String inputStr){
        setUnitOfMeasure(casterFieldProductFromString.castUnitOfMeasure(inputStr));
    }

    public void setCreationDate(Date creationDate){
        this.creationDate = creationDate;
    }
}
