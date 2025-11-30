package controller;

import dto.BookingDto;
import model.Booking;
import model.BookingStatus;
import model.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @PostMapping("/create")
    ResponseEntity<Booking>createBooking(@RequestBody BookingDto bookingDto){
        return ResponseEntity.ok(bookingService.createBooking(bookingDto));
    }
    @GetMapping("/all/{id}")
    public ResponseEntity<List<Booking>> getUserBooking(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.getUserBooking(id));
    }
    @GetMapping("/shows/{id}")
    public ResponseEntity<List<Booking>> getUserShows(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.getUserShows(id));
    }
    @PutMapping("/{id}/confirm")
    public ResponseEntity<Booking> confirmBooking(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.confirmBooking(id));
    }
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }
    @GetMapping("/status/{bookingStatus}")
    public ResponseEntity<List<Booking>>getBookingByStatus(@PathVariable BookingStatus bookingStatus){
        return ResponseEntity.ok(bookingStatus.getBookingStatus(bookingStatus));
    }

}
