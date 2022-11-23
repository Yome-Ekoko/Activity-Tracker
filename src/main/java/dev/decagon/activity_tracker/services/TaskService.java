package dev.decagon.activity_tracker.services;


import dev.decagon.activity_tracker.pojos.ApiResponseDto;
import dev.decagon.activity_tracker.pojos.TaskRequestDto;
import dev.decagon.activity_tracker.pojos.TaskResponseDto;
import org.springframework.http.ResponseEntity;


import javax.servlet.http.HttpSession;
import java.util.List;

public interface TaskService {
    ResponseEntity<ApiResponseDto> createTask(TaskRequestDto request, HttpSession session);
    ResponseEntity<List<TaskResponseDto>> viewAllTasks(HttpSession session);
     ApiResponseDto<String> deleteTask(Long task_id);
    ResponseEntity<TaskResponseDto> getTask(Long task_id);
    ResponseEntity<TaskResponseDto> editTaskTitle(TaskRequestDto taskRequestDto, Long id);
    ResponseEntity<TaskResponseDto> editTaskDescription(TaskRequestDto taskRequestDto, Long id);
     ResponseEntity<TaskResponseDto> updateStatus(TaskRequestDto taskRequestDto, Long id);
    ResponseEntity<List<TaskResponseDto>> viewTaskByStatus(String status, HttpSession session);

}
