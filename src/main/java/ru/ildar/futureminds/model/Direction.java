package ru.ildar.futureminds.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    @Size(min = 3, max = 64)
    private String title;
    @Column(name = "description")
    @Size(min = 10, max = 2048)
    private String description;
    @Column(name = "duration")
    @Min(value = 1)
    @Max(value = 999)
    private Integer duration;
    @Min(value = 0)
    @Column(name = "participants")
    private Integer participants;
    @Column(name = "image_link")
    private String imageLink;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "direction")
    private List<Section> sections;
}