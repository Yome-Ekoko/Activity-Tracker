package dev.decagon.activity_tracker.controllers;

import dev.decagon.activity_tracker.pojos.ApiResponseDto;
import dev.decagon.activity_tracker.pojos.LoginDto;
import dev.decagon.activity_tracker.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto> userLogin(@RequestBody LoginDto loginDto, HttpSession session){

       return userService.validateUser(loginDto,session);


    }
    @PostMapping("/logout")
    public ResponseEntity<String> userLogout(HttpSession session){
        return userService.userLogout(session);
    }

}
