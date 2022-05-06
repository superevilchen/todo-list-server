package com.example.reversed.mappers;

import com.example.reversed.beans.Task;
import com.example.reversed.beans.dtos.TaskDTO;

import java.util.List;

public interface Mapper {

    Task mapToEntity(TaskDTO taskDTO);
    TaskDTO mapToObject(Task task);
    List<TaskDTO> mapToObjectList(List<Task> taskList);
}
