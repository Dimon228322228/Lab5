package Content.Validator;

import Content.Product.UnitOfMeasure;

public class ValidatorUnitOfMeasure {
    public boolean ValidUnitOfMeasure(UnitOfMeasure unitOfMeasure){
        return unitOfMeasure != null;
    }

    public boolean ValidUnitOfMeasure(String unitOfMeasure){
        if (unitOfMeasure == null) return false;
        String[] list = UnitOfMeasure.getTitleinString().trim().split(" ");
        for (String string: list){
            if(unitOfMeasure.equals(string)) return true;
        }
        return false;
    }

}
