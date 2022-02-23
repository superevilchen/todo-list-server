package com.example.reversed.bootstrap;

import com.example.reversed.beans.Group;
import com.example.reversed.beans.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class RestTest implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private final String BASE = "http://localhost:8080/api/v1/todolist/";

    @Override
    public void run(String... args) throws Exception {

//        void add(Task task) throws TaskException;

        Task task = Task
                .builder()
                .title("task5")
                .description("faagdgsdgg")
                .groupType(Group.HTML)
                .whenToDo(Date.valueOf(LocalDate.of(2022, 12, 24)))
                .build();

        ResponseEntity<String> add = restTemplate.postForEntity(BASE, task, String.class);
        System.out.println(add.getStatusCode().is2xxSuccessful() ? "added" : "adding failed");

//        void update(long id, Task task) throws TaskException;

        task.setDescription("vsffr");

        HttpEntity<Task> taskHttpEntity = new HttpEntity<Task>(task);
        ResponseEntity<String> update = restTemplate.exchange(BASE+4, HttpMethod.PUT, taskHttpEntity, String.class);
        System.out.println(update.getStatusCode().is2xxSuccessful() ? "updated" : "updating failed");

//        void delete(long id) throws TaskException;

        ResponseEntity<String> delete = restTemplate.exchange(BASE+3, HttpMethod.DELETE, null, String.class);
        System.out.println(delete.getStatusCode().is2xxSuccessful() ? "deleted" : "deleing failed");

//        Task getOne(long id) throws TaskException;

        Task getOne = restTemplate.getForObject(BASE+1, Task.class);
        System.out.println(getOne);

//        List<Task> getAll();

        Task[] getAll = restTemplate.getForObject(BASE, Task[].class);
        System.out.println(Arrays.toString(getAll));

//        Task getByTitle(String title) throws TaskException;

        Task getByTitle = restTemplate.getForObject(BASE+"title?title=task1", Task.class);
        System.out.println(getByTitle);

//        List<Task> getByGroup(Group group);

        Task[] getByGroup = restTemplate.getForObject(BASE+"group?group=REACT", Task[].class);
        System.out.println(Arrays.toString(getByGroup));

//        List<Task> getBefore(Date date);

        Task[] getBefore = restTemplate.getForObject(BASE+"before?date=2050-1-1", Task[].class);
        System.out.println(Arrays.toString(getBefore));

//        List<Task> getAfter(Date date);

        Task[] getAfter = restTemplate.getForObject(BASE+"after?date=2022-10-10", Task[].class);
        System.out.println(Arrays.toString(getAfter));

//        int totalTasks();

        Integer totalTasks = restTemplate.getForObject(BASE+"total", Integer.class);
        System.out.println(totalTasks);

//        List<Task> sortByNearest();

        Task[] sortByNearest = restTemplate.getForObject(BASE+"nearest", Task[].class);
        System.out.println(Arrays.toString(sortByNearest));

//        List<Task> sortByFarthest();

        Task[] sortByFarthest = restTemplate.getForObject(BASE+"farthest", Task[].class);
        System.out.println(Arrays.toString(sortByFarthest));

//        List<Task> getAllExpired();

        Task[] getAllExpired = restTemplate.getForObject(BASE+"expired", Task[].class);
        System.out.println(Arrays.toString(getAllExpired));

    }
}
