package com.example.Controller;

import com.example.DTO.MovieDTO;
import com.example.Entity.Movie;
import com.example.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MoveController {
    @Autowired
    private MovieService service;

    @PostMapping("/addMovie")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> addMovie(MovieDTO movieDTO) {
        Movie savedMovie = service.addMovie(movieDTO);
        return ResponseEntity.ok(savedMovie);
    }

    @GetMapping("/getAllMovies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = service.getAllMovies();
        return ResponseEntity.ok(movies);
    }
    @GetMapping("/getMovieByGenre")
    public ResponseEntity<List<Movie>> getMovieByGenre(@RequestParam String genre){
        return ResponseEntity.ok(service.getMovieByGenre(genre));
    }
    @GetMapping("/getMovieByLanguage")
    public ResponseEntity<List<Movie>> getMovieByLanguage(@RequestParam String language){
        return ResponseEntity.ok(service.getMovieByLanguage(language));
    }
    @GetMapping("/getMovieByName")
    public ResponseEntity<Movie> getMovieByTitle(@RequestParam String name){
        return ResponseEntity.ok(service.getMovieByName(name));
    }
    @PutMapping("/updateMovie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        Movie updatedMovie = service.updateMovie(id, movieDTO);
        return ResponseEntity.ok(updatedMovie);
    }
    @DeleteMapping("/deleteMovie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        service.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

}
