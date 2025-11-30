package service;

import dto.BookingDto;
import model.Booking;
import model.BookingStatus;
import model.Show;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import repositories.BookingRepository;
import repositories.ShowRepository;
import repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

    public Booking createBooking(BookingDto bookingDto){
        Show show=showRepository.findById(bookingDto.getShowId())
                .orElseThrow(()->new RuntimeException("Show not found"));
        if(!isSeatsAvailable(show.getId(),bookingDto.getNoOfSeats())){
            throw new RuntimeException("No seats available");
        }
        if(bookingDto.getBookingSeats().size()!=bookingDto.getNoOfSeats()){
            throw new RuntimeException("seat numbers and Numbers of seats ")
        }

        validateSeatNumbers(show.getId(),bookingDto.getBookingSeats());
        User user=userRepository.findById(bookingDto.getUserId())
                .orElseThrow(()->new RuntimeException("No user found"));
        Booking booking=new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setNoOfSeats(bookingDto.getNoOfSeats());
        booking.setBookingSeats(bookingDto.getBookingSeats());
        booking.setPrice(calculateAmt(show.getPrice(),bookingDto.getNoOfSeats()));
        booking.setBookingTime(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.PENDING);
        return bookingRepository.save(booking);
    }
    public boolean isSeatsAvailable(Long showId,Integer NoOfSeats){
        Show show=showRepository.findById(showId)
                .orElseThrow(()->new RuntimeException("Show not found"));
        List<Booking> bookings=show.getBookings();
        int bookedSeats=0;
        for(Booking booking: bookings){
            if(booking.getBookingStatus()!=BookingStatus.CANCELLED)
                bookedSeats+=booking.getNoOfSeats();
        }
        return (show.getTheatre().getTheatreCapacity()-bookedSeats)>=NoOfSeats;
    }
    public void validateSeatNumbers(Long showId,List<String> seatNumbers){
        Show show=showRepository.findById(showId)
                .orElseThrow(()->new RuntimeException("Show not found"));
        Set<String> occupiedSeats=show.getBookings().stream()
                .filter(b->b.getBookingStatus()!=BookingStatus.CANCELLED)
                .flatMap(b->b.getBookingSeats().stream())
                .collect(Collectors.toSet());
        List<String>duplicateSeats=seatNumbers.stream().
                filter(occupiedSeats::contains).
                collect(Collectors.toList());
        if(!duplicateSeats.isEmpty()){
            throw new RuntimeException("Seats already booked");
        }
    }
    public Double calculateAmt(Double price, Integer NoOfSeats){
        return price*NoOfSeats;

    }
    public List<Booking> getUserBooking(Long id){
        return bookingRepository.findByUserId(id);

    }
    public List<Booking>getUserShows(Long id){
        return bookingRepository.findByShowId(id);

    }


}
