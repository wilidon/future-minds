package ru.ildar.futureminds.domain.dto.course;

import lombok.Data;

@Data
public class CourseMainDTO {
    private int id;
    private String title;
    private String description;
    private int duration;
    private int participants;
    private String imageLink;
    private Boolean locked;
}
