package ru.ildar.futureminds.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Setter
@Getter
@ToString
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(max = 1024)
    @NotNull
    private String text;
    @Column(name = "from_user")
    @NotNull
    private String fromUser;
    @Column(name = "from_role")
    @NotNull
    private String fromRole;
    @Column(name = "completed_course")
    @NotNull
    private String completedCourse;
}
