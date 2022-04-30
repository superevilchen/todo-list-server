package com.example.reversed.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@Profile("development")
//public class DevConfiguration implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS").allowedOrigins("*");
//    }
//}

import com.example.reversed.exceptions.TaskException;
import com.example.reversed.security.TokenManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static com.example.reversed.exceptions.ExceptionState.INVALID_TOKEN;

@Component
@Order(1)
@RequiredArgsConstructor
public class DevConfiguration implements Filter {

    private final TokenManager tokenManager;

    public void destroy() {
    }

    @SneakyThrows
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // Authorize (allow) all domains to consume the content
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin","http://localhost:3000");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST, DELETE");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers","authorization, Origin, Accept, x-auth-token, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");

        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
        if (request.getMethod().equals("OPTIONS")) {
            return;
        }

        // filter interceptor
//        String auth = request.getHeader("Authorization");
//        if (!tokenManager.isTokenExists(UUID.fromString(auth))){
//            throw new TaskException(INVALID_TOKEN);
//        }
        ///

        // pass the request along the filter chain
        chain.doFilter(request, servletResponse);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

}