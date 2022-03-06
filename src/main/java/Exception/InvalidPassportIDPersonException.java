package Exception;

public class InvalidPassportIDPersonException extends InvalidProductFieldException{
    public InvalidPassportIDPersonException(){
        super("Invalid passport id has been entered. Value isn't null, grated 41 and lowered 6!");
    }
}
