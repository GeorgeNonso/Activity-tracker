package com.example.demo.repositories;

import com.example.demo.enums.Status;
import com.example.demo.pojos.Task;
import com.example.demo.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByUser(User user);
    Task findTaskByUserAndId(User user, Long taskId);
    List<Task> findTasksByUserAndStatus(User user, Status status);
    //Task findTaskByUserAndIdAndStatus(User user, Long id, Status status);
}
