package com.example.service;

import com.example.DTO.MovieDTO;
import com.example.Entity.Movie;
import com.example.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public Movie addMovie(MovieDTO movieDTO) {
        Movie movie=new Movie();
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setGenre(movieDTO.getGenre());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDuration(movieDTO.getDuration());
        movie.setLanguage(movieDTO.getLanguage());
        movieRepository.save(movie);
        return movie;

    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> getMovieByGenre(String genre) {
        Optional<List<Movie>> ListOfMovie= movieRepository.findByGenre(genre);
        if(ListOfMovie.isPresent()){
            return ListOfMovie.get();
        }else
             throw new RuntimeException("No movies found for genre: " + genre);
    }

    public List<Movie> getMovieByLanguage(String language) {
        Optional<List<Movie>> ListOfMovie= movieRepository.findByLanguage(language);
        if(ListOfMovie.isPresent()){
            return ListOfMovie.get();
        }else
             throw new RuntimeException("No movies found for language: " + language);
    }

    public Movie getMovieByName(String name) {
        Optional<Movie> movie = movieRepository.findByName(name);
        if(movie.isPresent()){
            return movie.get();
        } else {
            throw new RuntimeException("No movie found with Name: " + name);
        }
    }

    public Movie updateMovie(Long id, MovieDTO movieDTO) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));

        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setGenre(movieDTO.getGenre());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDuration(movieDTO.getDuration());
        movie.setLanguage(movieDTO.getLanguage());

        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie not found with id: " + id);
        }
        movieRepository.deleteById(id);
    }
}
