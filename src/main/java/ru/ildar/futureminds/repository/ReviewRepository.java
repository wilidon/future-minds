package ru.ildar.futureminds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ildar.futureminds.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {


}
