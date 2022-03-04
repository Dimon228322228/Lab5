package Exception;

public class InvalidUnitOfMeasureExseption extends InvalidProductFieldException{
    public InvalidUnitOfMeasureExseption(){
        super("Invalid value has been entered. Choose unit of measurement include list!");
    }
}
