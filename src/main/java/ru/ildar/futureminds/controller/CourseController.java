package ru.ildar.futureminds.controller;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ildar.futureminds.dto.course.DirectionMainDTO;
import ru.ildar.futureminds.model.Direction;
import ru.ildar.futureminds.service.CourseService;

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


    @GetMapping("/")
    public ResponseEntity<?> getCourse() {
        List<Direction> directions = courseService.getAllCourses();
        List<DirectionMainDTO> directionMainDTOS = new ArrayList<>();
        for (Direction direction : directions) {
            directionMainDTOS.add(convertToDTO(direction));
        }
        return new ResponseEntity<>(directionMainDTOS, HttpStatus.OK);

    }

    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable int courseId) {
        Optional<Direction> course = courseService.getCourseById(courseId);
        if (course.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(course.get());
    }

    private DirectionMainDTO convertToDTO(Direction direction) {
        return modelMapper.map(direction, DirectionMainDTO.class);
    }

//    private AnimalTypeDTO convertToDTO(AnimalType animalType) {
//        return modelMapper.map(animalType, AnimalTypeDTO.class);
//    }
}