package lab.leleonz.springbootapi.Exceptions;

public class InvalidEmailException extends RuntimeException {
    
    public InvalidEmailException() {
        super("Email submitted is invalid");
    }

}
