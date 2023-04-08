package ru.ildar.futureminds.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ildar.futureminds.repository.UserRepository;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@ToString
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    @Size(min = 3, max = 32)
    @NotNull
    private String title;

    @Column(name = "text", length = 50000)
    @Size(max = 50000)
    @NotNull
    private String text;

    @JoinColumn(name = "section_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Section section;

}
