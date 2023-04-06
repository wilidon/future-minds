package ru.ildar.futureminds.repository;


import org.springframework.data.repository.CrudRepository;
import ru.ildar.futureminds.model.News;

public interface NewsRepository extends CrudRepository<News, Integer> {

}
