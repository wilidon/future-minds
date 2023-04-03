package ru.ildar.futureminds.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ildar.futureminds.dto.course.CourseMainDTO;
import ru.ildar.futureminds.dto.user.ProfileRequest;
import ru.ildar.futureminds.exception.UserNotFound;
import ru.ildar.futureminds.model.Course;
import ru.ildar.futureminds.model.User;
import ru.ildar.futureminds.service.AuthService;
import ru.ildar.futureminds.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {
    private final ModelMapper modelMapper;
    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> getCurrentUser() {
        int user_id = authService.getAuthInfo().getId();
        User user = userService.findById(user_id).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileRequest profile) {
        int user_id = authService.getAuthInfo().getId();
        try {
            userService.updateProfile(profile, user_id);
        }
        catch (UserNotFound userNotFound) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{user_id}")
    public ResponseEntity<?> getAllCourse(@PathVariable int user_id) {
        User user = userService.findById(user_id).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/{user_id}/courses")
    public ResponseEntity<?> getAllCourseByUser_id(@PathVariable int user_id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private CourseMainDTO convertToDTO(Course course) {
        return modelMapper.map(course, CourseMainDTO.class);
    }

    private User convertToEntity(ProfileRequest profileRequest) {
        return modelMapper.map(profileRequest, User.class);
    }
}
