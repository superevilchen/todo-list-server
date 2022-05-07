package com.example.reversed.bootstrap;

import com.example.reversed.beans.ClientType;
import com.example.reversed.beans.Group;
import com.example.reversed.beans.Task;
import com.example.reversed.beans.User;
import com.example.reversed.beans.dtos.TaskDTO;
import com.example.reversed.exceptions.TaskException;
import com.example.reversed.repos.UserRepository;
import com.example.reversed.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataMocker implements CommandLineRunner {

    private final TaskService taskService;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

//        void add(Task task) throws TaskException;

        User user1 = new User("user@u.com", "1234", ClientType.USER);
        User user2 = new User("user2@u.com", "1234", ClientType.USER);
        User user3 = new User("admin@a.com", "1234", ClientType.ADMIN);

        userRepository.save(user1);
//        userRepository.save(user2);
        userRepository.save(user3);

        TaskDTO task1 =
                TaskDTO.builder()
                .title("task1")
                .group(Group.REACT)
                .when(Date.valueOf(LocalDate.of(2023, 4, 5)))
                .build();

        TaskDTO task2 =
                TaskDTO.builder()
                .title("task2")
                .description("afeafgdg")
                .group(Group.JAVA)
                .when(Date.valueOf(LocalDate.of(2022, 7, 11)))
                .build();

        TaskDTO task3 =
                TaskDTO.builder()
                .title("task3")
                .description("matan")
                .group(Group.REACT)
                .when(Date.valueOf(LocalDate.of(2022, 9, 11)))
                .build();

        taskService.add(task1, 1);
        taskService.add(task2, 1);
//        taskService.add(task3, 2);

//        void update(long id, Task task) throws TaskException;

        task1.setDescription("matan bo lechol");

        taskService.update(1, task1, 1);

//        void delete(long id) throws TaskException;

//        taskService.delete(2, 1);

//        Task getOne(long id) throws TaskException;

        System.out.println(taskService.getOne(1));

//        List<Task> getAll();

        System.out.println(taskService.getAll(1));

//        Task getByTitle(String title) throws TaskException;
//
//        System.out.println(taskService.getByTitle("task3"));
//
////        List<Task> getByGroup(Group group);
//
//        System.out.println(taskService.getByGroup(Group.REACT));
//
////        List<Task> getBefore(Date date);
//
//        System.out.println(taskService.getBefore(Date.valueOf(LocalDate.of(2050, 1, 1))));
//
////        List<Task> getAfter(Date date);
//
//        System.out.println(taskService.getAfter(Date.valueOf(LocalDate.of(2022, 10, 10))));
//
////        int totalTasks();
//
//        System.out.println(taskService.totalTasks());
//
////        List<Task> sortByNearest();
//
//        System.out.println(taskService.sortByNearest());
//
////        List<Task> sortByFarthest();
//
//        System.out.println(taskService.sortByFarthest());

//        List<Task> getAllExpired();

    }
}
