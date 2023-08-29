package com.SK.LetsTravel.Transformers;

import com.SK.LetsTravel.Models.Booking;
import com.SK.LetsTravel.RequestDTOs.BookSeatsInFlight;

public class BookingTransformer {
    public static Booking convertDtoToEntity(BookSeatsInFlight bookSeatsInFlight){
        Booking booking = Booking.builder().seatNos(bookSeatsInFlight.getSeatNos()).
                journeyDate(bookSeatsInFlight.getJourneyDate()).build();
        return booking;
    }
}
