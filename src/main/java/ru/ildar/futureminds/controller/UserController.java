package ru.ildar.futureminds.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ildar.futureminds.model.User;
import ru.ildar.futureminds.service.AuthService;
import ru.ildar.futureminds.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> getCurrentUser() {
        String email = (String) authService.getAuthInfo().getPrincipal();
        User user = userService.getByLogin(email).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/course")
    public ResponseEntity<?> getCurrentUse2r() {
        String email = (String) authService.getAuthInfo().getPrincipal();
        User user = userService.getByLogin(email).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.getUserDirections(), HttpStatus.OK);
    }


    @GetMapping("/{user_id}")
    public ResponseEntity<?> getAllCourse(@PathVariable int user_id) {
        User user = userService.getById(user_id).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/{user_id}/courses")
    public ResponseEntity<?> getAllCourseByUser_id(@PathVariable int user_id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
