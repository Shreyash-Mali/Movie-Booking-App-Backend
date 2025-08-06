package com.example.Controller;

import com.example.DTO.BookingDTO;
import com.example.Entity.Booking;
import com.example.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@Service
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/createBooking")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO) {
        Booking createdBooking = bookingService.createBooking(bookingDTO);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @GetMapping("/getBooking/{id}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long id){
        return new ResponseEntity<>(bookingService.getUserBookings(id), HttpStatus.OK);
    }

    @GetMapping("/getShowBooking/{id}")
    public ResponseEntity<List<Booking>> getShowBookings(@PathVariable Long id) {
        return new ResponseEntity<>(bookingService.getShowBookings(id), HttpStatus.OK);
    }
    @PutMapping("{id}/confirmBooking")
    public  ResponseEntity<Booking> confirmBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.confirmBooking(id));
    }
    @PutMapping("{id}/cancelBooking")
    public  ResponseEntity<Booking> cancelBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }
    @GetMapping("/getBookingByStatus/{bookingStatus}")
    public ResponseEntity<List<Booking>> getBookingByStatus() {
        return ResponseEntity.ok(bookingService.getBookingByStatus());
    }
}
