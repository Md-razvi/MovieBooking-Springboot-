package repositories;

import model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre,Long> {

    List<Theatre> findByLocation(String location);
    Theatre findById(String id);
}
