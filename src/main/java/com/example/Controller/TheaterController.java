package com.example.Controller;

import com.example.DTO.TheaterDTO;
import com.example.Entity.Theater;
import com.example.Repository.TheaterRepository;
import com.example.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/theater")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;

    public ResponseEntity<Theater> saveTheater(@RequestBody TheaterDTO theaterDTO) {
        Theater savedTheater = theaterService.addTheater(theaterDTO);
        return ResponseEntity.ok(savedTheater);

    }
}
