package dto;

import lombok.Getter;
import lombok.Setter;
import model.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BookingDto {
    private Integer NoOfSeats;
    private LocalDateTime bookingTime;
    private Double price;
    private BookingStatus bookingStatus;
    private List<String> bookingSeats;
    private Long userId;
    private Long showId;

}
