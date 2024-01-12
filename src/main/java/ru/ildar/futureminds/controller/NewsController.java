package ru.ildar.futureminds.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ildar.futureminds.domain.dto.news.NewsDTO;
import ru.ildar.futureminds.model.News;
import ru.ildar.futureminds.service.NewsService;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/news")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class NewsController {

    private final ModelMapper modelMapper;
    private final NewsService newsService;

    @GetMapping("/")
    public ResponseEntity<List<News>> news(int offset, int limit) {
        List<News> newsList = newsService.findNews(offset, limit);
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @GetMapping("/{link}")
    public ResponseEntity<News> newsByLink(String link) {
        Optional<News> news = newsService.findNewsByLink(link);
        if (news.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(news.get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<News> createNews(@RequestBody NewsDTO newsDTO) {
        News news = newsService.createNews(convertDtoToEntity(newsDTO));
        return new ResponseEntity<>(news, HttpStatus.OK);
    }


    @PutMapping("/{link}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<News> updateNews(@RequestBody NewsDTO newsDTO) {
        News news = newsService.updateNews(convertDtoToEntity(newsDTO));
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @DeleteMapping("/{link}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteNews(@PathVariable String link) {
        newsService.deleteNewsByLink(link);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private News convertDtoToEntity(NewsDTO newsDTO) {
       return modelMapper.map(newsDTO, News.class);
    }

}
