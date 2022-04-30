package com.example.reversed.controllers;

import com.example.reversed.exceptions.TaskException;
import com.example.reversed.security.TokenManager;
import com.example.reversed.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/todolist/admin/")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("users")
    public int countUsers() throws TaskException {
        return adminService.countUsers();
    }

    @GetMapping("tasks")
    public int countTasks() throws TaskException {
        return adminService.countTasks();
    }
}
