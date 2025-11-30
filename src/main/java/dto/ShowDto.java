package dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class ShowDto {
    private LocalDateTime showTime;
    private Double price;
    private Long movieId;
    private Long theatreId;
}
