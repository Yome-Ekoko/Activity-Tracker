package dev.decagon.activity_tracker.controllers;

import dev.decagon.activity_tracker.pojos.ApiResponseDto;
import dev.decagon.activity_tracker.pojos.RegistrationRequestDto;
import dev.decagon.activity_tracker.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
    @AllArgsConstructor
    @RequestMapping("/api/v1/user")
    public class UserController {
    private final UserService userService;

    @PostMapping("/signUp")
    public ApiResponseDto<String> registerUser(@RequestBody RegistrationRequestDto request) {
        return userService.createUser(request);
    }


}


