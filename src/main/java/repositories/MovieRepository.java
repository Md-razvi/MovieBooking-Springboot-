package repositories;

import model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    public List<Movie> findByGenre(String genre);
    public List<Movie> findByLanguage(String language);
    Optional<Movie> findByName(String name);
    Optional<Movie>findById(Long id);
}
