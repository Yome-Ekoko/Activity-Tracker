package dev.decagon.activity_tracker.exceptions;

import lombok.Getter;
import lombok.Setter;

public class ResourceNotFoundException extends RuntimeException{
@Getter
@Setter
    private String debugMessage;


    public ResourceNotFoundException(String message, String debugMessage) {
        super(message);
        this.debugMessage = debugMessage;
    }
}
