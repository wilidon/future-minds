package ru.ildar.futureminds.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.ildar.futureminds.exception.ModuleNotFound;
import ru.ildar.futureminds.model.Course;
import ru.ildar.futureminds.model.Module;
import ru.ildar.futureminds.repository.CourseRepository;
import ru.ildar.futureminds.repository.ModuleRepository;
import ru.ildar.futureminds.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final UserRepository userRepository;


    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(int id) {
        return courseRepository.findByIdOrderByIdDesc(id);
    }


    public void passModule(int moduleId, int user_id) throws ModuleNotFound {
        // Хуже решения не видел еще
        // ну с другом стороны это гарантирует, что записи уникальные
//        Module module = moduleRepository.findById(moduleId).orElseThrow(() -> new ModuleNotFound());
//        module.getPassed_users().clear();
//        module.getPassed_users().add(userRepository.findById(user_id).get());
//        moduleRepository.save(module);
    }



}
