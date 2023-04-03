package ru.ildar.futureminds.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    private String title;

    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY)
    private List<Module> modules;

    @JoinColumn(name = "direction_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Direction direction;



}
