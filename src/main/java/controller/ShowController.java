package controller;

import dto.ShowDto;
import model.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.ShowService;

import java.util.List;

@RestController
@RequestMapping("/api/show")
public class ShowController {
    @Autowired
    private ShowService showService;
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Show> createShow(@RequestBody ShowDto showDto){
        return ResponseEntity.ok(showService.createShow(showDto));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Show>>getAllShow(){
        return ResponseEntity.ok(showService.getAllShow());
    }
    @GetMapping("/all/movie/{movieId}")
    public ResponseEntity<List<Show>> getShowsByMovie(@PathVariable Long movieId){
        return ResponseEntity.ok(showService.getShowsByMovie(movieId));
    }
    @GetMapping("/all/theatre/{theatreId}")
    public ResponseEntity<List<Show>>getShowsByTheatre(@PathVariable Long theatreId){
        return ResponseEntity.ok(showService.getShowByTheatre(theatreId));
    }
    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Show> updateShow(@PathVariable Long id,@RequestBody ShowDto showDto){
        return ResponseEntity.ok(showService.updateShow(id,showDto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id){
        showService.deleteShow(id);
        return ResponseEntity.ok().build();
    }

}
