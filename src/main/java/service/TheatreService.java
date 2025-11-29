package service;

import dto.TheatreDto;
import model.Theatre;
import org.springframework.stereotype.Service;
import repositories.TheatreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TheatreService {
    private final TheatreRepository theatreRepository;
    TheatreService(TheatreRepository theatreRepository){
        this.theatreRepository=theatreRepository;
    }
    public Theatre addTheatre(TheatreDto theatreDto){
        Theatre theatre=new Theatre();
        theatre.setTheatreName(theatreDto.getTheatreName());
        theatre.setTheatreCapacity(theatreDto.getTheatreCapacity());
        theatre.setTheatreScreenType(theatre.getTheatreScreenType());
        theatre.setTheatreLocation(theatreDto.getTheatreLocation());
        return theatreRepository.save(theatre);
    }
    public List<Theatre> getTheatreByLocation(String location){

        List<Theatre> ans=theatreRepository.findByLocation(location);
        if(ans.isEmpty()){
            throw new RuntimeException("No Theatres found");
        }
        return ans;
    }
    public Theatre updateTheatre(Long id,TheatreDto theatreDto){
        Optional<Theatre> theatre=theatreRepository.findById(id);
        if(theatre.isPresent()) {
            Theatre ans=theatre.get();
            ans.setTheatreName(theatreDto.getTheatreName());
            ans.setTheatreCapacity(theatreDto.getTheatreCapacity());
            ans.setTheatreScreenType(theatreDto.getTheatreScreenType());
            ans.setTheatreLocation(theatreDto.getTheatreLocation());
            return theatreRepository.save(ans);
        }else{
            throw new RuntimeException("Id not found");
        }

    }
    public void deleteTheatre(Long id){
        theatreRepository.deleteById(id);
    }

}
