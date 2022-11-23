package dev.decagon.activity_tracker.services.serviceimpls;

import dev.decagon.activity_tracker.emums.Status;
import dev.decagon.activity_tracker.entities.Task;
import dev.decagon.activity_tracker.entities.User;
import dev.decagon.activity_tracker.exceptions.NoUserException;
import dev.decagon.activity_tracker.exceptions.ResourceNotFoundException;
import dev.decagon.activity_tracker.pojos.ApiResponseDto;
import dev.decagon.activity_tracker.pojos.TaskRequestDto;
import dev.decagon.activity_tracker.pojos.TaskResponseDto;
import dev.decagon.activity_tracker.repositories.TaskRepository;
import dev.decagon.activity_tracker.repositories.UserRepository;
import dev.decagon.activity_tracker.services.TaskService;
import dev.decagon.activity_tracker.util.ResponseManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final ResponseManager responseManager;

    @Override
    public ResponseEntity<ApiResponseDto> createTask(TaskRequestDto request, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");

        if (user != null) {
            Task task = new Task();
            task.setUser(user);
            BeanUtils.copyProperties(request, task);
            task.setStatus(Status.PENDING);
            taskRepository.save(task);

            return new ResponseEntity<>(responseManager.success(task), HttpStatus.CREATED);

        } else {
            throw new ResourceNotFoundException("Login to create a task", "No User in session");
        }

    }

    @Override
    public ResponseEntity<List<TaskResponseDto>> viewAllTasks(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            throw new NoUserException("Login to view your tasks", "No user in session");
        }
        List<Task> tasks = user.getTasks();
        List<TaskResponseDto> taskResponseDtoList = new ArrayList<>();
        tasks.forEach(task -> {
            TaskResponseDto taskResponseDto = new TaskResponseDto();
            taskResponseDto.setDescription(task.getDescription());
            taskResponseDto.setTitle(task.getTitle());
            taskResponseDto.setStatus(task.getStatus());
            taskResponseDtoList.add(taskResponseDto);
        });
        return new ResponseEntity<>(taskResponseDtoList, HttpStatus.ACCEPTED);
    }


    @Override
    public ApiResponseDto<String> deleteTask(Long id) {

        boolean exists = taskRepository.existsById(id);
        if (!exists) {
            responseManager.unsuccessful("does not exist");
        }

        taskRepository.deleteById(id);
        return responseManager.success("Task Successfully deleted");
    }

    @Override
    public ResponseEntity<TaskResponseDto> getTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Task not found", "enter a valid Id for this task"));
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setDescription(task.getDescription());
        taskResponseDto.setTitle(task.getTitle());
        taskResponseDto.setStatus(task.getStatus());
        return new ResponseEntity<>(taskResponseDto, HttpStatus.ACCEPTED);


    }

    @Override
    public ResponseEntity<TaskResponseDto> editTaskTitle(TaskRequestDto taskRequestDto, Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Task not found", "enter a valid Id for this task"));
        task.setTitle(taskRequestDto.getTitle());
        taskRepository.save(task);
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setDescription(task.getDescription());
        taskResponseDto.setTitle(task.getTitle());
        taskResponseDto.setStatus(task.getStatus());

        return new ResponseEntity<>(taskResponseDto, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<TaskResponseDto> editTaskDescription(TaskRequestDto taskRequestDto, Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Task not found", "enter a valid Id for this task"));

        task.setDescription(taskRequestDto.getDescription());
        taskRepository.save(task);
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setDescription(task.getDescription());
        taskResponseDto.setTitle(task.getTitle());
        taskResponseDto.setStatus(task.getStatus());

        return new ResponseEntity<>(taskResponseDto, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<TaskResponseDto> updateStatus(TaskRequestDto taskRequestDto, Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Task not found", "enter a valid Id for this task"));

        task.setStatus(Status.valueOf(taskRequestDto.getStatus().toUpperCase()));
        if (taskRequestDto.getStatus().toUpperCase().equalsIgnoreCase("completed"))
            task.setCompleted_At(new Date());
        taskRepository.save(task);
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setStatus(task.getStatus());
        taskResponseDto.setTitle(task.getTitle());
        taskResponseDto.setDescription(task.getDescription());
        taskResponseDto.setCompletedDate(task.getCompleted_At());
        return new ResponseEntity<>(taskResponseDto, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<TaskResponseDto>> viewTaskByStatus(String status, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            throw new NoUserException("Login to view status", "User not found");
        } else {
            List<Task> tasks = user.getTasks();
            List<TaskResponseDto> response = new ArrayList<>();

            tasks.forEach(task -> {
                if(task.getStatus().equals(Status.valueOf(status.toUpperCase()))){
                    TaskResponseDto taskResponseDto = new TaskResponseDto();
                    taskResponseDto.setDescription(task.getDescription());
                    taskResponseDto.setTitle(task.getTitle());
                    taskResponseDto.setStatus(task.getStatus());
                    taskResponseDto.setCompletedDate(task.getCompleted_At());
                    response.add(taskResponseDto);
                }
            });
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
    }
}
