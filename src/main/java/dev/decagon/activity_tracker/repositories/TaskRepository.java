package dev.decagon.activity_tracker.repositories;

import dev.decagon.activity_tracker.emums.Status;
import dev.decagon.activity_tracker.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status);
}
