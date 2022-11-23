package dev.decagon.activity_tracker.services.serviceimpls;

import dev.decagon.activity_tracker.entities.User;
import dev.decagon.activity_tracker.pojos.LoginDto;
import dev.decagon.activity_tracker.repositories.UserRepository;
import dev.decagon.activity_tracker.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.servlet.http.HttpSession;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;
    @Test

    void createUser() {
        String username = "yo";
        String email = "yo@gmail.com";
        String password = "1234";
        String gender = "MALE";
        userRepository.save(new User(username, email, password, gender));
        User user = userRepository.findUserByEmailAndPassword(email, password);
        Boolean expected = false;
        Boolean actual = user == null;
        assertEquals(expected, actual);
    }
    @Test
    void userLogin() {
        LoginDto request = new LoginDto();
        request.setEmail("yo@gmail.com");
        request.setPassword("1234");
        userService.validateUser(request, session);
        User user = (User) session.getAttribute("currentUser");
        assertEquals(true, user != null);
    }
    @Test
    void userLogout(){
        LoginDto request = new LoginDto();
        request.setEmail("yom@gmail.com");
        request.setPassword("1234");
        userService.validateUser(request, session);
        userService.userLogout(session);
        User user = (User) session.getAttribute("currentUser");
        assertEquals(true, user == null);
    }
}