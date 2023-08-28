package com.SK.LetsTravel.Transformers;

import com.SK.LetsTravel.Models.Seat;
import com.SK.LetsTravel.ResponseDTOs.AvailableSeats;

public class SeatTransformer {
    public static AvailableSeats convertEntityToDto(Seat seat){
        AvailableSeats availableSeats = AvailableSeats.builder().seatNo(seat.getSeatNo())
                .seatType(seat.getSeatType()).seatPrice(seat.getPrice()).build();
        return availableSeats;
    }
}
