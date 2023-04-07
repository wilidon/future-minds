package ru.ildar.futureminds.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    USER("USER"),
    MODERATOR("MODERATOR"),
    ADMIN("ADMIN");


    private final String vale;

    @Override
    public String getAuthority() {
        return vale;
    }

}
