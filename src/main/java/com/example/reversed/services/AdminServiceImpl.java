package com.example.reversed.services;

import com.example.reversed.repos.TaskRepository;
import com.example.reversed.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public int countTasks() {
        return taskRepository.getTotalTasks();
    }

    @Override
    public int countUsers() {
        return (int) userRepository.count();
    }
}
