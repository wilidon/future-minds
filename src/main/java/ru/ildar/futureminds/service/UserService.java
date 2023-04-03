package ru.ildar.futureminds.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import ru.ildar.futureminds.model.Course;
import ru.ildar.futureminds.model.User;
import ru.ildar.futureminds.model.UserCourse;
import ru.ildar.futureminds.repository.UserCourseRepository;
import ru.ildar.futureminds.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CourseService courseService;
    private final UserCourseRepository userCourseRepository;

    public Optional<User> getById(@NonNull int id) {
        return userRepository.findById(id);
    }
    public Optional<User> getByLogin(@NonNull String email) {
        return userRepository.findByEmail(email);
    }
    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean joinCourse(int user_id, int course_id) {

        User user = userRepository.findById(user_id).orElse(null);
        Course course = courseService.getCourseById(course_id).orElse(null);
        if (userCourseRepository.findUserCourseByCourseAndUser(course, user).isPresent()) {
            return false;
        }
        userCourseRepository.save(new UserCourse(user, course));

        return true;
    }

    public List<Course> findUserCourses(int user_id) {
        Optional<User> user = userRepository.findById(user_id);
        return null;
    }

}