package ru.ildar.futureminds.controller;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ildar.futureminds.dto.course.DirectionMainDTO;
import ru.ildar.futureminds.dto.user.JwtAuthentication;
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
        List<DirectionMainDTO> directionMainDTOS = new ArrayList<>();
        for (Course course : courses) {
            directionMainDTOS.add(convertToDTO(course));
        }
        return new ResponseEntity<>(directionMainDTOS, HttpStatus.OK);

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
    @Transactional
    public ResponseEntity<?> joinCourse(@PathVariable int courseId) {
        final JwtAuthentication authInfo = authService.getAuthInfo();
        String userEmail = (String) authInfo.getPrincipal();
        Optional<User> user = userService.getByLogin(userEmail);
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

    private DirectionMainDTO convertToDTO(Course course) {
        return modelMapper.map(course, DirectionMainDTO.class);
    }

//    private AnimalTypeDTO convertToDTO(AnimalType animalType) {
//        return modelMapper.map(animalType, AnimalTypeDTO.class);
//    }
}
