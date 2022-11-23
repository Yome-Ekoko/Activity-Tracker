package dev.decagon.activity_tracker.services.serviceimpls;

import dev.decagon.activity_tracker.emums.Status;
import dev.decagon.activity_tracker.entities.Task;
import dev.decagon.activity_tracker.entities.User;
import dev.decagon.activity_tracker.pojos.LoginDto;
import dev.decagon.activity_tracker.pojos.TaskRequestDto;
import dev.decagon.activity_tracker.pojos.TaskResponseDto;
import dev.decagon.activity_tracker.repositories.TaskRepository;
import dev.decagon.activity_tracker.repositories.UserRepository;
import dev.decagon.activity_tracker.services.TaskService;
import dev.decagon.activity_tracker.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskServiceImplTest {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;
    @Autowired
    HttpSession session;
    @Test
    void createTask() {
        LoginDto request = new LoginDto();
        request.setEmail("yo@gmail.com");
        request.setPassword("1234");
        userService.validateUser(request, session);
        TaskRequestDto taskRequestDto = new TaskRequestDto();
        taskRequestDto.setTitle("Yome's");
        taskRequestDto.setDescription("complete my task now");
        taskService.createTask(taskRequestDto, session);
        Task task = taskRepository.findById(8L).get();
        assertEquals("Yome's", task.getTitle());
    }
    @Test
    void viewAllTask() {
        LoginDto request = new LoginDto();
        request.setEmail("yo@gmail.com");
        request.setPassword("1234");
        userService.validateUser(request, session);
        User user = (User) session.getAttribute("currentUser");
        List<Task> tasks = user.getTasks();
        assertEquals(true, tasks.size() > 0);
    }
    @Test
    void viewTaskById() {
        ResponseEntity<TaskResponseDto> response = taskService.getTask(1L);
        assertEquals(true, response != null);
    }
    @Test
    void edit_taskTitle() {
        TaskRequestDto request = new TaskRequestDto();
        request.setTitle("My new title");
        taskService.editTaskTitle(request, 1L);
        Task task = taskRepository.findById(1L).get();
        assertEquals("My new title", task.getTitle());
    }
    @Test
    void edit_taskDescription() {
        TaskRequestDto request = new TaskRequestDto();
        request.setDescription("My new description");
        taskService.editTaskDescription(request, 1L);
        Task task = taskRepository.findById(1L).get();
        assertEquals("My new description", task.getDescription());
    }
    @Test
    void deleteTask() {
    }
    @Test
    void viewTaskByStatus() {
        LoginDto request = new LoginDto();
        request.setEmail("yo@gmail.com");
        request.setPassword("1234");
        userService.validateUser(request, session);
        String status = "PENDING";
        ResponseEntity<List<TaskResponseDto>> task = taskService.viewTaskByStatus(status, session);
        assertEquals(true, task != null);
    }
    @Test
    void updateTaskStatus() {
        TaskRequestDto request = new TaskRequestDto();
        request.setStatus("In_progress");
        taskService.updateStatus(request, 1L);
        Task task = taskRepository.findById(1L).get();
        assertEquals(Status.IN_PROGRESS, task.getStatus());
    }
}