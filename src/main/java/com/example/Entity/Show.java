package com.example.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.print.Book;
import java.util.List;

@Entity
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String showTime;
    private Double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "show")
    private List<Booking> bookings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Show(Long id, String showTime, Double price, Movie movie, Theater theater, List<Booking> bookings) {
        this.id = id;
        this.showTime = showTime;
        this.price = price;
        this.movie = movie;
        this.theater = theater;
        this.bookings = bookings;
    }

    public Show() {
    }
}
