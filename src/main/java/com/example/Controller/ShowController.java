package com.example.Controller;

import com.example.DTO.ShowDTO;
import com.example.Entity.Show;
import com.example.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {
    @Autowired
    private ShowService showService;
    @PostMapping("/createShow")
    public ResponseEntity<Show> addShow(@RequestBody ShowDTO showDTO) {
        return new ResponseEntity<>(showService.addShow(showDTO),HttpStatus.CREATED);
    }
    @GetMapping("/getAllShows")
    public ResponseEntity<List<Show>> getAllShows() {
        return new ResponseEntity<>(showService.getAllShows(), HttpStatus.OK);
    }
    @GetMapping("/getShowById/{id}")
    public ResponseEntity<List<Show>> getShowByMovieName(@PathVariable Long id) {
        return new ResponseEntity<>(showService.getShowByMovie(id), HttpStatus.OK);
    }
    @GetMapping("/getShowByTheater/{id}")
    public ResponseEntity<List<Show>> getShowByTheaterName(@PathVariable Long id) {
        return new ResponseEntity<>(showService.getShowByTheater(id), HttpStatus.OK);
    }
    @PutMapping("/updateShow/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable Long id, @RequestBody ShowDTO showDTO) {
        return new ResponseEntity<>(showService.updateShow(id, showDTO), HttpStatus.OK);
    }
    @DeleteMapping("/deleteShow/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
