package ru.ildar.futureminds.controller;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ildar.futureminds.domain.dto.course.CourseMainDTO;
import ru.ildar.futureminds.exception.ModuleNotFound;
import ru.ildar.futureminds.model.Course;
import ru.ildar.futureminds.model.User;
import ru.ildar.futureminds.service.AuthService;
import ru.ildar.futureminds.service.CourseService;
import ru.ildar.futureminds.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final ModelMapper modelMapper;
    private final AuthService authService;
    private final UserService userService;


    @GetMapping("/")
    public ResponseEntity<?> getCourse() {
        List<Course> courses = courseService.getAllCourses();
        List<CourseMainDTO> courseMainDTOS = new ArrayList<>();
        for (Course course : courses) {
            courseMainDTOS.add(convertToDTO(course));
        }
        return new ResponseEntity<>(courseMainDTOS, HttpStatus.OK);

    }

    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable int courseId) {
        Optional<Course> course = courseService.getCourseById(courseId);
        if (course.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(course.get());
    }

    @PutMapping("/{courseId}/join")
    @PreAuthorize("hasAuthority('USER')")
    @Transactional
    public ResponseEntity<?> joinCourse(@PathVariable int courseId) {
        int user_id = authService.getAuthInfo().getId();
        Optional<User> user = userService.findById(user_id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Course> course = courseService.getCourseById(courseId);
        if (course.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        boolean resultJoin = userService.joinCourse(user.get().getId(), courseId);
        if (!resultJoin)  {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{moduleId}/passed")
    public ResponseEntity<?> passModule(@PathVariable int moduleId) {
        int user_id = authService.getAuthInfo().getId();
        try {
            courseService.passModule(moduleId, user_id);
        } catch (ModuleNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private CourseMainDTO convertToDTO(Course course) {
        return modelMapper.map(course, CourseMainDTO.class);
    }

//    private AnimalTypeDTO convertToDTO(AnimalType animalType) {
//        return modelMapper.map(animalType, AnimalTypeDTO.class);
//    }
}
