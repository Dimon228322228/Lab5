package Content;

import java.util.Date;

public abstract class RealizedProduct implements Comparable<Product>, Product, CasterFieldProductFromString{

    private long id; // >0 , unique and automatic generated

    private String name ; // not null and not empty

    private Coordinates coordinates ; // not null

    private Date creationDate ; // not null and automatic generation

    private Double price ; // not null and >0

    private String partNumber ; // >=22 and not null

    private double manufactureCost ; //

    private UnitOfMeasure unitOfMeasure ; // not null

    private Person owner ; // not null

    @Override
    public int compareTo(Product o) {
        return this.getName().compareTo(o.getName());
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

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
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
        this.owner = person;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setNameStr(String inputStr){
        setName(castName(inputStr));
    }

    public void setPriceStr(String inputStr){
        setPrice(castPrice(inputStr));
    }

    public void setPartNumberStr(String inputStr){
        setPartNumber(castPartNumber(inputStr));
    }

    public void setManufactureCostStr(String inputStr){
        setManufactureCost(castManufactureCost(inputStr));
    }

    public void setUnitOfMeasureStr(String inputStr){
        setUnitOfMeasure(castUnitOfMeasure(inputStr));
    }

    public void setCreationDate(Date creationDate){
        this.creationDate = creationDate;
    }

}
