package com.example.service;

import com.example.DTO.BookingDTO;
import com.example.Entity.Booking;
import com.example.Entity.Show;
import com.example.Repository.BookingRepository;
import com.example.Repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    public BookingRepository bookingRepository;
    @Autowired
    public ShowRepository showRepository;
    public Booking createBooking(BookingDTO bookingDTO) {

    }

    private boolean isSeatsAvailable(Long showid, int numberOfSeats) {
       
    }

    public List<Booking> getUserBookings(Long id) {
    }

    public List<Booking> getShowBookings(Long id) {
    }

    public List<Booking> confirmBooking(Long id) {
    }

    public List<Booking> cancelBooking(Long id) {
    }

    public List<Booking> getBookingByStatus() {
    }
}
