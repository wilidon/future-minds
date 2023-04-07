package ru.ildar.futureminds.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@ToString
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max = 64)
    @NotNull
    private String title;
    @Size(max = 50000)
    @NotNull
    private String text;
    @Column(name = "gradient_from")
    @Size(max = 100)
    @NotNull
    private String gradientFrom;
    @Column(name = "gradient_to")
    @Size(max = 100)
    @NotNull
    private String gradientTo;
    @Size(max = 512)
    @NotNull
    private String link;
}
