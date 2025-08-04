package com.example.Controller;

import com.example.DTO.TheaterDTO;
import com.example.Entity.Theater;
import com.example.Repository.TheaterRepository;
import com.example.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theater")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;
    @PostMapping("/addTheater")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> saveTheater(@RequestBody TheaterDTO theaterDTO) {
        Theater savedTheater = theaterService.addTheater(theaterDTO);
        return ResponseEntity.ok(savedTheater);

    }
    @GetMapping("/getAllTheaterByLocation")
    public ResponseEntity<List<Theater>> getTheaterByLocation(@RequestParam String location) {
        List<Theater> theaters = theaterService.getTheaterByLocation(location);
        return ResponseEntity.ok(theaters);
    }
    @PutMapping("/updateTheater/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> updateTheater(Long id,TheaterDTO theaterDTO){
        Theater updatedTheater = theaterService.updateTheater(id, theaterDTO);
        return ResponseEntity.ok(updatedTheater);
    }
    @DeleteMapping("/deleteTheater/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long id) {
        theaterService.deleteTheater(id);
        return ResponseEntity.noContent().build();
    }
}
