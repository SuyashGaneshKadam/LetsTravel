package com.SK.LetsTravel.Models;

import com.SK.LetsTravel.Enums.City;
import com.SK.LetsTravel.Enums.ModeOfTransport;
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
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer routeId;

    @Enumerated(value = EnumType.STRING)
    private City fromCity;

    @Enumerated(value = EnumType.STRING)
    private City toCity;

    private String stopsInBetween;  //Comma Separated - Mumbai,Pune

    @Enumerated(value = EnumType.STRING)
    private ModeOfTransport modeOfTransport;   // Flight, Train or Bus

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Transport> transportList = new ArrayList<>();
}
