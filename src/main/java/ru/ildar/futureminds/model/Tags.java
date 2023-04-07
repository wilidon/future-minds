package ru.ildar.futureminds.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Size(max = 32)
    @NotNull
    private String tag;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JsonBackReference
    private Course course;
}
