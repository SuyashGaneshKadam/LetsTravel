package com.SK.LetsTravel.Models;

import com.SK.LetsTravel.Enums.City;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table
@Data
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer routeId;

    private City fromCity;

    private City toCity;

    private String listOfStopsInBetween;

    private LocalDate date;
}
