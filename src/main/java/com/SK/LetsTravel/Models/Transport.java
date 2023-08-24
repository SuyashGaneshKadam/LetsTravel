package com.SK.LetsTravel.Models;

import com.SK.LetsTravel.Enums.ModeOfTransport;
import com.SK.LetsTravel.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    private Double journeyTime;

    @ManyToOne
    @JoinColumn
    private Route route;

    @OneToMany(mappedBy = "transport", cascade = CascadeType.ALL)
    private List<Seat> seatList = new ArrayList<>();

    @OneToMany(mappedBy = "transport", cascade = CascadeType.ALL)
    private List<Booking> bookingList = new ArrayList<>();
}
