package com.SK.LetsTravel.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tickedId;

    private String routeDetails;

    private LocalDate journeyDate;

    private LocalTime startTime;

    private String seatNos; //Comma separated values : 1A,1B,2A

    private Integer totalCostPaid;

    @OneToOne
    @JoinColumn
    private Booking booking;
}
