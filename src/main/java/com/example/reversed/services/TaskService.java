package com.example.reversed.services;

import com.example.reversed.beans.Group;
import com.example.reversed.beans.Task;
import com.example.reversed.beans.dtos.TaskDTO;
import com.example.reversed.exceptions.TaskException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface TaskService {

    TaskDTO add(TaskDTO task, int userID) throws TaskException;
    TaskDTO update(long id, TaskDTO task, int userID) throws TaskException;
    void delete(long id, int userID) throws TaskException;
    TaskDTO getOne(long id) throws TaskException;
    List<TaskDTO> getAll(int userID);
    TaskDTO getByTitle(String title) throws TaskException;
    List<TaskDTO> getByGroup(Group group);
    List<TaskDTO> getBefore(Date date);
    List<TaskDTO> getAfter(Date date);
    int totalTasks();
    List<TaskDTO> sortByNearest();
    List<TaskDTO> sortByFarthest();
    List<TaskDTO> getAllExpired();
}
