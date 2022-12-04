package com.example.demo.exceptions;

import com.example.demo.dtos.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ResponseStatus
@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessageDto> notFoundException(NotFoundException ex, WebRequest request){
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(HttpStatus.NOT_FOUND,ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessageDto);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorMessageDto> alreadyExistsException(AlreadyExistsException ex, WebRequest request){
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(HttpStatus.ALREADY_REPORTED, ex.getMessage());
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(errorMessageDto);
    }

    @ExceptionHandler(NotNullException.class)
    public ResponseEntity<ErrorMessageDto> notNullException(NotNullException ex, WebRequest request){
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessageDto);
    }
}
