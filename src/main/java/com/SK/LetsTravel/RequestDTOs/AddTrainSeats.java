package com.SK.LetsTravel.RequestDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTrainSeats {
    private Integer transportId;

    private Integer noOfUpperSeats;

    private Integer noOfMiddleSeats;

    private Integer noOfLowerSeats;

    private Integer priceOfUpperSeat;

    private Integer priceOfMiddleSeat;

    private Integer priceOfLowerSeat;
}