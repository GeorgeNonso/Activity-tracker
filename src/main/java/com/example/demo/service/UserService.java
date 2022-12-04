package com.example.demo.service;


import com.example.demo.dtos.userdto.UserResponseDto;
import com.example.demo.dtos.userdto.UserSignupDto;
import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.NotNullException;
import com.example.demo.pojos.ApiResponse;
import com.example.demo.pojos.User;

public interface UserService {
    boolean isUserExist(String email);

    boolean isUserExistByEmailAndPassword(String email, String Password);

    ApiResponse<User> signup(UserSignupDto userSignupDto) throws AlreadyExistsException, NotNullException;
    ApiResponse<UserResponseDto> login(String email, String password) throws NotNullException, NotFoundException;
}
