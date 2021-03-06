package com.example.reversed.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Group groupType;

    private Date whenToDo;

    @ManyToOne
    private User user;

    public Task(String title, String description, Group groupType, Date whenToDo) {
        this(0, title, description, groupType, whenToDo, null);
    }

    public Task(String title, Group groupType, Date whenToDo) {
        this(0, title, null, groupType, whenToDo, null);
    }
}
