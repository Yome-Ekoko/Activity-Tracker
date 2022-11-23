package dev.decagon.activity_tracker.pojos;

import lombok.Data;

@Data
public class RegistrationRequestDto {
    private String username;
    private String email;
    private String password;
    private String gender;

}
