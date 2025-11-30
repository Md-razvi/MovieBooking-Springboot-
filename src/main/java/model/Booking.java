package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking {
private Long Id;
private Integer NoOfSeats;
private LocalDateTime bookingTime;
private Double price;
private BookingStatus bookingStatus;
@ManyToOne(fetch = FetchType.EAGER)
@ElementCollection(fetch = FetchType.EAGER)
@CollectionTable(name="booking_seats")
private List<String> bookingSeats;
    @JoinColumn(name="user_id",nullable = false)
private User user;

@ManyToOne(fetch=FetchType.EAGER)
@JoinColumn(name="show_id",nullable = false)
private Show show;
}
