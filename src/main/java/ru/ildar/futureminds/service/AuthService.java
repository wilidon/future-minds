package ru.ildar.futureminds.service;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ildar.futureminds.domain.dto.Role;
import ru.ildar.futureminds.domain.dto.user.*;
import ru.ildar.futureminds.exception.AuthException;
import ru.ildar.futureminds.model.User;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Transactional
    public TokenResponse register(@NotNull RegisterRequest registerRequest) {
        if (userService.findByLogin(registerRequest.getEmail()).isPresent()) {
            return null;
        }
        final User newUser = new User(registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getName(),
                registerRequest.getSurname(),
                registerRequest.getMiddleName(),
                registerRequest.getDateBirth(),
                Collections.singleton(Role.USER));
        final User user = userService.save(newUser);
        final String accessToken = jwtProvider.generateAccessToken(user);
        return new TokenResponse(accessToken);
    }

    public LoginResponse login(@NonNull LoginRequest authRequest) {
        final User user = userService.findByLogin(authRequest.getLogin())
                .orElseThrow(() -> new AuthException("Пользователь не найден"));
        if (user.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            LoginResponse loginResponse = new LoginResponse(
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getMiddleName(),
                    user.getBirthday(),
                    accessToken
            );
            return loginResponse;
        } else {
            throw new AuthException("Неправильный пароль");
        }
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
