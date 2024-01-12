package ru.ildar.futureminds.domain.dto.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    private String dateBirth;
}
