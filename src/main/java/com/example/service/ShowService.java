package com.example.service;

import com.example.DTO.ShowDTO;
import com.example.Entity.Booking;
import com.example.Entity.Movie;
import com.example.Entity.Show;
import com.example.Entity.Theater;
import com.example.Repository.MovieRepository;
import com.example.Repository.ShowRepository;
import com.example.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;

    public Show addShow(ShowDTO showDTO) {
        Movie movie= movieRepository.findById(showDTO.getMovieId()).orElseThrow(()->
                new RuntimeException("Movie not found with id: " + showDTO.getMovieId()));
        Theater theater = theaterRepository.findById(showDTO.getTheaterId()).orElseThrow(()->
                new RuntimeException("Theater not found with id: " + showDTO.getTheaterId()));

        Show show=new Show();
        show.setShowTime(showDTO.getShowTime());
        show.setMovie(movie);
        show.setTheater(theater);
        show.setPrice(showDTO.getPrice());

        return showRepository.save(show);
    }

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public List<Show> getShowByMovie(Long id) {
        Optional<List<Show>> listOfShows = showRepository.findByMovieId(id);
        if (listOfShows.isPresent()) {
            return listOfShows.get();
        } else {
            throw new RuntimeException("No shows available for movie with id: " + id);
        }
     }

    public List<Show> getShowByTheater(Long id) {
        List<Show> shows = showRepository.findByTheaterId(id);
        if (shows.isEmpty()) {
            throw new RuntimeException("No shows available for theater: " + id);
        }
        return shows;
    }

    public Show updateShow(Long id, ShowDTO showDTO) {
        Show show = showRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not available for id: " + id));
        Movie movie= movieRepository.findById(showDTO.getMovieId()).orElseThrow(()->
                new RuntimeException("Movie not found with id: " + showDTO.getMovieId()));
        Theater theater = theaterRepository.findById(showDTO.getTheaterId()).orElseThrow(()->
                new RuntimeException("Theater not found with id: " + showDTO.getTheaterId()));
        show.setShowTime(showDTO.getShowTime());
        show.setMovie(movie);
        show.setTheater(theater);
        show.setPrice(showDTO.getPrice());
        return showRepository.save(show);
    }

    public void deleteShow(Long id) {
        if(!showRepository.existsById(id)) {
            throw new RuntimeException("Show not found with id: " + id);
        }
        List<Booking> bookings = showRepository.findById(id).get().getBookings();
        if(bookings.isEmpty()){
            throw new RuntimeException("Cannot delete show with id: " + id + " as it has associated bookings.");
        } else {
            showRepository.deleteById(id);

        }

    }
}
