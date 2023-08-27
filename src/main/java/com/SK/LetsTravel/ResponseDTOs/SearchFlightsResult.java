package com.SK.LetsTravel.ResponseDTOs;

import lombok.*;

import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchFlightsResult {
    private LocalDate journeyDate;

    private LocalTime startTime;

    private Double journeyTime;

    private String companyName;

    private String stopsInBetween;
}
