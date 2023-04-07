package ru.ildar.futureminds.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ildar.futureminds.model.Review;
import ru.ildar.futureminds.repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> findReview() {
        return reviewRepository.findAll();
    }

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public void delete(int id) {
        reviewRepository.deleteById(id);
    }
}
