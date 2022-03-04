package Content;

import Exception.InvalidNameProductException;
import Exception.InvalidPriceException;
import Exception.InvalidPartNumberException;
import Exception.InvalidManufactureCostException;
import Exception.InvalidUnitOfMeasureExseption;

public interface CasterFieldProductFromString {
     ValidatorProduct valProd = new ValidatorProduct() {};
     default String castName(String name){
          if (valProd.NameProductValid(name)) {
               return name;
          } else throw new InvalidNameProductException();
     }

     default Double castPrice(String str){
          Double price = Double.parseDouble(str);
          if(valProd.PriceValid(price)){
               return price;
          } else {
               throw new InvalidPriceException();
          }
     }

     default String castPartNumber(String str){
          if (valProd.partNumberValid(str)){
               return str;
          } else {
               throw new InvalidPartNumberException();
          }
     }

     default double castManufactureCost(String str){
          double manufactureCost = Double.parseDouble(str);
          if(valProd.manufactureCostValid(manufactureCost)){
               return manufactureCost;
          } else {
               throw new InvalidManufactureCostException();
          }
     }

     default UnitOfMeasure castUnitOfMeasure(String str){
          if (valProd.UnitOfMeasureValid(str)){
               return UnitOfMeasure.valueOf(str);
          } else {
               throw new InvalidUnitOfMeasureExseption();
          }
     }
}
