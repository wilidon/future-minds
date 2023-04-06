package ru.ildar.futureminds.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ildar.futureminds.model.News;
import ru.ildar.futureminds.service.NewsService;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/news")
    public ResponseEntity<?> news(int offset, int limit) {
        List<News> newsList = newsService.findNews(offset, limit);
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @GetMapping("/news/{link}")
    public ResponseEntity<?> newsByLink(String link) {
        Optional<News> news = newsService.findNewsByLink(link);
        if (news.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

}
