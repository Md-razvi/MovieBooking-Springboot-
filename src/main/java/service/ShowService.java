package service;

import dto.ShowDto;
import model.Booking;
import model.Movie;
import model.Show;
import model.Theatre;
import repositories.MovieRepository;
import repositories.ShowRepository;
import repositories.TheatreRepository;

import java.util.List;
import java.util.Optional;

public class ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private  final TheatreRepository theatreRepository;
    ShowService(ShowRepository showRepository,
                MovieRepository movieRepository,
                TheatreRepository theatreRepository){
        this.showRepository=showRepository;
        this.movieRepository=movieRepository;
        this.theatreRepository=theatreRepository;
    }
    public Show createShow(ShowDto showDto){
//        private Double price;
//        private Long showId;
//        private Long theatreId;
        Movie movie=movieRepository.findById(showDto.getMovieId()).
                orElseThrow(()-> new RuntimeException("Movie Id not found"));
        Theatre theatre=theatreRepository.findById(showDto.getTheatreId()).
                orElseThrow(()->new RuntimeException("No such theatre available"));

        Show show=new Show();
        show.setShowTime(showDto.getShowTime());
        show.setPrice(showDto.getPrice());
        show.setMovie(movie);
        show.setTheatre(theatre);
        return showRepository.save(show);
    }
    public List<Show> getAllShow(){
        return showRepository.findAll();

    }
    public List<Show> getShowsByMovie(Long movieId){

        List<Show>arr= showRepository.findByMovieId(movieId);
        if(arr.isEmpty()){
            throw new RuntimeException("No Shows available for this movie");
        }
        return arr;
    }
    public List<Show> getShowByTheatre(Long theatreId){

        List<Show>arr= showRepository.findByTheatreId(theatreId);
        if(arr.isEmpty()){
            throw new RuntimeException("No Shows available for this movie");
        }
        return arr;
    }
    public Show updateShow(Long id,ShowDto showDto){
        Show show=showRepository.findById(id).
                orElseThrow(()->new RuntimeException("there is no show availabe for such id"+id));
        Movie movie=movieRepository.findById(showDto.getMovieId()).
                orElseThrow(()-> new RuntimeException("Movie Id not found"));
        Theatre theatre=theatreRepository.findById(showDto.getTheatreId()).
                orElseThrow(()->new RuntimeException("No such theatre available"));
        show.setShowTime(showDto.getShowTime());
        show.setPrice(showDto.getPrice());
        show.setMovie(movie);
        show.setTheatre(theatre);
        return showRepository.save(show);
    }
public void deleteShow(Long id){
        if(!showRepository.existsById(id)){
            throw new RuntimeException("The id for such show is not available");
        }
        List<Booking> bookings=showRepository.findById(id).get().getBookings();
        if(!bookings.isEmpty()){
            throw new RuntimeException("Cant delete with existing bookings here");
        }
        showRepository.deleteById(id);

}


}
