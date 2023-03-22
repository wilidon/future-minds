package ru.ildar.futureminds.dto.user;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
public class RegisterRequest {
    private String email;
    private String password;
    private String surname;
    private String name;
    private String middleName;
    private Date dateBirth;
    private boolean sending;
    private String referral;
}

