package com.SK.LetsTravel.RequestDtos;

import com.SK.LetsTravel.Enums.ModeOfTransport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTransportRequestDto {
    private ModeOfTransport modeOfTransport;

    private LocalDate journeyDate;

    private LocalTime startTime;

    private Double journeyTime;

    private Integer routeId;
}
