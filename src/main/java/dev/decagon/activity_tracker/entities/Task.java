package dev.decagon.activity_tracker.entities;

import dev.decagon.activity_tracker.emums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = false)
    private Date created_At;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedAt")
    private Date updated_At;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "completedAt")
    private Date completed_At;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @PrePersist
    public void createdAt(){
        created_At=new Date();
    }

    @PreUpdate
    public void updatedAt(){
        updated_At = new Date();
    }

    }

