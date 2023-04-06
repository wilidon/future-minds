package ru.ildar.futureminds.dto.user;

import lombok.Getter;
import lombok.Setter;
import ru.ildar.futureminds.dto.Role;
import ru.ildar.futureminds.dto.course.CourseMainDTO;
import ru.ildar.futureminds.model.Course;

import java.util.List;
import java.util.Set;


@Setter
@Getter
public class UserProfileResponse {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    private String birthday;
    private Set<Role> roles;
    private List<CourseMainDTO> courses;
}
