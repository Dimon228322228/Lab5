package Content.Validator;

import Content.Product.Product;

public class RealizedValidatorProduct extends ValidatorProduct {
    public boolean validProduct(Product product){
        return (nameProductValid(product.getName()) &&
                coordinateValid(product.getCoordinates()) &&
                dateValid(product.getCreationDate()) &&
                priceValid(product.getPrice()) &&
                partNumberValid(product.getPartNumber()) &&
                manufactureCostValid(product.getManufactureCost()) &&
                unitOfMeasureValid(product.getUnitOfMeasure()) &&
                personValid(product.getOwner()));
    }
}
