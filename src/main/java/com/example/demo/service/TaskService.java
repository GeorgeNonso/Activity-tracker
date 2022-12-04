package com.example.demo.service;


import com.example.demo.dtos.taskdto.CreateTaskDto;
import com.example.demo.enums.Status;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.NotNullException;
import com.example.demo.pojos.ApiResponse;
import com.example.demo.pojos.Task;
import com.example.demo.pojos.User;

import java.util.List;

public interface TaskService {
    User findLoggedInUser();

    Task getTaskById(Long taskId);

    ApiResponse<Task> createTask(CreateTaskDto createTaskDto) throws NotNullException;
    ApiResponse<List<Task>> getAllUserTasks();
    ApiResponse<Task> viewTaskById(Long taskId) throws NotFoundException;
    ApiResponse<List<Task>> getTaskByStatus(Status status) throws NotFoundException;

    ApiResponse<Task> updateTaskStatus(Long taskId, Task newTask);

    ApiResponse<Task> updateTaskTitleOrDescription(Long taskId, Task newTask) throws NotFoundException;
    void deleteTask(Long taskId);
}
