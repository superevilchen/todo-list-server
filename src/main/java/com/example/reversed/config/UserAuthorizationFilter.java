package com.example.reversed.config;

import com.example.reversed.beans.ClientType;
import com.example.reversed.exceptions.ExceptionState;
import com.example.reversed.exceptions.TaskException;
import com.example.reversed.security.TokenManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebFilter({"api/v1/todolist/**", "api/v1/todolist/admin/**"})
@RequiredArgsConstructor
public class UserAuthorizationFilter implements Filter {

    private final TokenManager tokenManager;

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String token = httpServletRequest.getHeader("authorization");

        if (!tokenManager.isAuthorizedFor(UUID.fromString(token), tokenManager.getUserType(UUID.fromString(token)))){
            throw new TaskException(ExceptionState.INVALID_TOKEN);
        }

        chain.doFilter(request, response);
    }
}
