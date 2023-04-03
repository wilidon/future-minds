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
public class UserDirections {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;

    @ManyToOne
    @JoinColumn(name = "direction_id")
    @JsonIgnore
    Direction direction;

    @JsonIgnore
    LocalDateTime registeredAt;


    public UserDirections(User user, Direction direction) {
        this.user = user;
        this.direction = direction;
    }
}