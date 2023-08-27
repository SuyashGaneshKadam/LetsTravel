package com.SK.LetsTravel.Models;

import com.SK.LetsTravel.Enums.ModeOfTransport;
import com.SK.LetsTravel.Enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transport {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transportId;

    @Enumerated(value = EnumType.STRING)
    private ModeOfTransport modeOfTransport;

    private LocalDate journeyDate;

    private LocalTime startTime;

    private Double journeyTime; //Duration in hours - 1.5

    private String companyName;

    @ManyToOne
    @JoinColumn
    private Route route;

    @OneToMany(mappedBy = "transport", cascade = CascadeType.ALL)
    private List<Seat> seatList = new ArrayList<>();

    @OneToMany(mappedBy = "transport", cascade = CascadeType.ALL)
    private List<Booking> bookingList = new ArrayList<>();
}
