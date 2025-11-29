package dto;

import lombok.Data;

@Data
public class TheatreDto {
    private String theatreName;
    private String theatreLocation;
    private Integer theatreCapacity;
    private String theatreScreenType;
}
