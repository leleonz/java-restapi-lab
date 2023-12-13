package lab.leleonz.springbootapi.Exceptions;

public class EmptyNameException extends RuntimeException {

    public EmptyNameException() {
        super("Name cannot be empty");
    }

    public EmptyNameException(String message) {
        super(message);
    }
    
}
