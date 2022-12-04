package com.example.demo.controllers;

import com.example.demo.dtos.userdto.UserResponseDto;
import com.example.demo.dtos.userdto.UserSignupDto;
import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.NotNullException;
import com.example.demo.pojos.ApiResponse;
import com.example.demo.pojos.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/activity-tracker")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<User>> signup(@RequestBody UserSignupDto userSignupDto) throws AlreadyExistsException, NotNullException {
        ApiResponse<User> user = userService.signup(userSignupDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<ApiResponse<UserResponseDto>> login(@RequestBody User user) throws NotFoundException, NotNullException {
        ApiResponse<UserResponseDto> userResponseDtoApiResponse = userService.login(user.getEmail(), user.getPassword());
        return new ResponseEntity<>(userResponseDtoApiResponse, HttpStatus.ACCEPTED);
    }


}


