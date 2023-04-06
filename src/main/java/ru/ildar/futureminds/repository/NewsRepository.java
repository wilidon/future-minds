package ru.ildar.futureminds.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ildar.futureminds.model.News;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Integer> {
    @Query(value = "SELECT n.* from News n ORDER BY n.id ASC offset ?1 limit ?2", nativeQuery = true)
    List<News> findNews(int offset, int limit);


    Optional<News> findByLink(String link);
}
