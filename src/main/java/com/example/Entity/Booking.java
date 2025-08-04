package com.example.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberOfSeats;
    private LocalDateTime bookingTime;
    private Double price;
    private BookingStatus bookingStatus;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "booking_seat_numbers")
    private List<String> seatNumbers;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;
}
