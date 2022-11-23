package dev.decagon.activity_tracker.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoUserException extends NullPointerException {

    private String debugMessage;


    public NoUserException(String s, String debugMessage) {
        super(s);
        this.debugMessage = debugMessage;
    }
}
