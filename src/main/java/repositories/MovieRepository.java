package repositories;

import model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    public List<Movie> findByGenre(String genre);
    public List<Movie> findByLanguage(String language);
}
