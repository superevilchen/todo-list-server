package com.example.reversed.services;

import com.example.reversed.exceptions.TaskException;

import java.util.UUID;

public interface UserService {

    void register(String email, String password) throws TaskException;
    UUID login(String email, String password) throws TaskException;
}
