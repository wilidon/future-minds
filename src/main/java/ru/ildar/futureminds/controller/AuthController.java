package ru.ildar.futureminds.controller;


import lombok.RequiredArgsConstructor;
import lombok.experimental.Tolerate;
import org.apache.el.parser.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ildar.futureminds.dto.user.RegisterRequest;
import ru.ildar.futureminds.dto.user.LoginRequest;
import ru.ildar.futureminds.dto.user.LoginResponse;
import ru.ildar.futureminds.dto.user.TokenResponse;
import ru.ildar.futureminds.service.AuthService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest authRequest) {
        final TokenResponse token = authService.register(authRequest);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(token);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest authRequest) {
        final LoginResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }
}