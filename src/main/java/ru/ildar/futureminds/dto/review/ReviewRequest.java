package ru.ildar.futureminds.dto.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequest {
    private String title;
    private String text;
    private String fromUser;
    private String fromRole;
    private String completedCourse;
}
