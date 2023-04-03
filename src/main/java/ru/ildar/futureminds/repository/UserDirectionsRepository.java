package ru.ildar.futureminds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ildar.futureminds.model.Direction;
import ru.ildar.futureminds.model.User;
import ru.ildar.futureminds.model.UserDirections;

import java.util.Optional;

public interface UserDirectionsRepository extends JpaRepository<UserDirections, Integer> {
    Optional<UserDirections> findUserDirectionsByDirectionAndUser(Direction direction, User user);
}
