package dev.decagon.activity_tracker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
   private Long id;

    @Column(name="username",nullable = false)
    private String username;

    @Column(name="email",nullable = false,unique = true)
  private String email;

    @Column(name="password",nullable = false)
  private String password;

    @Column(name="gender",nullable = false)
  private String Gender;

    @Column(name="createdAt",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updatedAt")
  private Date updatedAt;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name="lastLogin",nullable = false)
//  private Date lastLogin;
    @JsonIgnore
    @OneToMany(mappedBy = "user",fetch=FetchType.EAGER)
    private List<Task> tasks;

    public User(String username, String email, String password, String gender) {
        this.username = username;
        this.email = email;
        this.password = password;
        Gender = gender;
    }

    @PrePersist
    public void createdAt(){
        createdAt=new Date();
    }

    @PreUpdate
    public void updatedAt(){
        updatedAt = new Date();
    }

}
