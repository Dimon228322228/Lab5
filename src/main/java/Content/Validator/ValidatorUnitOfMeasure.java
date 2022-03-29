package Content.Validator;

import Content.Product.UnitOfMeasure;

public class ValidatorUnitOfMeasure {
    public boolean validUnitOfMeasure(UnitOfMeasure unitOfMeasure){
        return unitOfMeasure != null;
    }

    public boolean validUnitOfMeasure(String unitOfMeasure){
        if (unitOfMeasure == null) return false;
        String[] list = UnitOfMeasure.getTitleinString().trim().split(" ");
        for (String string: list){
            if(unitOfMeasure.equals(string)) return true;
        }
        return false;
    }

}
