package dev.decagon.activity_tracker.pojos;

import dev.decagon.activity_tracker.emums.Status;
import lombok.Data;

import java.util.Date;

@Data
public class TaskResponseDto {
        private String title;
        private String description;
        private Status status;
        private Date completedDate;
}
