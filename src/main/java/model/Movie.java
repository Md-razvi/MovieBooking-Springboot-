package model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genre;
    private String description;
    private String language;
    private Integer duration;
    private LocalDate releaseDate;
    @OneToMany(mappedBy = "movie" ,fetch = FetchType.LAZY)
    private List<Show> shows;
}
