package ru.ildar.futureminds.service;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

import ru.ildar.futureminds.model.Direction;
import ru.ildar.futureminds.model.User;
import ru.ildar.futureminds.model.UserDirections;
import ru.ildar.futureminds.repository.UserDirectionsRepository;
import ru.ildar.futureminds.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CourseService courseService;
    private final UserDirectionsRepository userDirectionsRepository;

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
        Direction direction = courseService.getCourseById(course_id).orElse(null);
        if (userDirectionsRepository.findUserDirectionsByDirectionAndUser(direction, user).isPresent()) {
            return false;
        }
        userDirectionsRepository.save(new UserDirections(user, direction));

        return true;
    }

}