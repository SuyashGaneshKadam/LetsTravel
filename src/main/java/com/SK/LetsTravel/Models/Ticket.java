package com.SK.LetsTravel.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tickedId;

    private String routeDetails; // From City TO To City - Pune TO Mumbai

    private String stopsInBetween;

    private LocalDate journeyDate;

    private LocalTime startTime;

    private String seatNos; //Comma separated values : E1,E8,B4

    private Integer totalCostPaid;

    @OneToOne
    @JoinColumn
    private Booking booking;
}
