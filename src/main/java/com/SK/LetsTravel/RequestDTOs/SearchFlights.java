package com.SK.LetsTravel.RequestDTOs;

import com.SK.LetsTravel.Enums.City;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchFlights {
    private City fromCity;

    private City toCity;

    private LocalDate journeyDate;
}
