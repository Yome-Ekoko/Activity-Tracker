package dev.decagon.activity_tracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(ResourceNotFoundException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(HttpStatus.NOT_FOUND);
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setDebugMessage(ex.getDebugMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    protected ResponseEntity<Object> handleInvalidException(InvalidRequestException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(HttpStatus.BAD_REQUEST);
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setDebugMessage(ex.getDebugMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoUserException.class)
    protected ResponseEntity<Object> handleNoUserException(NoUserException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(HttpStatus.NOT_FOUND);
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setDebugMessage(ex.getDebugMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
