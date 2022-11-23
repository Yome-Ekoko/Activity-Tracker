package dev.decagon.activity_tracker.repositories;

import dev.decagon.activity_tracker.entities.Task;
import dev.decagon.activity_tracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User ,Long> {


            boolean existsByEmail(String email);
//    Optional<User> findByEmail(String email);
//    @Query(nativeQuery = true,value = "select * from  users  where name like %:question% or email like %:question%")
//    Optional<List<User>> findBySearch(String question);
        User findUserByEmailAndPassword(String email, String password);

    }

