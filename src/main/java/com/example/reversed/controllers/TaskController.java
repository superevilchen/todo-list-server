package com.example.reversed.controllers;

import com.example.reversed.beans.Group;
import com.example.reversed.beans.Task;
import com.example.reversed.exceptions.TaskException;
import com.example.reversed.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/todolist/")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid Task task) throws TaskException {
        taskService.add(task);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long id, @RequestBody @Valid Task task) throws TaskException {
        taskService.update(id, task);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) throws TaskException {
        taskService.delete(id);
    }

    @GetMapping("{id}")
    public Task getOne(@PathVariable long id) throws TaskException {
        return taskService.getOne(id);
    }

    @GetMapping
    public List<Task> getAll(){
        return taskService.getAll();
    }

    @GetMapping("title")
    public Task getByTitle(@RequestParam String title) throws TaskException {
        return taskService.getByTitle(title);
    }

    @GetMapping("group")
    public List<Task> getByGroup(@RequestParam Group group){
        return taskService.getByGroup(group);
    }

    @GetMapping("before")
    public List<Task> getBefore(@RequestParam Date date){
        return taskService.getBefore(date);
    }

    @GetMapping("after")
    public List<Task> getAfter(@RequestParam Date date){
        return taskService.getAfter(date);
    }

    @GetMapping("total")
    public int getTotalTasks(){
        return taskService.totalTasks();
    }

    @GetMapping("nearest")
    public List<Task> sortByNearest(){
        return taskService.sortByNearest();
    }

    @GetMapping("farthest")
    public List<Task> sortByFarthest(){
        return taskService.sortByFarthest();
    }

    @GetMapping("expired")
    public List<Task> getAllExpired(){
        return taskService.getAllExpired();
    }
}
