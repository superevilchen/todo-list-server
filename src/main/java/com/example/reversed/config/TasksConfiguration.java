package com.example.reversed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableScheduling
@EnableSwagger2
@EnableAspectJAutoProxy
public class TasksConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
