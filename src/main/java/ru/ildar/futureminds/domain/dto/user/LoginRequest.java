package ru.ildar.futureminds.domain.dto.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {

    private String login;
    private String password;

}
