package com.SK.LetsTravel.RequestDTOs;

import com.SK.LetsTravel.Enums.City;
import com.SK.LetsTravel.Enums.ModeOfTransport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRoute {
    private City fromCity;

    private City toCity;

    private String stopsInBetween;

    private ModeOfTransport modeOfTransport;
}
