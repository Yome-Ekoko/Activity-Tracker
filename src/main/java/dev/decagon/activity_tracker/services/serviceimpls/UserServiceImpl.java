package dev.decagon.activity_tracker.services.serviceimpls;

import dev.decagon.activity_tracker.entities.User;
import dev.decagon.activity_tracker.pojos.ApiResponseDto;
import dev.decagon.activity_tracker.pojos.LoginDto;
import dev.decagon.activity_tracker.pojos.RegistrationRequestDto;
import dev.decagon.activity_tracker.repositories.UserRepository;
import dev.decagon.activity_tracker.services.UserService;
import dev.decagon.activity_tracker.util.ResponseManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final ResponseManager responseManager;
    private final UserRepository registrationRepository;
    private final UserRepository userRepository;


    @Override
    public ApiResponseDto<String> createUser(RegistrationRequestDto request) {
        if (request.getUsername() == null)
            return responseManager.unsuccessful("name required");

        if (request.getEmail() == null || !request.getEmail().contains("@"))
            return responseManager.unsuccessful("email required");

        if (request.getPassword() == null)
            return responseManager.unsuccessful("password required");


        if (request.getGender() == null)
            return responseManager.unsuccessful("Gender required");

        Boolean existingUser = registrationRepository.existsByEmail(request.getEmail());

        if (existingUser)
            return responseManager.unsuccessful("Email Already Exist");
        else {

            User user = new User();
            BeanUtils.copyProperties(request, user);
            registrationRepository.save(user);

            return responseManager.success(user);

        }

    }



    @Override
    public ResponseEntity<ApiResponseDto>  validateUser(LoginDto loginDto, HttpSession session) {
        User user =userRepository.findUserByEmailAndPassword(loginDto.getEmail(),loginDto.getPassword());
    if(user != null){
        session.setAttribute("currentUser",user);
        return  new ResponseEntity<>(responseManager.loginSuccess(user), HttpStatus.OK);
    }
    return new ResponseEntity<>(responseManager.unsuccessful("LOGIN FAILED"), HttpStatus.BAD_REQUEST);
    }
    @Override
    public ResponseEntity<String> userLogout(HttpSession session){
        session.invalidate();
       return new  ResponseEntity<>("Logged_out Successfully",HttpStatus.ACCEPTED);

    }
}
