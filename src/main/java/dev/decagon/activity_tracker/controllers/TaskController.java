package dev.decagon.activity_tracker.controllers;

import dev.decagon.activity_tracker.entities.Task;
import dev.decagon.activity_tracker.pojos.ApiResponseDto;
import dev.decagon.activity_tracker.pojos.TaskRequestDto;
import dev.decagon.activity_tracker.pojos.TaskResponseDto;
import dev.decagon.activity_tracker.services.TaskService;
import dev.decagon.activity_tracker.util.ResponseManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("api/v1/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private ResponseManager responseManager;

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto> createTask(@RequestBody TaskRequestDto request, HttpSession session) {
        return taskService.createTask(request,session);
    }

    @GetMapping("/viewAll")
    public ResponseEntity<List<TaskResponseDto>> viewAllTasks(HttpSession session ) {

        return taskService.viewAllTasks(session);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponseDto<String> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);

    }
    @GetMapping("/view/{id}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable Long id){
//        TaskRequestDto taskRequestDto = taskService.getTask(id);
        return taskService.getTask(id);
    }
    @PatchMapping("/edit_title/{id}")
    public ResponseEntity<TaskResponseDto>  editTaskTitle(@PathVariable Long id, @RequestBody TaskRequestDto request){
        return taskService.editTaskTitle(request,id);

    }
    @PatchMapping("/edit_description/{id}")
    public ResponseEntity<TaskResponseDto>  editTaskDescription(@PathVariable Long id, @RequestBody TaskRequestDto request){
        return taskService.editTaskDescription(request,id);

    }
    @PatchMapping("/update_status/{id}")
    public ResponseEntity<TaskResponseDto> updateStatus(@PathVariable Long id, @RequestBody TaskRequestDto request){
        return taskService.updateStatus(request,id);
    }
    @GetMapping("/view_tasks/{status}")
    public ResponseEntity<List<TaskResponseDto>> viewTaskByStatus(@PathVariable String status,HttpSession session){
        return taskService.viewTaskByStatus(status,session);


    }
}
