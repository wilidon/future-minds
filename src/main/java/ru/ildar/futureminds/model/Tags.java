package ru.ildar.futureminds.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tag;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JsonBackReference
    private Course course;
}
