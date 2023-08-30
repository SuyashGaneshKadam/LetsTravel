package com.SK.LetsTravel.RequestDTOs;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBusSeats {
    private Integer transportId;

    private Integer noOfWindowSeats;

    private Integer noOfACSeats;

    private Integer noOfSleeperSeats;

    private Integer priceOfWindowSeat;

    private Integer priceOfACSeat;

    private Integer priceOfSleeperSeat;
}
