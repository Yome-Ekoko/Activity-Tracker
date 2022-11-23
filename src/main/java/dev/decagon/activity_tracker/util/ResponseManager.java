package dev.decagon.activity_tracker.util;

import dev.decagon.activity_tracker.pojos.ApiResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

    @Service
    @AllArgsConstructor
    public class ResponseManager<T> {
        public ApiResponseDto<T> success(T data) {
            return new ApiResponseDto<T>("Request successful", true, data);
        }

        public ApiResponseDto<T> loginSuccess(T data) {
            return new ApiResponseDto<T>("Login successful", true, data);
        }

        public ApiResponseDto<T> unsuccessful(String errorMessage) {
            return new ApiResponseDto<T>(errorMessage, false, null);
        }
    }
