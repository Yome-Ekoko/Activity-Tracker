package dev.decagon.activity_tracker.services;

import dev.decagon.activity_tracker.entities.User;
import dev.decagon.activity_tracker.pojos.ApiResponseDto;
import dev.decagon.activity_tracker.pojos.LoginDto;
import dev.decagon.activity_tracker.pojos.RegistrationRequestDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface UserService {
     ApiResponseDto<String> createUser(RegistrationRequestDto request);
      ResponseEntity<ApiResponseDto> validateUser(LoginDto loginDto, HttpSession session);
    ResponseEntity<String> userLogout(HttpSession session);
//
//    User validateUser(UserDto userSignUpDto);
//
//    ApiResponseDto getUser(Long Id);
//    void deleteUser(Long Id);
//    List<ApiResponseDto> getAllUsers();
}
