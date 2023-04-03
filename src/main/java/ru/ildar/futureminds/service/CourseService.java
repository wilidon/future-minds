package ru.ildar.futureminds.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ildar.futureminds.model.Course;
import ru.ildar.futureminds.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;


    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(int id) {
        return courseRepository.findById(id);
    }



}
