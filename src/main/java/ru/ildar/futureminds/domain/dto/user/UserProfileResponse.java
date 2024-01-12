package ru.ildar.futureminds.domain.dto.user;

import lombok.Getter;
import lombok.Setter;
import ru.ildar.futureminds.domain.dto.course.CourseMainDTO;
import ru.ildar.futureminds.domain.dto.Role;

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
