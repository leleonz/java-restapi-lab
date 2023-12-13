package lab.leleonz.springbootapi.Exceptions.Handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lab.leleonz.springbootapi.Exceptions.EmptyNameException;

@ControllerAdvice
public class EmptyNameAdvice {
    
    @ResponseBody
    @ExceptionHandler(EmptyNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String emptyNameHandler(EmptyNameException ex) {
        return ex.getMessage();
    }
}
