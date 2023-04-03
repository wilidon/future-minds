package ru.ildar.futureminds.dto.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequest {
    private String email;
    private String name;
    private String lastName;
    private String middleName;
    private String dateBirth;
    private String accessToken;
}
