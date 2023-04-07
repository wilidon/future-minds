package ru.ildar.futureminds.dto.news;


import lombok.Data;

@Data
public class NewsDTO {
    private String title;
    private String text;
    private String gradientTo;
    private String gradientFrom;
}
