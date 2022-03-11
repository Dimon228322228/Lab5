package Content.Validator;

import Content.Product.UnitOfMeasure;

public interface ValidatorUnitOfMeasure {
    default boolean ValidUnitOfMeasure(UnitOfMeasure unitOfMeasure){
        return unitOfMeasure != null;
    }

    default boolean ValidUnitOfMeasure(String unitOfMeasure){
        if (unitOfMeasure == null) return false;
        String[] list = UnitOfMeasure.getTitleinString().trim().split(" ");
        for (String string: list){
            if(unitOfMeasure.equals(string)) return true;
        }
        return false;
    }

}
