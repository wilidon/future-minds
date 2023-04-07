package ru.ildar.futureminds.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@ToString
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    @Size(min = 3, max = 32)
    @NotNull
    private String title;

    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY)
    @OrderBy("id ASC")
    private List<Module> modules;

    @JoinColumn(name = "course_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Course course;



}
