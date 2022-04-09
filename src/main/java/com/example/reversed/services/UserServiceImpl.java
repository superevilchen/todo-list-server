package com.example.reversed.services;

import com.example.reversed.beans.ClientType;
import com.example.reversed.beans.User;
import com.example.reversed.exceptions.ExceptionState;
import com.example.reversed.exceptions.TaskException;
import com.example.reversed.repos.UserRepository;
import com.example.reversed.security.Info;
import com.example.reversed.security.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenManager tokenManager;

    @Override
    public void register(String email, String password) throws TaskException {

        if (userRepository.existsByEmail(email)){
            throw new TaskException(ExceptionState.ALREADY_EXISTS);
        }

        userRepository.save(new User(email, password, ClientType.USER));
    }

    @Override
    public UUID login(String email, String password) throws TaskException {

        User user = userRepository.findTop1ByEmail(email).orElseThrow(() -> new TaskException(ExceptionState.NOT_EXISTS));

        Info info = new Info(user.getId(), email, user.getClientType(), LocalDateTime.now());
//        Info info = new Info()

        return tokenManager.addToken(info);
    }
}
