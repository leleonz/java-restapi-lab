package lab.leleonz.springbootapi.Exceptions.Handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lab.leleonz.springbootapi.Exceptions.InvalidEmailException;

@ControllerAdvice
public class InvalidEmailAdvice {
    
    @ResponseBody
    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidEmailHandler(InvalidEmailException ex) {
        return ex.getMessage();
    }

}
