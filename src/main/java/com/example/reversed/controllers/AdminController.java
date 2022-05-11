package com.example.reversed.controllers;

import com.example.reversed.exceptions.TaskException;
import com.example.reversed.security.TokenManager;
import com.example.reversed.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

// currently not implemented

@RestController
@RequestMapping("api/v1/todolist/admin/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")

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
