package com.example.reversed.mappers;

import com.example.reversed.beans.Task;
import com.example.reversed.beans.dtos.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapperImpl implements Mapper {

    @Override
    public Task mapToEntity(TaskDTO taskDTO) {
        return Task.builder()
                .id(taskDTO.getId())
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .groupType(taskDTO.getGroup())
                .whenToDo(taskDTO.getWhen()).build();
    }

    @Override
    public TaskDTO mapToObject(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .group(task.getGroupType())
                .when(task.getWhenToDo()).build();
    }

    @Override
    public List<TaskDTO> mapToObjectList(List<Task> taskList) {
        return taskList.stream().map(this::mapToObject).collect(Collectors.toList());
    }
}
