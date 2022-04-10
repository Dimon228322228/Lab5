package Content.Caster;

import Content.Product.UnitOfMeasure;
import Content.Validator.ValidatorProduct;
import Exception.InvalidNameProductException;
import Exception.InvalidPriceException;
import Exception.InvalidPartNumberException;
import Exception.InvalidManufactureCostException;
import Exception.InvalidUnitOfMeasureExseption;
/**
 * A class which creates field of product from different classes
 */
public class CasterFieldProductFromString {
     /**
      * checker correctness of field
      */
     ValidatorProduct valProd = new ValidatorProduct();

     /**
      * @return checked name from the string
      */
     public String castName(String name){
          if (valProd.nameProductValid(name.trim())) {
               return name.trim();
          } else throw new InvalidNameProductException();
     }

     /**
      * @return checked price from the string
      */
     public Double castPrice(String str){
          Double price = Double.parseDouble(str.trim());
          if(valProd.priceValid(price)){
               return price;
          } else {
               throw new InvalidPriceException();
          }
     }

     /**
      * @return checked part number from the string
      */
     public String castPartNumber(String str){
          if (valProd.partNumberValid(str.trim())){
               return str.trim();
          } else {
               throw new InvalidPartNumberException();
          }
     }

     /**
      * @return checked manufacture cost from the string
      */
     public double castManufactureCost(String str){
          double manufactureCost = Double.parseDouble(str.trim());
          if(valProd.manufactureCostValid(manufactureCost)){
               return manufactureCost;
          } else {
               throw new InvalidManufactureCostException();
          }
     }

     /**
      * @return checked unit product from the string
      */
     public UnitOfMeasure castUnitOfMeasure(String str){
          if (valProd.unitOfMeasureValid(str)){
               return UnitOfMeasure.fromString(str.trim());
          } else {
               throw new InvalidUnitOfMeasureExseption();
          }
     }
}
