package com.SK.LetsTravel.Models;

import com.SK.LetsTravel.Enums.City;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Entity
@Table(name = "routes")
@Data //Getter Setter ToString RequiredArgsConstructor
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

    private String stopsInBetween;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Transport> transportList = new ArrayList<>();
}
