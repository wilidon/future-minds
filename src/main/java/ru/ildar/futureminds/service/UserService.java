package ru.ildar.futureminds.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import ru.ildar.futureminds.model.User;
import ru.ildar.futureminds.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> getByLogin(@NonNull String email) {
        return userRepository.findByEmail(email);
    }
    public User save(User user) {
        return userRepository.save(user);
    };

}