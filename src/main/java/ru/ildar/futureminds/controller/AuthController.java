package ru.ildar.futureminds.controller;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ildar.futureminds.domain.dto.user.RegisterRequest;
import ru.ildar.futureminds.domain.dto.user.LoginRequest;
import ru.ildar.futureminds.domain.dto.user.LoginResponse;
import ru.ildar.futureminds.domain.dto.user.TokenResponse;
import ru.ildar.futureminds.service.AuthService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    Logger logger = LoggerFactory.getLogger(AuthController.class);



    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest authRequest) {
        try {
            final TokenResponse token = authService.register(authRequest);
            if (token == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(token);

        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest authRequest) {
        try {
            final LoginResponse token = authService.login(authRequest);
            return ResponseEntity.ok(token);
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}