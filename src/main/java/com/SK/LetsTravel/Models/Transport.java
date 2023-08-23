package com.SK.LetsTravel.Models;

import com.SK.LetsTravel.Enums.ModeOfTransport;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
@Data
public class Transport {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transportId;

    private ModeOfTransport modeOfTransport;

    private LocalDate journeyDate;

    private LocalTime startTime;

    @ManyToOne
    @JoinColumn
    private Route route;
}
