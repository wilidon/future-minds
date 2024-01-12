package ru.ildar.futureminds.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ildar.futureminds.domain.dto.review.ReviewRequest;
import ru.ildar.futureminds.model.Review;
import ru.ildar.futureminds.service.ReviewService;

import java.util.List;

@RestController()
@RequestMapping("/api/review")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ModelMapper modelMapper;


    @GetMapping("/")
    public ResponseEntity<List<Review>> findReview() {
        return new ResponseEntity<>(reviewService.findReview(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Review> createReview(@RequestBody @Valid ReviewRequest request) {
        Review review = reviewService.save(convertDTOtoEntity(request));
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Review> updateReview(@RequestBody @Valid ReviewRequest request) {
        Review review = reviewService.save(convertDTOtoEntity(request));
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteReview(@PathVariable int id) {
        reviewService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Review convertDTOtoEntity(ReviewRequest request) {
        return modelMapper.map(request, Review.class);
    }




}
