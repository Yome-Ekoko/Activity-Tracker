package dev.decagon.activity_tracker.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorMessage {
    private HttpStatus status;
    private String message;
    private String debugMessage;
}
