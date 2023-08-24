package com.SK.LetsTravel.RequestDtos;

import com.SK.LetsTravel.Enums.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRouteRequestDto {
    private City fromCity;

    private City toCity;

    String stopsInBetween;
}
