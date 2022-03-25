package Content.Caster;

import Content.Product.UnitOfMeasure;
import Content.Validator.ValidatorProduct;
import Exception.InvalidNameProductException;
import Exception.InvalidPriceException;
import Exception.InvalidPartNumberException;
import Exception.InvalidManufactureCostException;
import Exception.InvalidUnitOfMeasureExseption;

public class CasterFieldProductFromString {
     ValidatorProduct valProd = new ValidatorProduct();
     public String castName(String name){
          if (valProd.NameProductValid(name.trim())) {
               return name.trim();
          } else throw new InvalidNameProductException();
     }

     public Double castPrice(String str){
          Double price = Double.parseDouble(str.trim());
          if(valProd.PriceValid(price)){
               return price;
          } else {
               throw new InvalidPriceException();
          }
     }

     public String castPartNumber(String str){
          if (valProd.partNumberValid(str.trim())){
               return str.trim();
          } else {
               throw new InvalidPartNumberException();
          }
     }

     public double castManufactureCost(String str){
          double manufactureCost = Double.parseDouble(str.trim());
          if(valProd.manufactureCostValid(manufactureCost)){
               return manufactureCost;
          } else {
               throw new InvalidManufactureCostException();
          }
     }

     public UnitOfMeasure castUnitOfMeasure(String str){
          if (valProd.UnitOfMeasureValid(str)){
               return UnitOfMeasure.fromString(str.trim());
          } else {
               throw new InvalidUnitOfMeasureExseption();
          }
     }
}
