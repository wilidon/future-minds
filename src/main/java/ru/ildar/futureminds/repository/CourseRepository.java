package ru.ildar.futureminds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ildar.futureminds.model.Direction;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Direction, Integer> {
    Optional<Direction> findById(int id);
}
