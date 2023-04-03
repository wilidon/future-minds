package ru.ildar.futureminds.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ildar.futureminds.repository.ReviewRepository;
import ru.ildar.futureminds.service.ReviewService;

@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    @GetMapping("/review")
    public ResponseEntity<?> findReview() {
        return new ResponseEntity<>(reviewService.findReview(), HttpStatus.OK);
    }
}
