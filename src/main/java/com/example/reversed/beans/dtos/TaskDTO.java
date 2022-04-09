package com.example.reversed.beans.dtos;

import com.example.reversed.beans.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private long id;
    private String title;
    private String description;
    private Group group;
    private Date when;
}
