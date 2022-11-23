package dev.decagon.activity_tracker.exceptions;

import lombok.Getter;
import lombok.Setter;

public class InvalidRequestException extends RuntimeException{
    @Getter
    @Setter
    private String debugMessage;

    public InvalidRequestException(String message, String debugMessage) {
        super(message);
        this.debugMessage = debugMessage;
    }
}
