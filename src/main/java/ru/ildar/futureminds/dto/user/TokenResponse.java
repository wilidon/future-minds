package ru.ildar.futureminds.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponse {

    private final String type = "Bearer";
    private String accessToken;
    // private String refreshToken;

}
