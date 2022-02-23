package com.example.reversed.services;

import com.example.reversed.beans.Group;
import com.example.reversed.beans.Task;
import com.example.reversed.exceptions.ExceptionState;
import com.example.reversed.exceptions.TaskException;
import com.example.reversed.repos.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import static com.example.reversed.exceptions.ExceptionState.NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    @Override
    public void add(Task task) throws TaskException {

        if (taskRepository.existsById(task.getId())){
            throw new TaskException(ExceptionState.ALREADY_EXISTS);
        }

        taskRepository.save(task);
    }

    @Override
    public void update(long id, Task task) throws TaskException {

        if (!taskRepository.existsById(id)){
            throw new TaskException(NOT_EXISTS);
        }

        taskRepository.saveAndFlush(task);

    }

    @Override
    public void delete(long id) throws TaskException {

        //TODO - this is the same as update, so need to export it to util

        if (!taskRepository.existsById(id)){
            throw new TaskException(NOT_EXISTS);
        }

        taskRepository.deleteById(id);

    }

    @Override
    public Task getOne(long id) throws TaskException {
        return taskRepository.findById(id).orElseThrow(() -> new TaskException(NOT_EXISTS));
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task getByTitle(String title) throws TaskException {
        return taskRepository.findByTitle(title).orElseThrow(() -> new TaskException(NOT_EXISTS));
    }

    @Override
    public List<Task> getByGroup(Group group) {
        return taskRepository.findByGroupType(group);
    }

    @Override
    public List<Task> getBefore(Date date) {
        return taskRepository.findByWhenToDoBefore(date);
    }

    @Override
    public List<Task> getAfter(Date date) {
        return taskRepository.findByWhenToDoAfter(date);
    }

    @Override
    public int totalTasks() {
        return taskRepository.getTotalTasks();
    }

    @Override
    public List<Task> sortByNearest() {
        return taskRepository.sortByNearest();
    }

    @Override
    public List<Task> sortByFarthest() {
        return taskRepository.sortByFarthest();
    }

    @Override
    public List<Task> getAllExpired() {
        return taskRepository.getExpired();
    }
}
