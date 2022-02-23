package com.example.reversed.services;

import com.example.reversed.beans.Group;
import com.example.reversed.beans.Task;
import com.example.reversed.exceptions.TaskException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface TaskService {

    void add(Task task) throws TaskException;
    void update(long id, Task task) throws TaskException;
    void delete(long id) throws TaskException;
    Task getOne(long id) throws TaskException;
    List<Task> getAll();
    Task getByTitle(String title) throws TaskException;
    List<Task> getByGroup(Group group);
    List<Task> getBefore(Date date);
    List<Task> getAfter(Date date);
    int totalTasks();
    List<Task> sortByNearest();
    List<Task> sortByFarthest();
    List<Task> getAllExpired();
}
