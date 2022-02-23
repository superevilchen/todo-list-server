package com.example.reversed.jobs;

import com.example.reversed.repos.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteExpiredAfterMonthJob {

    private final TaskRepository taskRepository;

    @Scheduled(initialDelayString = "PT5S", fixedDelayString = "PT24H")
    public void run(){

        taskRepository.deleteExpired();
    }
}
