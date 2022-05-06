package com.example.reversed.controllers;

import com.example.reversed.beans.Group;
import com.example.reversed.beans.Task;
import com.example.reversed.beans.dtos.TaskDTO;
import com.example.reversed.exceptions.TaskException;
import com.example.reversed.security.TokenManager;
import com.example.reversed.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/todolist/")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TokenManager tokenManager;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO add(@RequestBody @Valid TaskDTO task, @RequestHeader("Authorization")UUID token) throws TaskException {
        return taskService.add(task, tokenManager.getUserId(token));
    }

    @PutMapping("{id}")
    public TaskDTO update(@PathVariable long id, @RequestBody @Valid TaskDTO task, @RequestHeader("Authorization") UUID token) throws TaskException {
        return taskService.update(id, task, tokenManager.getUserId(token));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id, @RequestHeader("Authorization") UUID token) throws TaskException {
        taskService.delete(id, tokenManager.getUserId(token));
    }

    @GetMapping
    public List<TaskDTO> getAll(@RequestHeader("Authorization") UUID token) throws TaskException {
        return taskService.getAll(tokenManager.getUserId(token));
    }

    // currently not in use:

    @GetMapping("{id}")
    public TaskDTO getOne(@PathVariable long id) throws TaskException {
        return taskService.getOne(id);
    }

    @GetMapping("title")
    public TaskDTO getByTitle(@RequestParam String title) throws TaskException {
        return taskService.getByTitle(title);
    }

    @GetMapping("group")
    public List<TaskDTO> getByGroup(@RequestParam Group group){
        return taskService.getByGroup(group);
    }

    @GetMapping("before")
    public List<TaskDTO> getBefore(@RequestParam Date date){
        return taskService.getBefore(date);
    }

    @GetMapping("after")
    public List<TaskDTO> getAfter(@RequestParam Date date){
        return taskService.getAfter(date);
    }

    @GetMapping("total")
    public int getTotalTasks(){
        return taskService.totalTasks();
    }

    @GetMapping("nearest")
    public List<TaskDTO> sortByNearest(){
        return taskService.sortByNearest();
    }

    @GetMapping("farthest")
    public List<TaskDTO> sortByFarthest(){
        return taskService.sortByFarthest();
    }

    @GetMapping("expired")
    public List<TaskDTO> getAllExpired(){
        return taskService.getAllExpired();
    }
}
