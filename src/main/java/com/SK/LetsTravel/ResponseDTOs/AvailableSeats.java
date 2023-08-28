package com.SK.LetsTravel.ResponseDTOs;

import com.SK.LetsTravel.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvailableSeats {
    private String seatNo;

    private SeatType seatType;

    private Integer seatPrice;
}
