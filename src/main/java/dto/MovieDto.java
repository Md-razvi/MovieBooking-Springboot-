package dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class MovieDto {
    private String name;
    private String genre;
    private String description;
    private String language;
    private Integer duration;
    private LocalDate releaseDate;
}
