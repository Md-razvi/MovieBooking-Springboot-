package service;

import controller.MovieController;
import dto.MovieDto;
import model.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import repositories.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    //Instead of simple Filed injection such as Auto Wire I will use  Contructor Injection here
    private final MovieRepository movieRepository;
    MovieService(MovieRepository movieRepository){
        this.movieRepository=movieRepository;
    }
    public Movie addMovie(MovieDto movieDto){
        Movie movie=new Movie();
        movie.setName(movieDto.getName());
        movie.setDescription(movieDto.getDescription());
        movie.setGenre(movieDto.getGenre());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setDuration(movieDto.getDuration());
        movie.setLanguage(movieDto.getLanguage());
        return movieRepository.save(movie);
    }
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
    public List<Movie> getMoviesByGenre(String genre){
        List<Movie> byGenre=movieRepository.findByGenre(genre);
        if(byGenre.isEmpty()){
            throw new RuntimeException("there is no such genre present try again");
        }
        return byGenre;
    }
    public List<Movie>getMoviesByLanguage(String language){
        List<Movie> byLanguage=movieRepository.findByLanguage(language);
        if(byLanguage.isEmpty()){
            throw new RuntimeException("Sorry the given language is not found in repository");
        }
        return byLanguage;
    }
    public Movie getMovieByTitle(String title){
        Optional<Movie> movie=movieRepository.findByName(title);
        if(movie.isEmpty()){
            throw new RuntimeException("No movie of such name found");
        }
            return movie.get();
    }
    public Movie updateMovie(Long id,MovieDto movieDto){
        Movie movie=movieRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Id not found"));
        movie.setName(movieDto.getName());
        movie.setDescription(movieDto.getDescription());
        movie.setGenre(movieDto.getGenre());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setDuration(movieDto.getDuration());
        movie.setLanguage(movieDto.getLanguage());
        return movieRepository.save(movie);
    }
    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }

}
