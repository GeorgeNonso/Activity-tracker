package com.example.demo.service.ClassServiceImpl;

import com.example.demo.dtos.taskdto.CreateTaskDto;
import com.example.demo.enums.Status;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.NotNullException;
import com.example.demo.pojos.ApiResponse;
import com.example.demo.pojos.Task;
import com.example.demo.pojos.User;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.TaskService;
import com.example.demo.utility.ResponseManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final HttpSession httpSession;
    private final ResponseManager responseManager;
    private ApiResponse apiResponse;

    @Override
    public User findLoggedInUser() {
        Long userId = (Long) httpSession.getAttribute("userId");
        User user = userRepository.findById(userId).get();
        return user;
    }

    @Override
    public Task getTaskById(Long taskId){
        return taskRepository.findById(taskId).get();
    }

    @Override
    public ApiResponse<Task> createTask(CreateTaskDto createTaskDto) throws NotNullException {
        if(createTaskDto.getTitle().equals("")){
            throw new NotNullException("Task must have a title");
        } else if (createTaskDto.getDescription().equals("")) {
            throw new NotNullException("Task must have a description");
        } else {
            Task task = new Task();
            task.setTitle(createTaskDto.getTitle());
            task.setDescription(createTaskDto.getDescription());
            task.setStatus(Status.PENDING);
            task.setCreatedAt(new Date());
            task.setUpdatedAt(new Date());
            task.setCompletedAt(null);
            task.setUser(findLoggedInUser());
            taskRepository.save(task);
            apiResponse = responseManager.success(task);
        }
        return apiResponse;
    }

    @Override
    public ApiResponse<List<Task>> getAllUserTasks() {
        List<Task> userTasks = taskRepository.findTasksByUser(findLoggedInUser());
        return responseManager.success(userTasks);
    }

    @Override
    public ApiResponse<Task> viewTaskById(Long taskId) throws NotFoundException {
        Task task = taskRepository.findTaskByUserAndId(findLoggedInUser(), taskId);
        if(task != null){
            return  responseManager.success(task);
        }
        throw new NotFoundException("Task not available");
    }

    @Override
    public ApiResponse<List<Task>> getTaskByStatus(Status status) throws NotFoundException {
        List<Task> tasksByStatus = taskRepository.findTasksByUserAndStatus(findLoggedInUser(),status);
        if(tasksByStatus == null || tasksByStatus.size() < 1){
            throw new NotFoundException("No " + status +" Tasks");
        }
        return responseManager.success(tasksByStatus);
    }

    @Override
    public ApiResponse<Task> updateTaskStatus(Long taskId, Task newTask){
        Task editTask = getTaskById(taskId);
        editTask.setStatus(newTask.getStatus());
        editTask.setUpdatedAt(new Date());

        if(newTask.getStatus() == (Status.DONE)){
            editTask.setCompletedAt(new Date());
        }
        taskRepository.save(editTask);
        return responseManager.success(editTask);
    }

    @Override
    public ApiResponse<Task> updateTaskTitleOrDescription(Long taskId, Task newTask) throws NotFoundException {
        Task editTask = getTaskById(taskId);
        if(editTask != null || !editTask.equals("")){
            editTask.setTitle(newTask.getTitle());
            editTask.setDescription(newTask.getDescription());
            editTask.setUpdatedAt(new Date());
            Task newUpdatedTask = taskRepository.save(editTask);

            return responseManager.success(newUpdatedTask);
        } else {
            throw new NotFoundException("No such task");
        }
    }

    @Override
    public void deleteTask(Long taskId){
        taskRepository.deleteById(taskId);
    }


}
