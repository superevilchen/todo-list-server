package com.example.reversed.utils;

import com.example.reversed.beans.Task;
import com.example.reversed.exceptions.ExceptionState;
import com.example.reversed.repos.TaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.function.Function;

import static com.example.reversed.exceptions.ExceptionState.*;

public interface TaskValidator extends Function<Task, ExceptionState> {

//    // for add
//    static <T> TaskValidator isNotExistsById(JpaRepository<T, Long> repo){
//        return task -> !repo.existsById(task.getId()) ? SUCCESS : ALREADY_EXISTS;
//    }
//
//    // for delete, update
//    static <T> TaskValidator isExistsById(JpaRepository<T, Long> repo){
//        return task -> repo.existsById(task.getId()) ? SUCCESS : NOT_EXISTS;
//    }

//    static TaskValidator isUpdatedDateValid(){
//        return task -> task.getWhen().after(Date.valueOf(LocalDate.now())) ? SUCCESS : INVALID_DATE;
//    }
//
//    static TaskValidator isUpdatedTitleValid(){
//        return task ->
//    }
}
