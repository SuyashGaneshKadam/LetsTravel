package com.SK.LetsTravel.RequestDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFlightSeats {
    private Integer transportId;

    private Integer noOfEconomySeats;

    private Integer noOfBusinessSeats;

    private Integer priceOfEconomySeat;

    private Integer priceOfBusinessSeat;
}
