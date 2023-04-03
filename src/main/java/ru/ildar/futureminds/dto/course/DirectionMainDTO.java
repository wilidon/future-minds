package ru.ildar.futureminds.dto.course;

import lombok.Data;

@Data
public class DirectionMainDTO {
    private int id;
    private String title;
    private String description;
    private int duration;
    private int participants;
    private String imageLink;
}
