package com.example.service;

import com.example.DTO.BookingDTO;
import com.example.Entity.Booking;
import com.example.Entity.BookingStatus;
import com.example.Entity.Show;
import com.example.Entity.User;
import com.example.Repository.BookingRepository;
import com.example.Repository.ShowRepository;
import com.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class BookingService {

    @Autowired
    public BookingRepository bookingRepository;
    @Autowired
    public ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;
    public Booking createBooking(BookingDTO bookingDTO) {
        Show show = showRepository.findById(bookingDTO.getShowId())
                .orElseThrow(() -> new RuntimeException("Show not found"));

        if(!isSeatsAvailable(show.getId(), bookingDTO.getNumberOfSeats())) {
            throw new RuntimeException("Not enough seat are available");
        }
        if(bookingDTO.getSeatNumbers().size() != bookingDTO.getNumberOfSeats()) {
            throw new RuntimeException("Number of seats does not match the number of seat numbers provided");
        }
        validateDuplicateSeats(show.getId(), bookingDTO.getSeatNumbers());
        User user=userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
        booking.setSeatNumbers(bookingDTO.getSeatNumbers());
        booking.setBookingTime(bookingDTO.getBookingTime());
        booking.setPrice(CalculateTotalAmount(show.getPrice(), bookingDTO.getNumberOfSeats()));
        booking.setBookingStatus(BookingStatus.PENDING);
        return  bookingRepository.save(booking);

    }

    private Double CalculateTotalAmount(Double price, Integer numberOfSeats) {
        return price * numberOfSeats;
    }

    public boolean isSeatsAvailable(Long showid, Integer numberOfSeats) {
        Show show = showRepository.findById(showid)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        int bookedSeats = show.getBookings().stream()
                .filter(booking -> booking.getBookingStatus() != BookingStatus.CANCELLED)
                .mapToInt(Booking::getNumberOfSeats)
                .sum();
        return (show.getTheater().getTheaterCapacity() - bookedSeats) >= numberOfSeats;
    }
    private void validateDuplicateSeats(Long showId, List<String> seatNumbers) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));
        Set<String> occupiedSeats = show.getBookings().stream()
                .filter(booking -> booking.getBookingStatus() != BookingStatus.CANCELLED)
                .flatMap(booking -> booking.getSeatNumbers().stream())
                .collect(Collectors.toSet());

        List<String> duplicateSeats = seatNumbers.stream()
                .filter(occupiedSeats::contains)
                .toList();
        if(!duplicateSeats.isEmpty()) {
            throw new RuntimeException("The following seats are already booked: " + String.join(", ", duplicateSeats));
        }
    }

    public List<Booking> getUserBookings(Long userId) {
        return bookingRepository.findByShowId(userId);

    }

    public List<Booking> getShowBookings(Long showId) {
        return bookingRepository.findByShowId(showId);
    }

    public Booking confirmBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        if (booking.getBookingStatus() != BookingStatus.PENDING) {
            throw new RuntimeException("Booking is not in PENDING status and cannot be confirmed");
        }
        //PAYMENT PROCESS
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        return bookingRepository.save(booking);
    }

    public Booking cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        validateCancellation(booking);
        booking.setBookingStatus(BookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }

    private void validateCancellation(Booking booking) {
        LocalDateTime showTime= LocalDateTime.parse(booking.getShow().getShowTime());
        LocalDateTime deadlineTime = showTime.minusHours(2);
        if (LocalDateTime.now().isAfter(deadlineTime)) {
            throw new RuntimeException("Booking cannot be cancelled within 2 hours of the show time");
        }
        if(booking.getBookingStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Booking is already cancelled");
        }

    }

//    public List<Booking> getBookingByStatus() {
//    }
}
