package com.example.demo.dtos.taskdto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CreateTaskDto {
    private String title;
    private String description;
}

