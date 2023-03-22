package ru.ildar.futureminds.service;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ildar.futureminds.dto.Role;
import ru.ildar.futureminds.dto.user.JwtAuthentication;
import ru.ildar.futureminds.dto.user.RegisterRequest;
import ru.ildar.futureminds.dto.user.LoginRequest;
import ru.ildar.futureminds.dto.user.TokenResponse;
import ru.ildar.futureminds.model.User;
import ru.ildar.futureminds.exception.AuthException;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Transactional
    public TokenResponse register(@NotNull RegisterRequest registerRequest) {
        if (userService.getByLogin(registerRequest.getEmail()).isPresent()) {
            return null;
        }
        final User newUser = new User(registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getName(),
                registerRequest.getSurname(),
                registerRequest.getMiddleName(),
                Collections.singleton(Role.USER));
        final User user = userService.save(newUser);
        final String accessToken = jwtProvider.generateAccessToken(user);
        return new TokenResponse(accessToken);
    }

    public TokenResponse login(@NonNull LoginRequest authRequest) {
        final User user = userService.getByLogin(authRequest.getLogin())
                .orElseThrow(() -> new AuthException("Пользователь не найден"));
        if (user.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            return new TokenResponse(accessToken);
        } else {
            throw new AuthException("Неправильный пароль");
        }
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
