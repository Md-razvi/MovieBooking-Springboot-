package controller;

import dto.TheatreDto;
import model.Theatre;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import service.TheatreService;

import java.util.List;

@RestController
@RequestMapping("/api/theatre")
public class TheatreController {
    //Contructor injection for now
    private final TheatreService theatreService;

    TheatreController(TheatreService theatreService){
        this.theatreService=theatreService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theatre> addTheatre(@RequestBody TheatreDto theatreDto){
        return ResponseEntity.ok(theatreService.addTheatre(theatreDto));
    }
    @GetMapping("/loc")
    public ResponseEntity<List<Theatre>> getTheatreByLocation(@RequestParam String location){
        return ResponseEntity.ok(theatreService.getTheatreByLocation(location));
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theatre> updateTheatre(@PathVariable Long id,@RequestBody TheatreDto theatreDto){
        return ResponseEntity.ok(theatreService.updateTheatre(id,theatreDto));
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTheatre(@PathVariable Long id){
        theatreService.deleteTheatre(id);
        return ResponseEntity.ok().build();
    }

}
