package ru.ildar.futureminds.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<Object> handlerAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Access denied message here", new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({AuthException.class})
    protected ResponseEntity<Object> handlerNotFoundUser(AuthException ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "User not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
