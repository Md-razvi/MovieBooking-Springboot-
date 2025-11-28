package controller;

import dto.MovieDto;
import model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    //Using Field injection here
    @Autowired
    private MovieService movieService;
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDto movieDto){
    return ResponseEntity.ok(movieService.addMovie(movieDto));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }
    @GetMapping("/genre")
    public ResponseEntity<List<Movie>>getMoviesByGenre(@RequestParam String genre){
        return ResponseEntity.ok(movieService.getMoviesByGenre(genre));
    }
    @GetMapping("/language")
    public ResponseEntity<List<Movie>>getMoviesByLanguage(@RequestParam String language){
        return ResponseEntity.ok(movieService.getMoviesByLanguage(language));
    }
    @GetMapping("/title")
    public ResponseEntity<Movie>getMovieByTitle(@RequestParam String title){
        return ResponseEntity.ok(movieService.getMovieByTitle(title));
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie>updateMovie(@PathVariable Long id,@RequestBody MovieDto movieDto){
        return ResponseEntity.ok(movieService.updateMovie(id,movieDto));
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void>deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }
}
