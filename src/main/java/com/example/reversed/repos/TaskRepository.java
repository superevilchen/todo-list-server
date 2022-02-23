package com.example.reversed.repos;

import com.example.reversed.beans.Group;
import com.example.reversed.beans.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByTitle(String title);

    List<Task> findByGroupType(Group group);

    List<Task> findByWhenToDoBefore(Date date);

    List<Task> findByWhenToDoAfter(Date date);

    @Query(value = "SELECT COUNT(*) FROM tasks", nativeQuery = true)
    Integer getTotalTasks();

    @Query(value = "SELECT * FROM tasks ORDER BY when_to_do ASC", nativeQuery = true)
    List<Task> sortByNearest();

    @Query(value = "SELECT * FROM tasks ORDER BY when_to_do DESC", nativeQuery = true)
    List<Task> sortByFarthest();

    @Query(value = "SELECT * FROM tasks WHERE when_to_do < NOW()", nativeQuery = true)
    List<Task> getExpired();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tasks WHERE when_to_do < now() - interval 30 DAY;", nativeQuery = true)
    void deleteExpired();

}
