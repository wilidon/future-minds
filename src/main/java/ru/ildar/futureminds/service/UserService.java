package ru.ildar.futureminds.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import ru.ildar.futureminds.domain.dto.user.ProfileRequest;
import ru.ildar.futureminds.exception.UserNotFound;
import ru.ildar.futureminds.model.Course;
import ru.ildar.futureminds.model.User;
import ru.ildar.futureminds.model.UserCourse;
import ru.ildar.futureminds.repository.CourseRepository;
import ru.ildar.futureminds.repository.UserCourseRepository;
import ru.ildar.futureminds.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CourseService courseService;
    private final UserCourseRepository userCourseRepository;
    private final CourseRepository courseRepository;

    public Optional<User> findById(@NonNull int id) {
        return userRepository.findById(id);
    }
    public Optional<User> findByLogin(@NonNull String email) {
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
        course.setParticipants(course.getParticipants() + 1);
        courseRepository.save(course);
        userCourseRepository.save(new UserCourse(user, course));

        return true;
    }

    public List<Course> findUserCourses(int user_id) {
        Optional<User> user = userRepository.findById(user_id);
        return null;
    }


    public boolean updateProfile(ProfileRequest profileRequest, int user_id) throws UserNotFound {
        User user = findById(user_id).orElseThrow(() -> new UserNotFound());
        user.setFirstName(profileRequest.getFirstName());
        user.setLastName(profileRequest.getLastName());
        user.setMiddleName(profileRequest.getMiddleName());
        user.setEmail(profileRequest.getEmail());
        save(user);
        // TODO add birthday
        // user.setBirthday();
        return true;
    }

}