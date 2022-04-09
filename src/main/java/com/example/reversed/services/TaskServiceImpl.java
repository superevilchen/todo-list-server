package com.example.reversed.services;

import com.example.reversed.beans.Group;
import com.example.reversed.beans.Task;
import com.example.reversed.beans.User;
import com.example.reversed.beans.dtos.TaskDTO;
import com.example.reversed.exceptions.ExceptionState;
import com.example.reversed.exceptions.TaskException;
import com.example.reversed.mappers.Mapper;
import com.example.reversed.repos.TaskRepository;
import com.example.reversed.repos.UserRepository;
import com.example.reversed.security.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import static com.example.reversed.exceptions.ExceptionState.NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final Mapper mapper;
    private final TokenManager tokenManager;
    private final UserRepository userRepository;

    @Override
    public TaskDTO add(TaskDTO task, int userID) throws TaskException {

        if (taskRepository.existsById(task.getId())){
            throw new TaskException(ExceptionState.ALREADY_EXISTS);
        }

        User user = userRepository.findById(userID).orElseThrow(() -> new TaskException(ExceptionState.NOT_EXISTS));

        Task t1 = mapper.mapToEntity(task);
        t1.setUser(user);

        return mapper.mapToObject(taskRepository.saveAndFlush(t1));
    }

    @Override
    public TaskDTO update(long id, TaskDTO task, int userID) throws TaskException {

        if (!taskRepository.existsById(id)){
            throw new TaskException(NOT_EXISTS);
        }

        User user = userRepository.findById(userID).orElseThrow(() -> new TaskException(ExceptionState.NOT_EXISTS));

        Task t1 = mapper.mapToEntity(task);
        t1.setId(id);
        t1.setUser(user);

        return mapper.mapToObject(taskRepository.saveAndFlush(t1));
    }

    @Override
    public void delete(long id, int userID) throws TaskException {

        //TODO - this is the same as update, so need to export it to util

//        if (!taskRepository.existsById(id)){
//            throw new TaskException(NOT_EXISTS);
//        }

        User user = userRepository.findById(userID).orElseThrow(() -> new TaskException(ExceptionState.NOT_EXISTS));
        user.getTasks().remove(taskRepository.findById(id).orElseThrow(() -> new TaskException(NOT_EXISTS)));

        taskRepository.deleteById(id);
    }

    @Override
    public TaskDTO getOne(long id) throws TaskException {
        return mapper.mapToObject(taskRepository.findById(id).orElseThrow(() -> new TaskException(NOT_EXISTS)));
    }

    @Override
    public List<TaskDTO> getAll(int userID) {
        return mapper.mapToObjectList(taskRepository.findByUserId(userID));
    }

    @Override
    public TaskDTO getByTitle(String title) throws TaskException {
        return mapper.mapToObject(taskRepository.findByTitle(title).orElseThrow(() -> new TaskException(NOT_EXISTS)));
    }

    @Override
    public List<TaskDTO> getByGroup(Group group) {
        return mapper.mapToObjectList(taskRepository.findByGroupType(group));
    }

    @Override
    public List<TaskDTO> getBefore(Date date) {
        return mapper.mapToObjectList(taskRepository.findByWhenToDoBefore(date));
    }

    @Override
    public List<TaskDTO> getAfter(Date date) {
        return mapper.mapToObjectList(taskRepository.findByWhenToDoAfter(date));
    }

    @Override
    public int totalTasks() {
        return taskRepository.getTotalTasks();
    }

    @Override
    public List<TaskDTO> sortByNearest() {
        return mapper.mapToObjectList(taskRepository.sortByNearest());
    }

    @Override
    public List<TaskDTO> sortByFarthest() {
        return mapper.mapToObjectList(taskRepository.sortByFarthest());
    }

    @Override
    public List<TaskDTO> getAllExpired() {
        return mapper.mapToObjectList(taskRepository.getExpired());
    }
}
