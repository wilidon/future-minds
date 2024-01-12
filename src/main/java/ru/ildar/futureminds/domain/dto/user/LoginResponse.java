package ru.ildar.futureminds.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private String email;
    private String name;
    private String surname;
    private String middleName;
    private Date dateBirth;
    private final String type = "Bearer";
    private String accessToken;

}
