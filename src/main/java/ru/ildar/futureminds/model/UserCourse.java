package ru.ildar.futureminds.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;

    @JsonIgnore
    LocalDateTime registeredAt;


    public UserCourse(User user, Course course) {
        this.user = user;
        this.course = course;
    }
}