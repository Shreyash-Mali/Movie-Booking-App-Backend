package com.example.service;

import com.example.DTO.TheaterDTO;
import com.example.Entity.Theater;
import com.example.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    public  Theater addTheater(TheaterDTO theaterDTO) {
        Theater theater = new Theater();
        theater.setName(theaterDTO.getName());
        theater.setLocation(theaterDTO.getLocation());
        theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
        theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
        return theaterRepository.save(theater);
    }

    public List<Theater> getTheaterByLocation(String location) {
        Optional<List<Theater>> listOfTheaters = theaterRepository.findByLocation(location);
        if (listOfTheaters.isPresent()) {
            return listOfTheaters.get();
        } else {
            throw new RuntimeException("No theaters found for location: " + location);
        }
    }

    public Theater updateTheater(Long id, TheaterDTO theaterDTO) {
        Optional<Theater> theater= theaterRepository.findById(id);
        if (theater.isPresent()) {
            Theater existingTheater = theater.get();
            existingTheater.setName(theaterDTO.getName());
            existingTheater.setLocation(theaterDTO.getLocation());
            existingTheater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
            existingTheater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
            return theaterRepository.save(existingTheater);
        } else {
            throw new RuntimeException("Theater not found with id: " + id);
        }
    }

    public void deleteTheater(Long id) {
        Optional<Theater> theater = theaterRepository.findById(id);
        if (theater.isPresent()) {
            theaterRepository.delete(theater.get());
        } else {
            throw new RuntimeException("Theater not found with id: " + id);
        }
    }
}
