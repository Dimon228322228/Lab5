package Content;

public class RealizedValidatorProduct implements ValidatorProduct{
    public boolean validProduct(Product product){
        return (NameProductValid(product.getName()) &&
                CoordinateValid(product.getCoordinates()) &&
                DateValid(product.getCreationDate()) &&
                PriceValid(product.getPrice()) &&
                partNumberValid(product.getPartNumber()) &&
                manufactureCostValid(product.getManufactureCost()) &&
                UnitOfMeasureValid(product.getUnitOfMeasure()) &&
                PersonValid(product.getOwner()));
    }
}
