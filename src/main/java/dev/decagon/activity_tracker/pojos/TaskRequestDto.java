package dev.decagon.activity_tracker.pojos;

import dev.decagon.activity_tracker.entities.User;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class TaskRequestDto {
    private String title;
    private String description;
    private  Long user_id;
    private String status;

}
