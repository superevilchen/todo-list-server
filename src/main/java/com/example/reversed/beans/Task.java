package com.example.reversed.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

//    @NotBlank
//    @NotNull
    @Column(unique = true, updatable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Group groupType;

//    @Future
//    @Column(name="WhenToDo")
    private Date whenToDo;

    public Task(String title, String description, Group groupType, Date whenToDo) {
        this(0, title, description, groupType, whenToDo);
    }

    public Task(String title, Group groupType, Date whenToDo) {
        this(0, title, null, groupType, whenToDo);
    }
}
