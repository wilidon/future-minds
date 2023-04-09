package ru.ildar.futureminds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ildar.futureminds.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByIdOrderByIdDesc(int id);
    List<Course> findAllByOrderByIdAsc();
}
