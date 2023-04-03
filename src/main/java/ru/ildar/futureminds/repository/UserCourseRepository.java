package ru.ildar.futureminds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ildar.futureminds.model.Course;
import ru.ildar.futureminds.model.User;
import ru.ildar.futureminds.model.UserCourse;

import java.util.Optional;

public interface UserCourseRepository extends JpaRepository<UserCourse, Integer> {
    Optional<UserCourse> findUserCourseByCourseAndUser(Course course, User user);
}
