package dev.decagon.activity_tracker.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseDto<T> {
    private String message;
    private Boolean success;
    private T data;
}
