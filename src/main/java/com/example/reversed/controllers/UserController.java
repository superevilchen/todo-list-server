package com.example.reversed.controllers;

import com.example.reversed.beans.dtos.LoginReqDto;
import com.example.reversed.beans.dtos.LoginResDto;
import com.example.reversed.beans.dtos.RegisterReqDto;
import com.example.reversed.exceptions.TaskException;
import com.example.reversed.security.TokenManager;
import com.example.reversed.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/todolist/user/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class UserController {

    private final UserService userService;
    private final TokenManager tokenManager;

    @PostMapping("register")
    public void register(@RequestBody RegisterReqDto registerReqDto) throws TaskException {
        userService.register(registerReqDto.getEmail(), registerReqDto.getPassword());
    }

    @PostMapping("login")
    public LoginResDto login(@RequestBody LoginReqDto loginReqDto) throws TaskException {

        UUID token = userService.login(loginReqDto.getEmail(), loginReqDto.getPassword());

        return new LoginResDto(loginReqDto.getEmail(), token, tokenManager.getUserType(token));
    }
}
